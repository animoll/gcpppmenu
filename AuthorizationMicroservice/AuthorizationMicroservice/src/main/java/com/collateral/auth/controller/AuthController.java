package com.collateral.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collateral.auth.dao.UserDAO;
import com.collateral.auth.model.AuthResponse;
import com.collateral.auth.model.UserData;
import com.collateral.auth.service.CustomerDetailsService;
import com.collateral.auth.service.JwtUtil;

@RestController
//@RequestMapping("/authapp")
public class AuthController {

	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private CustomerDetailsService custdetailservice;
	@Autowired
	private UserDAO userservice;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@RequestMapping(path = "/health", method = RequestMethod.GET)
	public ResponseEntity<?> healthCheckup() {
		LOGGER.info("AWS Health Check ");
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserData> login(@RequestBody UserData userlogincredentials) throws Exception{

		final UserDetails userdetails = custdetailservice.loadUserByUsername(userlogincredentials.getUserid());
		String uid = "";
		String generateToken = "";
		if (userdetails.getPassword().equals(userlogincredentials.getUpassword())) {
			uid = userlogincredentials.getUserid();
			generateToken = jwtutil.generateToken(userdetails);
			return new ResponseEntity<UserData>(new UserData(uid, null, null, generateToken), HttpStatus.OK);
		} else {
			LOGGER.info("At Login : ");
			LOGGER.error("Not Accesible");
			//return new ResponseEntity<>("Not Accesible", HttpStatus.FORBIDDEN);
			throw new Exception("credentials invalid");
		}
	}

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") final String token) throws Exception{
		String token1 = token.substring(7);
		AuthResponse res = new AuthResponse();
		if (jwtutil.validateToken(token1)) {
			res.setUid(jwtutil.extractUsername(token1));
			res.setValid(true);
			res.setName(userservice.findById(jwtutil.extractUsername(token1)).get().getUname());
			return new ResponseEntity<AuthResponse>(res, HttpStatus.OK);
		} else {
			//res.setValid(false);
			LOGGER.info("At Validity : ");
			LOGGER.error("Token Has Expired");
			throw new Exception("toekn invalid");
		}
		
	}

}
