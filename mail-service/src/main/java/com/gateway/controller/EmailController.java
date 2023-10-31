package com.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gateway.service.EmailSenderService;

@RestController
@RequestMapping("/mail")
public class EmailController {
	
	@Autowired
	EmailSenderService service;

	@GetMapping("/sendMail/{to}/{subject}/{body}")
	public String sendMail(@PathVariable String to,@PathVariable String subject,@PathVariable String body)
	{
		return service.sendEmail(to, subject, body);
	}
}
