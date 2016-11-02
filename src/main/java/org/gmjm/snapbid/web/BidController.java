package org.gmjm.snapbid.web;

import java.math.BigInteger;
import java.time.ZonedDateTime;

import org.gmjm.snapbid.domain.listeners.BidListener;
import org.gmjm.snapbid.domain.model.Bid;
import org.gmjm.snapbid.domain.model.Item;
import org.gmjm.snapbid.domain.repository.AuctionRepository;
import org.gmjm.snapbid.domain.repository.BidRepository;
import org.gmjm.snapbid.domain.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ControllerLinkBuilderFactory;
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
	BidListener bidListener;

	@Autowired
	RepositoryEntityLinks entityLinks;

	@RequestMapping("/increaseBid{& itemId}")
	public Bid increaseBid(Long itemId) {
		Item item = itemRepository.getOne(itemId);


		Bid newBid = new Bid();
		newBid.setItem(item);
		newBid.setBidtime(ZonedDateTime.now());
		newBid.setBidUnits(item.getCurrentPrice().add(BigInteger.valueOf(300l)));
		newBid.setUser("Anon");

		bidRepository.save(newBid);

		newBid.add(entityLinks.linkToSingleResource(BidRepository.class, newBid.getBidId()));
		newBid.add(entityLinks.linkToSingleResource(ItemRepository.class, newBid.getItem().getId()));
		newBid.add(entityLinks.linkToSingleResource(AuctionRepository.class, newBid.getItem().getAuction().getId()));

		bidListener.handleBidSave(newBid);

		return newBid;
	}

}
