package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;
@Component("menuItemDao")
public class MenuItemDaoCollectionImpl implements MenuItemDao {
	private static List<MenuItem> menuItemList = null;

	public MenuItemDaoCollectionImpl() {
		super();
		ApplicationContext context = new ClassPathXmlApplicationContext("truyum.xml");
		try {
			if (menuItemList == null) {
				menuItemList = (ArrayList) context.getBean("menuItemList");
			}
		} catch (Exception e) {
		}
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		ArrayList<MenuItem> arrayList = new ArrayList<MenuItem>();
		Date currdate = null;
		try {
			currdate = DateUtil.convertToDate("16/04/2020");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (MenuItem menuItem : menuItemList) {
			if (currdate.compareTo(menuItem.getDateOfLaunch()) >= 0 && menuItem.isActive()) {
				arrayList.add(menuItem);
			}
		}
		return arrayList;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		for (MenuItem menuItems : menuItemList) {
			if (menuItem.getId() == menuItems.getId()) {
				menuItems.setName(menuItem.getName());
				menuItems.setActive(menuItem.isActive());
				menuItems.setFreeDelivery(menuItem.isFreeDelivery());
				menuItems.setCategory(menuItem.getCategory());
				menuItems.setPrice(menuItem.getPrice());
				menuItems.setDateOfLaunch(menuItem.getDateOfLaunch());
				break;
			}
		}

	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		for (MenuItem menuItem : menuItemList) {
			if(menuItem.getId()==menuItemId) {
				return menuItem;
			}
		}
		return null;
	}
}
