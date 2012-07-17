package dbManagers;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import dtos.AccountDto;
import dtos.DepositDto;

public interface DepositManager {

	public DepositDto selectDeposit(long client_id);

	void addDeposit(DepositDto dto);

	void updateDeposit(DepositDto dto);

	LinkedList<DepositDto> getDepositList();

	LinkedList<DepositDto> getClosedDepositList();

	void removeDeposit(DepositDto ddto, AccountDto adto);

	public ResultSet getResultSet();

	public List<DepositDto> getClientDepositList(long client_id);

	public DepositDto selectDepositByDepositId(long deposit_id);
}// class
