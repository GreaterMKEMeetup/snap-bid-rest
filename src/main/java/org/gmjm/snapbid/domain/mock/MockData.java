package org.gmjm.snapbid.domain.mock;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Random;

import org.gmjm.snapbid.domain.model.Auction;
import org.gmjm.snapbid.domain.model.AuctionWatch;
import org.gmjm.snapbid.domain.model.Bid;
import org.gmjm.snapbid.domain.model.Item;
import org.gmjm.snapbid.domain.repository.AuctionWatchRepository;
import org.gmjm.snapbid.domain.repository.BidRepository;
import org.gmjm.snapbid.domain.repository.ItemRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import org.gmjm.snapbid.domain.repository.AuctionRepository;
import org.springframework.stereotype.Controller;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

@Controller
@Profile("mock")
public class MockData implements InitializingBean
{

	@Autowired
	private AuctionRepository auctionRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private BidRepository bidRepository;

	@Autowired
	private AuctionWatchRepository auctionWatchRepository;

	private Random r = new Random();

	Lorem lorem = LoremIpsum.getInstance();

	@Override
	public void afterPropertiesSet() throws Exception
	{
		for(int i = 0; i < 100; i++){
			Auction a = new Auction();
			String fname = lorem.getFirstName();
			a.setAuctionName(fname + "'s Auction");
			a.setDescription("A pretty cool auction full of " + fname + "'s junk.");
			a.setLocation(String.format("%s, %s, %s",lorem.getCity(),lorem.getStateAbbr(), lorem.getZipCode()));
			int rand = (int)Math.floor(r.nextDouble() * 20);
			int rand2 = (int)Math.floor(r.nextDouble() * 5);
			a.setStartDateTime(ZonedDateTime.now().plusDays(rand));
			a.setEndDateTime(ZonedDateTime.now().plusDays(rand+rand2));
			auctionRepository.save(a);

			createItems(fname,a);
		}

		{
			AuctionWatch auctionWatch = new AuctionWatch();

			Auction toWatch = new Auction();
			toWatch.setId(1l);

			auctionWatch.setAuction(toWatch);
			auctionWatch.setUserId("aglassman");
			auctionWatchRepository.save(auctionWatch);
		}

		{
			AuctionWatch auctionWatch = new AuctionWatch();

			Auction toWatch = new Auction();
			toWatch.setId(2l);

			auctionWatch.setAuction(toWatch);
			auctionWatch.setUserId("steve");
			auctionWatchRepository.save(auctionWatch);
		}

	}


	private void createItems(String fname, Auction a)
	{
		for(int i = 0; i < 20; i++) {
			Item item = new Item();
			item.setAuction(a);
			item.setName(fname + "'s really cool thing #" + i);
			item.setDescription("It's cool I swear.");
			item.setStartingPriceUnits(new BigInteger(8,r));
			item.setMinimumBidIncrease(BigInteger.TEN);
			itemRepository.save(item);
			createBids(item);

		}
	}

	private void createBids(Item item) {

		ZonedDateTime bidTime = item.getAuction().getStartDateTime();

		int bids = (int)Math.floor(r.nextDouble() * 20);

		for(int i = 0; i < bids; i++) {
			Bid bid = new Bid();
			bid.setItem(item);

			bidTime = bidTime.plusMinutes((int)Math.floor(r.nextDouble() * 20));
			bid.setBidtime(bidTime);

			while(bid.getBidUnits().compareTo(item.getMinimumBidIncrease()) <= 0)
			{
				bid.setBidUnits(item.getCurrentPrice().add(new BigInteger(8,r)));
			}

			bid.setUser(lorem.getEmail());
			bidRepository.save(bid);
			item.getBids().add(bid);
		}
	}
}
