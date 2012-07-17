package Action;

import java.util.List;

import dbManagers.AccountDBManager;
import dbManagers.AccountManager;
import dbManagers.ActivityDBManeger;
import dbManagers.ActivityManager;
import dbManagers.ClientDBManager;
import dbManagers.ClientManager;
import dbManagers.DepositDBManager;
import dbManagers.DepositManager;
import dbManagers.PropertiesDBManager;
import dbManagers.PropertiesManager;
import dtos.AccountDto;
import dtos.ActivityDto;
import dtos.ClientDto;
import dtos.DepositDto;
import dtos.PropertiesDto;

public abstract class Action {

	// ---------u-p-d-a-t-e--C-l-i-e-n-t--D-e-t-a-i-l-s--R-e-a-d-y------
	public void updateClientDetails(ClientDto dto) {
		ClientManager cm = new ClientDBManager();
		ClientDto d1 = cm.selectClient(dto.getClient_id());
		d1.setClient_id(dto.getClient_id());
		d1.setClient_name(dto.getClient_name());
		d1.setPassword(dto.getPassword());
		d1.getType();// <--------only Admin can update this
		d1.setAddress(dto.getAddress());
		d1.setEmail(dto.getEmail());
		d1.setPhone(dto.getPhone());
		d1.getComment();// <--------only Admin can update this
		cm.updateClient(d1);

	} // updateClientDetails

	// ---------u-p-d-a-t-e--C-l-i-e-n-t--D-e-t-a-i-l-s--R-e-a-d-y------

	// ------------v-i-e-w--C-l-i-e-n-t--D-e-t-a-i-l-s--R-e-a-d-y-------
	public ClientDto viewClientDetails(long client_id) {
		ClientDto dto = new ClientDto();
		ClientManager cm = new ClientDBManager();
		dto = cm.selectClient(client_id);
		return dto;
	}// viewClientDetails

	// ------------v-i-e-w--C-l-i-e-n-t--D-e-t-a-i-l-s--R-e-a-d-y-------

	// -----------v-i-e-w--A-c-c-o-u-n-t--D-e-t-a-i-l-s--R-e-a-d-y------
	public void viewAccountDetails(long account_id) {
		AccountDto dto = new AccountDto();
		AccountManager am = new AccountDBManager();
		dto = am.selectAccount(account_id);
		dto.setAccount_id(account_id);
		dto.setClient_id(dto.getClient_id());
		dto.setBalance(dto.getBalance());
		dto.setCredit_limit(dto.getCredit_limit());
		dto.setComment(dto.getComment());
		System.out.println(dto.getAll());

	}// viewAccountDetails

	// -----------v-i-e-w--A-c-c-o-u-n-t--D-e-t-a-i-l-s--R-e-a-d-y------

	// --------------v-i-e-w--C-l-i-e-n-t--D-e-p-o-s-i-t-s--R-e-a-d-y----
	public DepositDto viewClientDeposits(long client_id) {
		DepositManager dm = new DepositDBManager();
		DepositDto dto = dm.selectDeposit(client_id);
		return dto;
	}// viewClientDeposits

	// --------------v-i-e-w--C-l-i-e-n-t--D-e-p-o-s-i-t-s--R-e-a-d-y----

	// -------------v-i-e-w--C-l-i-e-n-t--A-c-t-i-v-i-e-s--R-e-a-d-y-----
	public List<ActivityDto> viewClientActivities(long client_id) {
		ActivityManager am = new ActivityDBManeger();
		List<ActivityDto> list = am.selectActivities(client_id);
		return list;
	}// viewClientActivities

	// -------------v-i-e-w--C-l-i-e-n-t--A-c-t-i-v-i-e-s--R-e-a-d-y-----

	// ------------v-i-e-w--S-y-s-t-e-m--P-r-o-p-e-r-t-y--R-e-a-d-y-----
	public void viewSystemProperty(String prop_key) {
		PropertiesDto dto = new PropertiesDto();
		PropertiesManager pm = new PropertiesDBManager();
		dto = pm.selectProperties(prop_key);
		dto.setProp_key(prop_key);
		dto.setProp_value(dto.getProp_value());
		System.out.println(dto.getProp_value());

	}// viewSystemProperty
		// -----------v-i-e-w--S-y-s-t-e-m--P-r-o-p-e-r-t-y--R-e-a-d-y-----

	public AccountDto viewAccountDetailsByClientId(long client_id) {
		AccountManager am = new AccountDBManager();
		AccountDto dto = am.selectAccountByClientId(client_id);
		return dto;
	}

	public List<DepositDto> viewClientDepositsList(long client_id) {
		DepositManager dm = new DepositDBManager();
		List<DepositDto> list = dm.getClientDepositList(client_id);
		return list;

	}
}// class
