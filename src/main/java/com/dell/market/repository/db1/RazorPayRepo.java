package com.dell.market.repository.db1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dell.market.entity.db1.Customer;
import com.dell.market.entity.db1.PaymentRequest;

@Repository
public interface RazorPayRepo extends JpaRepository<PaymentRequest, Integer>{

	@Query("SELECT p FROM PaymentRequest p WHERE p.reference_id = :reference_id")
	PaymentRequest findByReferenceId(@Param("reference_id") String reference_id);
	
	@Query("SELECT new com.dell.market.entity.db1.Customer(c.name,c.contact,c.email) FROM Customer c WHERE c.cid = :customer_cid")
	Customer findByCid(@Param("customer_cid") int customer_cid);
		
}
