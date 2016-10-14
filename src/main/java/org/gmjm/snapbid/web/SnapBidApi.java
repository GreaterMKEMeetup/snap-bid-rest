package org.gmjm.snapbid.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnapBidApi
{

	@RequestMapping("/")
	public String hello() {
		return "Hello World";
	}

}
