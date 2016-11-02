package org.gmjm.snapbid.domain.listeners;

import org.aspectj.lang.annotation.After;
import org.gmjm.snapbid.domain.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Bid.class)
public class BidListener {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@HandleAfterCreate
	public void handleBidSave(Bid b) {

		simpMessagingTemplate.convertAndSend("/topic/bids",b);
	}

}
