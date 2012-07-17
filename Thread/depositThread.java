package Thread;

import java.util.LinkedList;

import dbManagers.AccountDBManager;
import dbManagers.AccountManager;
import dbManagers.DepositDBManager;
import dbManagers.DepositManager;
import dtos.AccountDto;
import dtos.DepositDto;

public class depositThread extends Thread {

	public void run() {

		while (true) {
			try {
				DepositManager dm = new DepositDBManager();
				AccountManager am = new AccountDBManager();
				AccountDto adto = new AccountDto();
				LinkedList<DepositDto> LL = dm.getClosedDepositList();
				for (int i = 0; i < LL.size(); ++i) {
					DepositDto ddto = LL.get(i);
					adto = am.selectAccountByClientId(ddto.getClient_id());
					dm.removeDeposit(ddto, adto);
				}// for

				Thread.sleep(86400000);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}
}// class

