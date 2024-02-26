package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class MessageRestController {

    private final MessageService messageService;

    @Autowired
    public MessageRestController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public List<String> getMessages() {
        return messageService.getMessages();
    }
}
