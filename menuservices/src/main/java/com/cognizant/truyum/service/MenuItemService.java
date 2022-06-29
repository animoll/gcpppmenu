package com.cognizant.truyum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.model.MenuItem;

@Service
public class MenuItemService {
	@Autowired
	MenuItemDao menuItemDao;

	public List<MenuItem> getMenuListCustomer() {

		return menuItemDao.getMenuItemListCustomer();

	}

	public MenuItem getMenuItem(long menuItemId) {
		return menuItemDao.getMenuItem(menuItemId);
	}

	public boolean modifyMenuItem(MenuItem menuItem) {
			menuItemDao.modifyMenuItem(menuItem);
			return true;
	}

}