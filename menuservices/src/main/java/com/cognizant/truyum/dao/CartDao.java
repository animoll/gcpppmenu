package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.exception.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;

public interface CartDao {
	public void addCartItem(long userId, long menuItemId);
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException;
	public void deleteCartItem(long userId, long menuItemId);
}
