package org.gmjm.snapbid.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController
{

	@MessageMapping("/bids")
	@SendTo("/topic/bids")
	public String greeting(String message) throws Exception {

		return "Chat: " + message;
	}

}
