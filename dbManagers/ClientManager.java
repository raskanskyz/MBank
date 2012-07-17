package dbManagers;

import java.sql.ResultSet;

import dtos.ClientDto;

public interface ClientManager {

	public ClientDto selectClient(long clientId);

	void addClient(ClientDto dto);

	void removeClient(long client_id);

	void updateClient(ClientDto dto);
	
	public void getClientList();

	public ResultSet getResultSet();



}// class
