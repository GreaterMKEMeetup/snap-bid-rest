package org.gmjm.snapbid.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController
{

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/hello")
	public void greeting(String message) throws Exception {
		System.out.println("recieved: " + message);
		reply(message);
	}



	public void reply(String message) {
		simpMessagingTemplate.convertAndSend("/topic/reply",message);
	}

}
