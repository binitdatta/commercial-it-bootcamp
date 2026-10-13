package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated lesson route. All existing internship routes remain unchanged. */
@Controller
public class AzureAiChatbotController {

    @GetMapping("/internships/cloud-computing/azure/ai-chatbot-foundry-rag")
    public String lesson() {
        return "internships/azure-ai-chatbot-foundry-rag";
    }
}
