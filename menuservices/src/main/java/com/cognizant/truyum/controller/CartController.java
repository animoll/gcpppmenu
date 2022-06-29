package com.cognizant.truyum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.truyum.exception.AuthorizationException;
import com.cognizant.truyum.exception.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
	@Autowired
	CartService cartService;
	
	@PostMapping("/{userId}/{menuItemId}")
	//pending validation
	public void addCartItem(@PathVariable long userId, @PathVariable long menuItemId,
			@RequestHeader(value = "Authorization", required = true)String requestTokenHeader) throws AuthorizationException {
		cartService.addCartItem(userId, menuItemId);
	}
	@GetMapping("/{userId}")
	public List<MenuItem> getAllCartItems(@PathVariable long userId,
			@RequestHeader(value = "Authorization", required = true)String requestTokenHeader) throws AuthorizationException, CartEmptyException {
		return cartService.getAllCartItems(userId);
	}
	@DeleteMapping("/{userId}/{menuItemId}")
	public void deleteCartItem(@PathVariable long userId, @PathVariable long menuItemId,
			@RequestHeader(value = "Authorization", required = true)String requestTokenHeader) throws AuthorizationException {
		cartService.deleteCartItem(userId, menuItemId);
	}
}
