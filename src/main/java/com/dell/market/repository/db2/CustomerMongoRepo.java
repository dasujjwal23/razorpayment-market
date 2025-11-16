package com.dell.market.repository.db2;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dell.market.entity.db2.CustomerEntity;

public interface CustomerMongoRepo extends MongoRepository<CustomerEntity, Integer>{

	@Query(value="{'email': ?0 }",fields ="{'custid': 1,'name' : 1,'contact' : 1,'email' : 1}") 
	public CustomerEntity findByEmail(@Param("email") String email);
}
