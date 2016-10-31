package org.gmjm.snapbid.domain.model;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Bid")
public class Bid
{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String user;

	private ZonedDateTime bidtime;

	private BigInteger bidUnits = BigInteger.ZERO;


	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;


	public Long getId()
	{
		return id;
	}


	public void setId(Long id)
	{
		this.id = id;
	}


	@JsonIgnore
	public ZonedDateTime getBidtime()
	{
		return bidtime;
	}

	public String getBidTime() {
		return bidtime != null ? bidtime.format(DateTimeFormatter.ISO_DATE_TIME) : null;
	}


	public void setBidtime(ZonedDateTime bidtime)
	{
		this.bidtime = bidtime;
	}


	public BigInteger getBidUnits()
	{
		return bidUnits;
	}


	public void setBidUnits(BigInteger bidUnits)
	{
		this.bidUnits = bidUnits;
	}


	public Item getItem()
	{
		return item;
	}


	public void setItem(Item item)
	{
		this.item = item;
	}


	public String getUser()
	{
		return user;
	}


	public void setUser(String user)
	{
		this.user = user;
	}

	public Currency getCurrency(){
		return item != null ? item.getCurrency() : null;
	}
}
