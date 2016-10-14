package org.gmjm.snapbid.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private String startingPrice;

	@ManyToOne
	@JoinColumn(name="auction_id")
	private Auction auction;


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


	public String getStartingPrice()
	{
		return startingPrice;
	}


	public void setStartingPrice(String startingPrice)
	{
		this.startingPrice = startingPrice;
	}


	public Auction getAuction()
	{
		return auction;
	}


	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}
}
