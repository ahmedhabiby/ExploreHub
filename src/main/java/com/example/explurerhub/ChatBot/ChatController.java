package com.example.explurerhub.ChatBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class ChatController {

    private final GeminiService pythonService;

    @Autowired
    public ChatController(GeminiService pythonService) {
        this.pythonService = pythonService;
    }

    @GetMapping("/chat")
    public String index() {
        return "chat"; // resources/templates/chat.html
    }

    @PostMapping("/chat/ask")
    @ResponseBody
    public String askChat(@RequestBody Map<String, String> payload) {
        String query = payload.get("query");
        return pythonService.askChatBot(query);
    }

    @PostMapping("/chat/plan")
    @ResponseBody
    public String plan(@RequestBody Map<String, String> payload) {
        String location = payload.get("location");
        int duration_days = Integer.parseInt(payload.get("duration_days"));
        String interests = payload.get("interests");
        return pythonService.generateTourPlan(location, duration_days, interests);
    }
}
