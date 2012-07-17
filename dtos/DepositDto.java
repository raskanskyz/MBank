package dtos;

import java.sql.Timestamp;

public class DepositDto {
	String type;
	Double balance; 
	Long client_id, estimated_balance, deposit_id;
	Timestamp opening_date, closing_date;
	
	public Long getDeposit_id() {
		return deposit_id;
	}

	public void setDeposit_id(Long deposit_id) {
		this.deposit_id = deposit_id;
	}

	public Long getClient_id() {
		return client_id;
	}

	public void setClient_id(long client_id) {
		this.client_id = client_id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getEstimated_balance() {
		return estimated_balance;
	}

	public void setEstimated_balance(long estimated_balance) {
		this.estimated_balance = estimated_balance;
	}

	public Timestamp getOpening_date() {
		return opening_date;
	}

	public void setOpening_date(Timestamp opening_date) {
		this.opening_date = opening_date;
	}

	public Timestamp getClosing_date() {
		return closing_date;
	}

	public void setClosing_date(Timestamp closing_date) {
		this.closing_date = closing_date;
	}
	
	public String getAll() {
		return "deposit_id : " + deposit_id 
				+ "\nclient_id : " + client_id
				+ "\nbalance : " + balance 
				+ "\ntype : " + type
				+ "\nestimated_balance : " + estimated_balance 
				+ "\nopening_date : " + opening_date
				+ "\nclosing_date : " + closing_date;

	}// getAll
}// class

