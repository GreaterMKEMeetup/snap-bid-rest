package org.gmjm.snapbid.domain.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Item")
public class Item
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String description;
	private String imageUrl;
	private BigInteger startingPriceUnits;
	private BigInteger minimumBidIncrease = BigInteger.TEN;

	@Transient
	private Currency currency = Currency.getInstance("USD");

	@ManyToOne
	@JoinColumn(name="auction_id")
	private Auction auction;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
	private List<Bid> bids = new ArrayList<>();


	public Long getId()
	{
		return id;
	}


	public void setId(Long id)
	{
		this.id = id;
	}


	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getDescription()
	{
		return description;
	}


	public void setDescription(String description)
	{
		this.description = description;
	}


	public String getImageUrl()
	{
		return imageUrl;
	}


	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}


	public BigInteger getStartingPriceUnits()
	{
		return startingPriceUnits;
	}


	public void setStartingPriceUnits(BigInteger startingPriceUnits)
	{
		this.startingPriceUnits = startingPriceUnits;
	}


	public Auction getAuction()
	{
		return auction;
	}


	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}


	public List<Bid> getBids()
	{
		return bids;
	}


	public void setBids(List<Bid> bids)
	{
		this.bids = bids;
	}


	public Currency getCurrency()
	{
		return currency;
	}


	public BigInteger getMinimumBidIncrease()
	{
		return minimumBidIncrease;
	}


	public void setMinimumBidIncrease(BigInteger minimumBidIncrease)
	{
		this.minimumBidIncrease = minimumBidIncrease;
	}


	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}


	/**
	 *
	 * @return The lastest bid price, or the starting price if no bids have been received.
	 */
	@JsonIgnore
	public BigInteger getCurrentPrice() {

		if(bids == null || bids.isEmpty()) {
			return startingPriceUnits;
		} else {
			List<BigInteger> bidPrices = bids.stream()
				.map(bid -> bid.getBidUnits())
				.collect(Collectors.toList());

			return Collections.max(bidPrices);

		}

	}
}
