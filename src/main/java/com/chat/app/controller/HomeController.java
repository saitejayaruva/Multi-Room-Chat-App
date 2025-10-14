package com.chat.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/chat")
    public String redirectToChatHtml() {
        return "redirect:/chat.html";
    }
}
