package org.gmjm.snapbid.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AuctionWatch")
public class AuctionWatch
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name="auction_id")
	private Auction auction;

	private String userId;


	public Long getId()
	{
		return id;
	}


	public void setId(Long id)
	{
		this.id = id;
	}


	public Auction getAuction()
	{
		return auction;
	}


	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}


	public String getUserId()
	{
		return userId;
	}


	public void setUserId(String userId)
	{
		this.userId = userId;
	}
}
