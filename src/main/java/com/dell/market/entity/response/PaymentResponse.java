package com.dell.market.entity.response;

import java.io.Serializable;
import java.util.List;

public class PaymentResponse{

	public boolean accept_partial;
    public int amount;
    public int amount_paid;
    public String callback_method;
    public String callback_url;
    public int cancelled_at;
    public int created_at;
    public String currency;
    public Customer customer;
    public String description;
    public int expire_by;
    public int expired_at;
    public int first_min_partial_amount;
    public String id;
    public Notes notes;
    public Notify notify;
    public Object payments;
    public String reference_id;
    public boolean reminder_enable;
    public List<String> reminders;
    public String short_url;
    public String status;
    public int updated_at;
    public boolean upi_link;
    public String user_id;
    public boolean whatsapp_link;
    
	public boolean isAccept_partial() {
		return accept_partial;
	}
	public void setAccept_partial(boolean accept_partial) {
		this.accept_partial = accept_partial;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(int amount_paid) {
		this.amount_paid = amount_paid;
	}
	public String getCallback_method() {
		return callback_method;
	}
	public void setCallback_method(String callback_method) {
		this.callback_method = callback_method;
	}
	public String getCallback_url() {
		return callback_url;
	}
	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}
	public int getCancelled_at() {
		return cancelled_at;
	}
	public void setCancelled_at(int cancelled_at) {
		this.cancelled_at = cancelled_at;
	}
	public int getCreated_at() {
		return created_at;
	}
	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getExpire_by() {
		return expire_by;
	}
	public void setExpire_by(int expire_by) {
		this.expire_by = expire_by;
	}
	public int getExpired_at() {
		return expired_at;
	}
	public void setExpired_at(int expired_at) {
		this.expired_at = expired_at;
	}
	public int getFirst_min_partial_amount() {
		return first_min_partial_amount;
	}
	public void setFirst_min_partial_amount(int first_min_partial_amount) {
		this.first_min_partial_amount = first_min_partial_amount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Notes getNotes() {
		return notes;
	}
	public void setNotes(Notes notes) {
		this.notes = notes;
	}
	public Notify getNotify() {
		return notify;
	}
	public void setNotify(Notify notify) {
		this.notify = notify;
	}
	public Object getPayments() {
		return payments;
	}
	public void setPayments(Object payments) {
		this.payments = payments;
	}
	public String getReference_id() {
		return reference_id;
	}
	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}
	public boolean isReminder_enable() {
		return reminder_enable;
	}
	public void setReminder_enable(boolean reminder_enable) {
		this.reminder_enable = reminder_enable;
	}
	public List<String> getReminders() {
		return reminders;
	}
	public void setReminders(List<String> reminders) {
		this.reminders = reminders;
	}
	public String getShort_url() {
		return short_url;
	}
	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(int updated_at) {
		this.updated_at = updated_at;
	}
	public boolean isUpi_link() {
		return upi_link;
	}
	public void setUpi_link(boolean upi_link) {
		this.upi_link = upi_link;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public boolean isWhatsapp_link() {
		return whatsapp_link;
	}
	public void setWhatsapp_link(boolean whatsapp_link) {
		this.whatsapp_link = whatsapp_link;
	}
	public PaymentResponse(boolean accept_partial, int amount, int amount_paid, String callback_method,
			String callback_url, int cancelled_at, int created_at, String currency, Customer customer,
			String description, int expire_by, int expired_at, int first_min_partial_amount, String id, Notes notes,
			Notify notify, Object payments, String reference_id, boolean reminder_enable, List<String> reminders,
			String short_url, String status, int updated_at, boolean upi_link, String user_id, boolean whatsapp_link) {
		super();
		this.accept_partial = accept_partial;
		this.amount = amount;
		this.amount_paid = amount_paid;
		this.callback_method = callback_method;
		this.callback_url = callback_url;
		this.cancelled_at = cancelled_at;
		this.created_at = created_at;
		this.currency = currency;
		this.customer = customer;
		this.description = description;
		this.expire_by = expire_by;
		this.expired_at = expired_at;
		this.first_min_partial_amount = first_min_partial_amount;
		this.id = id;
		this.notes = notes;
		this.notify = notify;
		this.payments = payments;
		this.reference_id = reference_id;
		this.reminder_enable = reminder_enable;
		this.reminders = reminders;
		this.short_url = short_url;
		this.status = status;
		this.updated_at = updated_at;
		this.upi_link = upi_link;
		this.user_id = user_id;
		this.whatsapp_link = whatsapp_link;
	}
	public PaymentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PaymentResponse [accept_partial=" + accept_partial + ", amount=" + amount + ", amount_paid="
				+ amount_paid + ", callback_method=" + callback_method + ", callback_url=" + callback_url
				+ ", cancelled_at=" + cancelled_at + ", created_at=" + created_at + ", currency=" + currency
				+ ", customer=" + customer + ", description=" + description + ", expire_by=" + expire_by
				+ ", expired_at=" + expired_at + ", first_min_partial_amount=" + first_min_partial_amount + ", id=" + id
				+ ", notes=" + notes + ", notify=" + notify + ", payments=" + payments + ", reference_id="
				+ reference_id + ", reminder_enable=" + reminder_enable + ", reminders=" + reminders + ", short_url="
				+ short_url + ", status=" + status + ", updated_at=" + updated_at + ", upi_link=" + upi_link
				+ ", user_id=" + user_id + ", whatsapp_link=" + whatsapp_link + "]";
	}
    
    
    
}
