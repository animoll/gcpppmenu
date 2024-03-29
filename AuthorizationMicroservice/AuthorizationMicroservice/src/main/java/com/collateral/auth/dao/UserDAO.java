package com.collateral.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collateral.auth.model.UserData;

@Repository
public interface UserDAO extends JpaRepository<UserData, String> {

}
