package com.dell.market.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Notes {

	@JsonIgnore
	public int pid;
	
	@JsonProperty("policy_name")
	public String policy_name;

	
	public String getPolicy_name() {
		return policy_name;
	}

	public void setPolicy_name(String policy_name) {
		this.policy_name = policy_name;
	}

	public Notes(String policy_name) {
		super();
		this.policy_name = policy_name;
	}

	@Override
	public String toString() {
		return "Notes [policy_name=" + policy_name + "]";
	}
	
	
}
