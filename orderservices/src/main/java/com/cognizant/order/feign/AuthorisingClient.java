package com.cognizant.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognizant.order.model.AuthResponse;




@FeignClient(name = "Authorizatiion-Microservice", url = "${auth.url}")

public interface AuthorisingClient {

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") final String token);
}