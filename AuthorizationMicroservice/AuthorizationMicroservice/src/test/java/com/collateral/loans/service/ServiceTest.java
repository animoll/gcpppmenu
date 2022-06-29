package com.collateral.loans.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.collateral.auth.dao.UserDAO;
import com.collateral.auth.model.UserData;
import com.collateral.auth.service.CustomerDetailsService;


@SpringBootTest
public class ServiceTest {

	UserDetails userdetails;
	
	@InjectMocks
	CustomerDetailsService custdetailservice;

	@Mock
	UserDAO userservice;

	@Test
	public void loadUserByUsernameTest() {
		
		UserData user1=new UserData("admin","admin","admin",null);
		Optional<UserData> data =Optional.of(user1) ;
		when(userservice.findById("admin")).thenReturn(data);
		UserDetails loadUserByUsername2 = custdetailservice.loadUserByUsername("admin");
		assertEquals(user1.getUserid(),loadUserByUsername2.getUsername());
	}

	
}
