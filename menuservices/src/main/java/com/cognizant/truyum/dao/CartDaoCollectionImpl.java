package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.truyum.exception.CartEmptyException;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
@Component("cartDao")
public class CartDaoCollectionImpl implements CartDao {
	private static HashMap<String, Cart> userCarts = null;

	public CartDaoCollectionImpl() {
		super();
		if (userCarts == null) {
			userCarts = new HashMap<String, Cart>();
		}
	}

	@Override
	public void addCartItem(long userId, long menuItemId) {
		String user=Long.toString(userId);
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		if (!userCarts.containsKey(user)) {
			userCarts.put(user, new Cart(new ArrayList<MenuItem>(), 0));
		}
		List<MenuItem> menuItemList = userCarts.get(user).getMenuItemList();
		menuItemList.add(menuItem);
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		String user=Long.toString(userId);
		if (!userCarts.containsKey(user) || userCarts.get(user).getMenuItemList().isEmpty()) {
			throw new CartEmptyException();
		}
		Cart cart = userCarts.get(user);
		for (MenuItem menuItem : cart.getMenuItemList()) {
			cart.setTotal(cart.getTotal() + menuItem.getPrice());
		}
		return cart.getMenuItemList();
	}

	@Override
	public void deleteCartItem(long userId, long menuItemId) {
		List<MenuItem> menuItemList = userCarts.get(Long.toString(userId)).getMenuItemList();
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getId() == menuItemId) {
				menuItemList.remove(menuItem);
				break;
			}
		}
	}
}
