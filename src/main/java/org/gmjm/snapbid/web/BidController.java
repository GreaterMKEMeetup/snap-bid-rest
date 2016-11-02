package org.gmjm.snapbid.web;

import java.math.BigInteger;
import java.time.ZonedDateTime;

import org.gmjm.snapbid.domain.model.Bid;
import org.gmjm.snapbid.domain.model.Item;
import org.gmjm.snapbid.domain.repository.BidRepository;
import org.gmjm.snapbid.domain.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidController
{

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private BidRepository bidRepository;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@RequestMapping("/increaseBid{& itemId}")
	public String increaseBid(Long itemId) {
		Item item = itemRepository.getOne(itemId);


		Bid newBid = new Bid();
		newBid.setItem(item);
		newBid.setBidtime(ZonedDateTime.now());
		newBid.setBidUnits(item.getCurrentPrice().add(BigInteger.valueOf(300l)));
		newBid.setUser("Anon");

		bidRepository.save(newBid);

		simpMessagingTemplate.convertAndSend("/topic/bids",String.format("%s : %s",item.getName(), newBid.getBidUnits()));

		return "success";
	}

}
