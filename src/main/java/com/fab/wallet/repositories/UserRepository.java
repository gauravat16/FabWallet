package com.fab.wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fab.wallet.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
