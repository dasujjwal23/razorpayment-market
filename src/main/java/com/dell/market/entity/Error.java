package com.dell.market.entity;

import java.util.List;

public class Error {

	public String code;
    public String description;
    public List<String> metadata;
    public String reason;
    public String source;
    public String step;
    
    
    
	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public List<String> getMetadata() {
		return metadata;
	}



	public void setMetadata(List<String> metadata) {
		this.metadata = metadata;
	}



	public String getReason() {
		return reason;
	}



	public void setReason(String reason) {
		this.reason = reason;
	}



	public String getSource() {
		return source;
	}



	public void setSource(String source) {
		this.source = source;
	}



	public String getStep() {
		return step;
	}



	public void setStep(String step) {
		this.step = step;
	}



	@Override
	public String toString() {
		return "Error [code=" + code + ", description=" + description + ", metadata=" + metadata + ", reason=" + reason
				+ ", source=" + source + ", step=" + step + "]";
	}
    
    
}
