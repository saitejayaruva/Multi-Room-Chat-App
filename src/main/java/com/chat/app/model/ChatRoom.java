package com.chat.app.model;

public class ChatRoom {
    private String roomId;
    private String roomName;
    private String password;

    public ChatRoom(String roomId, String roomName, String password) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.password = password;
    }

    public String getRoomId() { return roomId; }
    public String getRoomName() { return roomName; }
    public String getPassword() { return password; }
}
