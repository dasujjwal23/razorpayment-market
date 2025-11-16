package com.dell.market.entity.db1;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Notes {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("pid")
	public int pid;
	
	@JsonProperty("policy_name")
	public String policy_name;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

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

	
	public Notes() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Notes [pid=" + pid + ", policy_name=" + policy_name + "]";
	}

	
}
