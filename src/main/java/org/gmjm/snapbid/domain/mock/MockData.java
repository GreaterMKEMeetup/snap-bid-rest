package org.gmjm.snapbid.domain.mock;

import java.time.ZonedDateTime;
import java.util.Random;

import org.gmjm.snapbid.domain.model.Auction;
import org.gmjm.snapbid.domain.model.Item;
import org.gmjm.snapbid.domain.repository.ItemRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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

	private Random r = new Random();

	@Override
	public void afterPropertiesSet() throws Exception
	{



		Lorem lorem = LoremIpsum.getInstance();

		for(int i = 0; i < 20; i++){
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


	}


	private void createItems(String fname, Auction a)
	{
		for(int i = 0; i < 20; i++) {
			Item item = new Item();
			item.setAuction(a);
			item.setName(fname + "'s really cool thing #" + i);
			item.setDescription("It's cool I swear.");
			int dollars = (int)Math.floor(r.nextDouble() * 1000);
			int cents = (int)Math.floor(r.nextDouble() * 100);
			item.setStartingPrice(String.format("%s.%s",dollars,cents));
			itemRepository.save(item);

		}
	}
}
