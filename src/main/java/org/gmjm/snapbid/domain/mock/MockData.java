package org.gmjm.snapbid.domain.mock;

import java.time.ZonedDateTime;

import org.gmjm.snapbid.domain.model.Auction;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.gmjm.snapbid.domain.repository.AuctionRepository;
import org.springframework.stereotype.Controller;

@Controller
@Profile("mock")
public class MockData implements InitializingBean
{

	@Autowired
	private AuctionRepository auctionRepository;


	@Override
	public void afterPropertiesSet() throws Exception
	{
		{
			Auction a = new Auction();
			a.setAuctionName("My First Auction");
			a.setDescription("A pretty cool auction.");
			a.setLocation("My parent's basement.");
			a.setStartDateTime(ZonedDateTime.now());
			a.setEndDateTime(ZonedDateTime.now().plusDays(3));
			auctionRepository.save(a);
		}

		{
			Auction a = new Auction();
			a.setAuctionName("My Second Auction");
			a.setDescription("A garage sale.");
			a.setLocation("West Allis");
			a.setStartDateTime(ZonedDateTime.now().plusDays(1));
			a.setEndDateTime(ZonedDateTime.now().plusDays(4));
			auctionRepository.save(a);
		}

		{
			Auction a = new Auction();
			a.setAuctionName("Comics Galore");
			a.setDescription("Nerd Alert.");
			a.setLocation("ComicCon.");
			a.setStartDateTime(ZonedDateTime.now().plusDays(2));
			a.setEndDateTime(ZonedDateTime.now().plusDays(5));
			auctionRepository.save(a);
		}
	}
}
