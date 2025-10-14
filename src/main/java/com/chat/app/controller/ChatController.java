package com.chat.app.controller;

import com.chat.app.model.ChatMessage;
import com.chat.app.model.ChatRoom;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    private Map<String, ChatRoom> rooms = new ConcurrentHashMap<>();
    private Map<String, Set<String>> onlineUsers = new ConcurrentHashMap<>(); // roomId -> set of users

    // --- Send message ---
    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage sendMessage(@DestinationVariable String roomId, ChatMessage message){
        message.setTimestamp(LocalDateTime.now());
        return message;
    }

    // --- Typing indicator ---
    @MessageMapping("/typing/{roomId}")
    public void typing(@DestinationVariable String roomId, Map<String,String> payload){
        messagingTemplate.convertAndSend("/topic/typing/" + roomId, payload);
    }

    // --- Create Room ---
    @PostMapping("/createRoom")
    @ResponseBody
    public String createRoom(@RequestParam String roomName,
                             @RequestParam(required=false) String password){
        String roomId = UUID.randomUUID().toString().replace("-", "").substring(0,6);
        ChatRoom room = new ChatRoom(roomId, roomName, password);
        rooms.put(roomId, room);
        return roomId;
    }

    // --- Join Room ---
    @PostMapping("/joinRoom")
    @ResponseBody
    public Map<String,String> joinRoom(@RequestParam String roomId,
                                       @RequestParam(required=false) String password,
                                       @RequestParam String userName){
        Map<String,String> resp = new HashMap<>();
        ChatRoom room = rooms.get(roomId);
        if(room == null || (room.getPassword() != null && !room.getPassword().equals(password))){
            resp.put("success","false");
            return resp;
        }
        resp.put("success","true");
        resp.put("roomName", room.getRoomName());

        // Add user to online list
        onlineUsers.putIfAbsent(roomId, ConcurrentHashMap.newKeySet());
        onlineUsers.get(roomId).add(userName);

        // Broadcast online users
        messagingTemplate.convertAndSend("/topic/users/" + roomId, onlineUsers.get(roomId));

        return resp;
    }

    // --- Leave room / disconnect ---
    @PostMapping("/leaveRoom")
    @ResponseBody
    public void leaveRoom(@RequestParam String roomId, @RequestParam String userName){
        if(onlineUsers.containsKey(roomId)){
            onlineUsers.get(roomId).remove(userName);
            messagingTemplate.convertAndSend("/topic/users/" + roomId, onlineUsers.get(roomId));
        }
    }
}
