package com.higradius;

public class Response {
	private String name_customer;
	private String cust_number;
	private String FIELD1;
	private String predicted_clear_date;
	private String due_in_date;
	private String invoice_id;
	private String total_open_amount;
	private String notes;
	private String executionstatus;
	private String executionmessage;
	private String notesdata;
	
	public String getNotesdata() {
		return notesdata;
	}
	public void setNotesdata(String notesdata) {
		this.notesdata = notesdata;
	}
	public String getExecutionmessage() {
		return executionmessage;
	}
	public void setExecutionmessage(String executionmessage) {
		this.executionmessage = executionmessage;
	}
	public String getExecutionstatus() {
		return executionstatus;
	}
	public void setExecutionstatus(String executionstatus) {
		this.executionstatus = executionstatus;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getName_customer() {
		return name_customer;
	}
	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
	}
	public String getCust_number() {
		return cust_number;
	}
	public void setCust_number(String cust_number) {
		this.cust_number = cust_number;
	}
	public String getFIELD1() {
		return FIELD1;
	}
	public void setFIELD1(String fIELD1) {
		FIELD1 = fIELD1;
	}
	public String getPredicted_clear_date() {
		return predicted_clear_date;
	}
	public void setPredicted_clear_date(String predicted_clear_date) {
		this.predicted_clear_date = predicted_clear_date;
	}
	public String getDue_in_date() {
		return due_in_date;
	}
	public void setDue_in_date(String due_in_date) {
		this.due_in_date = due_in_date;
	}
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String getTotal_open_amount() {
		return total_open_amount;
	}
	public void setTotal_open_amount(String total_open_amount) {
		this.total_open_amount = total_open_amount;
	}


}
