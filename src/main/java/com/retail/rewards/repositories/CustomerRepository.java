package com.retail.rewards.repositories;


import com.retail.rewards.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {

}
