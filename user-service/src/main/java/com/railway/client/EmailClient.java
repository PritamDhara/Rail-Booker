package com.railway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.railway.errordecoder.CustomErrorDecoder;

@FeignClient(url = "http://localhost:5050/mail",name = "mail-service",configuration = CustomErrorDecoder.class)
public interface EmailClient {
	
	@GetMapping("/sendMail/{to}/{subject}/{body}")
	public String sendMail(@PathVariable String to,@PathVariable String subject,@PathVariable String body);
	
}
