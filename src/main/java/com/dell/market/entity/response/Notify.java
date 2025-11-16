package com.dell.market.entity.response;


public class Notify {

	public boolean email;
    public boolean sms;
    public boolean whatsapp;
    
	public boolean isEmail() {
		return email;
	}
	public void setEmail(boolean email) {
		this.email = email;
	}
	public boolean isSms() {
		return sms;
	}
	public void setSms(boolean sms) {
		this.sms = sms;
	}
	public boolean isWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(boolean whatsapp) {
		this.whatsapp = whatsapp;
	}
    
    
}
