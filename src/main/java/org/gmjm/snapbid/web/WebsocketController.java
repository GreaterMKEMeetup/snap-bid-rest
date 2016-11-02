package org.gmjm.snapbid.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController
{

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/chat")
	@SendTo("/topic/chat")
	public String greeting(Principal principal, String message) throws Exception {

		Map<String,String> details = (Map)((OAuth2Authentication)principal).getUserAuthentication().getDetails();

		return details.get("name") + " : " + message;
	}

}
