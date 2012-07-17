package dbManagers;

import java.sql.ResultSet;

import dtos.AccountDto;

public interface AccountManager {
	
	AccountDto selectAccount(long account_id);

	void addAccount(AccountDto dto);

	void removeAccount(long account_id);

	void updateAccount(AccountDto dto);

	void getAccountList();

	AccountDto selectAccountByClientId(long client_id);

	ResultSet getResultSet(); 
}// class
