package dtos;

public class ClientDto {

	
	String client_name, password, type, address, email, phone, comment;
	Long  client_id;
	
	
	public Long getClient_id() {
		return client_id;
	}

	public void setClient_id(long clientId) {
		client_id = clientId;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String clientName) {
		client_name = clientName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAll() {
		return "client_id : " + client_id + "\nclient_name : " + client_name
				+ "\npassword : " + password + "\ntype : " + type
				+ "\naddress : " + address + "\nemail : " + email
				+ "\nphone : " + phone + "\ncomment : " + comment;
	}


	
}// class
