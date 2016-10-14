package org.gmjm.snapbid.domain.model;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Auction")
public class Auction
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String auctionName;
	private String description;
	private String location;
	private ZonedDateTime startDateTime;
	private ZonedDateTime endDateTime;

	@OneToMany(mappedBy = "auction")
	private List<Item> auctionItems;

	public Long getId()
	{
		return id;
	}


	public void setId(Long id)
	{
		this.id = id;
	}


	public String getAuctionName()
	{
		return auctionName;
	}


	public void setAuctionName(String auctionName)
	{
		this.auctionName = auctionName;
	}


	public String getDescription()
	{
		return description;
	}


	public void setDescription(String description)
	{
		this.description = description;
	}


	public String getLocation()
	{
		return location;
	}


	public void setLocation(String location)
	{
		this.location = location;
	}

	@JsonIgnore
	public ZonedDateTime getStartDateTime()
	{
		return startDateTime;
	}


	public String getAuctionStart() {
		return startDateTime != null ? startDateTime.toString() : null;
	}

	public void setStartDateTime(ZonedDateTime startDateTime)
	{
		this.startDateTime = startDateTime;
	}

	@JsonIgnore
	public ZonedDateTime getEndDateTime()
	{
		return endDateTime;
	}

	public String getAuctionEnd() {
		return endDateTime != null ? endDateTime.toString() : null;
	}


	public void setEndDateTime(ZonedDateTime endDateTime)
	{
		this.endDateTime = endDateTime;
	}


	public List<Item> getAuctionItems()
	{
		return auctionItems;
	}


	public void setAuctionItems(List<Item> auctionItems)
	{
		this.auctionItems = auctionItems;
	}
}
