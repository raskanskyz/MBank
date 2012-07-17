package dtos;

//import java.sql.Date;
import java.sql.Timestamp;
//import java.util.Calendar;

public class ActivityDto {
	String  description;
	long id, client_id;
	Timestamp activity_date;
	double amount,commission;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClient_id() {
		return client_id;
	}

	public void setClient_id(long client_id) {
		this.client_id = client_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Timestamp getActivity_date() {
		return activity_date;
	}

	public void setActivity_date(Timestamp today) {
		this.activity_date = today;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getAll() {
		return "id : " + id 
				+ "\nclient_id : " + client_id
				+ "\namount : " + amount 
				+ "\nactivity_date : " + activity_date
				+ "\ncommission : " + commission 
				+ "\ndescription : " + description;
				

	}// getAll
}// class
