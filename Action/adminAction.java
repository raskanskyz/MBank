package Action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Concrete.MBankST;
import DB.dbMngST;
import dbManagers.*;
import dtos.*;

public class adminAction extends Action {

	// ---------C-r-e-a-t-e--N-e-w--C-l-i-e-n-t--R-e-a-d-y------
	public boolean addNewClient(ClientDto dto, double initialDeposit) {// <-------client must make a deposit
		int ID = 0;
		String clientType;
		double creditLimit;
		double regular = Double.valueOf(MBankST.m.get("regular_deposit_rate").replace("$", ""));//<------extracts valueOf regular deposit rate from Properties Map
		double gold = Double.valueOf(MBankST.m.get("gold_deposit_rate").replace("$", ""));//<------extracts valueOf gold deposit rate from Properties Map
		final String queryStr = "select LAST_INSERT_ID()";
		if (initialDeposit <= regular) {														//This checks the first deposit(initialDeposit)
			clientType = "regular";																//made by the client and and sets
		}// if regular																			//his client type(regular, gold, platinum)
																								//		
		else if (initialDeposit < gold) {														//
																								//
			clientType = "gold";																//
		}// else if gold																		//
																								//
		else {																					//
			clientType = "platinum";															//
		}// else platinum																		//
																								//
		if (clientType.equals("regular")) {														//
			creditLimit = regular;																//
		} else if (clientType.equals("gold")) {													//
			creditLimit = gold;																	//
		} else {																				//
			creditLimit = 1000000000;														//
		}

		ClientDto d1 = new ClientDto();
		AccountDto a1 = new AccountDto();
		ClientManager cm = new ClientDBManager();
		AccountManager am = new AccountDBManager();

		d1.setClient_name(dto.getClient_name());
		d1.setPassword(dto.getPassword());
		d1.setType(clientType);
		d1.setAddress(dto.getAddress());
		d1.setEmail(dto.getEmail());
		d1.setPhone(dto.getPhone());
		d1.setComment(dto.getComment());
		cm.addClient(d1);

		try {																					//This code extracts the last PK inserted into
			Statement stmt = dbMngST.instance().getCon().createStatement();						//the Schema and stores it in int = ID
																								//
			ResultSet rs = stmt.executeQuery(queryStr);											//
																								//
			if (rs.next())																		//
																								//
			{																					//
																								//
				ID = rs.getInt(1);																//
																								//
			}																					//
		} catch (SQLException e) {																//
																								//
			e.printStackTrace();																//
		}																						//

		a1.setBalance(initialDeposit);
		a1.setClient_id(ID);
		a1.setCredit_limit(creditLimit);
		if(ID!=0)
		{
			am.addAccount(a1);
			return true;
							}//<-----Creates an account only if new client was successfully registered
		else{return false;}
		
		 

	} // addNewClient

	// ---------C-r-e-a-t-e--N-e-w--C-l-i-e-n-t--R-e-a-d-y------

	// ---------R-e-m-o-v-e--C-l-i-e-n-t--R-e-a-d-y------
	public void removeClient(ClientDto dto) {
		ClientManager cm = new ClientDBManager();
		cm.removeClient(dto.getClient_id());
	} // removeClient

	// ---------R-e-m-o-v-e--C-l-i-e-n-t--R-e-a-d-y------

	// ---------C-r-e-a-t-e--N-e-w--A-c-c-o-u-n-t-------
	public void addNewAccount(AccountDto dto) {
		AccountDto adt = new AccountDto();
		AccountManager am = new AccountDBManager();
		adt.setAccount_id(dto.getAccount_id());
		adt.setClient_id(dto.getClient_id());
		adt.setBalance(dto.getBalance());
		adt.setCredit_limit(dto.getCredit_limit());
		adt.setComment(dto.getComment());
		am.addAccount(adt);
	}// viewAccountDetails

	// ---------C-r-e-a-t-e--N-e-w--A-c-c-o-u-n-t-------

	// ---------R-e-m-o-v-e--A-c-c-o-u-n-t--R-e-a-d-y------
	public void removeAccount(AccountDto dto) {
		AccountManager am = new AccountDBManager();
		am.removeAccount(dto.getAccount_id());
	} // removeAccount
	// ---------R-e-m-o-v-e--A-c-c-o-u-n-t--R-e-a-d-y------

	// -----------v-i-e-w--A-l-l--C-l-i-e-n-t-s--D-e-t-a-i-l-s--R-e-a-d-y----
	public void viewAllClientsDetails() {

		ClientManager cm = new ClientDBManager();
		cm.getClientList();
	}// viewAllClientsDetails

	// -----------v-i-e-w--A-l-l--C-l-i-e-n-t--D-e-t-a-i-l-s--R-e-a-d-y------

	// -----------v-i-e-w--A-l-l--A-c-c-u-n-t-s--D-e-t-a-i-l-s--R-e-a-d-y------
	public void viewAllAccountsDetails() {
		AccountManager am = new AccountDBManager();
		am.getAccountList();
	}// viewAllAccountsDetails

	// -----------v-i-e-w--A-l-l--A-c-c-u-n-t-s--D-e-t-a-i-l-s--R-e-a-d-y------

	// -----------v-i-e-w--A-l-l--D-e-p-o-s-i-t-s--D-e-t-a-i-l-s--R-e-a-d-y------
	public void viewAllDepositsDetails() {
		DepositManager dm = new DepositDBManager();
		dm.getDepositList();
		
	}// viewAllDepositsDetails

	// -----------v-i-e-w--A-l-l--D-e-p-o-s-i-t-s--D-e-t-a-i-l-s--R-e-a-d-y------

	// -----------v-i-e-w--A-l-l--A-c-t-i-v-t-i-e-s--D-e-t-a-i-l-s--R-e-a-d-y------

	public void viewAllActivitiesDetails() {

		ActivityManager acm = new ActivityDBManeger();
		acm.getActivityList();

	}// viewAllActivitiesDetails

	// -----------v-i-e-w--A-l-l--A-c-t-i-v-t-i-e-s--D-e-t-a-i-l-s--R-e-a-d-y------

	// ---------u-p-d-a-t-e--S-y-s-t-e-m--P-r-o-p-e-r-t-y--R-e-a-d-y------
	public void updateSystemProperty(PropertiesDto dto) {
		PropertiesDto pm1 = new PropertiesDto();
		PropertiesManager pm = new PropertiesDBManager();
		pm1.setProp_key(dto.getProp_key());
		pm1.setProp_value(dto.getProp_value());
		pm.updateProperties(pm1);
	} // updateClientDetails
	// ---------u-p-d-a-t-e--S-y-s-t-e-m--P-r-o-p-e-r-t-y--R-e-a-d-y------

	@Override
	public void updateClientDetails(ClientDto dto) {		
		ClientManager cm = new ClientDBManager();
		ClientDto d1 = cm.selectClient(dto.getClient_id());
		d1.setClient_id(dto.getClient_id());
		d1.setClient_name(dto.getClient_name());
		d1.setPassword(dto.getPassword());
		d1.setType(dto.getType());
		d1.setAddress(dto.getAddress());
		d1.setEmail(dto.getEmail());
		d1.setPhone(dto.getPhone());
		d1.setComment(dto.getComment());
		cm.updateClient(dto);
		
	}

}// class
