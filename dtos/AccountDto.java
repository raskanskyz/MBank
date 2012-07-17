package dtos;

public class AccountDto {
	 
	Long client_id, account_id;
	String  comment;
	Double credit_limit,balance;

	public Long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}

	public Long getClient_id() {
		return client_id;
	}

	public void setClient_id(long client_id) {
		this.client_id = client_id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getCredit_limit() {
		return credit_limit;
	}

	public void setCredit_limit(double credit_limit) {
		this.credit_limit = credit_limit;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getAll() {
		return "account_id : " + account_id 
				+ "\nclient_id : " + client_id
				+ "\nbalance : " + balance 
				+ "\ncredit_limit : " + credit_limit
				+ "\ncomment : " + comment;

	}// getAll
}// class

	
