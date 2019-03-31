package com.fab.wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fab.wallet.entities.Transaction;
import com.fab.wallet.entities.Wallet;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
