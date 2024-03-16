package com.java.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.banking.entity.Accounts;

/*this interface extends JpaRepository which allows to this interface for performing 
the CRUD operations to the entity which present in the generic **/


public interface AccountRepository extends JpaRepository<Accounts, Long>{

}
