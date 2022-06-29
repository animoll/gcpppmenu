package com.collateral.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.collateral.auth.dao.UserDAO;
import com.collateral.auth.model.UserData;

@Service
public class CustomerDetailsService implements UserDetailsService {
	@Autowired
	private UserDAO userdao;

	@Override
	public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
		UserData custuser = userdao.findById(uid).orElse(null);
		return new User(custuser.getUserid(), custuser.getUpassword(), new ArrayList<>());
	}

}
