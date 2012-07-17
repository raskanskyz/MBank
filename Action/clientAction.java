package Action;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Concrete.MBankST;
import dbManagers.AccountDBManager;
import dbManagers.AccountManager;
import dbManagers.ActivityDBManeger;
import dbManagers.ActivityManager;
import dbManagers.ClientDBManager;
import dbManagers.ClientManager;
import dbManagers.DepositDBManager;
import dbManagers.DepositManager;
import dtos.AccountDto;
import dtos.ActivityDto;
import dtos.DepositDto;

public class clientAction extends Action {

	// ---------W-i-t-h-d-r-a-w-a-l--F-r-o-m--A-c-c-o-u-n-t--R-e-a-d-y------
	public void withdrawFromAccount(long account_id, double wdSum)

	throws Exception {
		String commission = MBankST.m.get("commission_rate").replace("$", "");

		Calendar cal = new GregorianCalendar();
		cal = Calendar.getInstance();
		Date d = new Date();
		cal.setTime(d);
		Timestamp today = new Timestamp(cal.getTime().getTime());

		AccountManager am = new AccountDBManager();
		AccountDto adt = am.selectAccount(account_id);
		ActivityManager actman = new ActivityDBManeger();
		ActivityDto dto = new ActivityDto();

		if (adt.getBalance() - wdSum >= (adt.getCredit_limit()) * -1) {
			adt.setBalance(adt.getBalance()
					- (wdSum + Double.parseDouble(commission)));
			am.updateAccount(adt);

			// ^^^^^^^^^^^^^^^^^^U^P^D^A^T^E^S--A^C^C^O^U^N^T^^^^^^^^^^^^^^^^^^^^^^^^^^
			dto.setClient_id(adt.getClient_id());
			dto.setAmount(wdSum);
			dto.setActivity_date((java.sql.Timestamp) today);
			dto.setCommission(Double.valueOf(commission));
			dto.setDescription("Withdraw From Account");

			actman.addActivity(dto);
			// ^^^^^^^^^^^^^^^^^^C^R^E^A^T^E^S--A--N^E^W--A^C^T^I^V^I^T^Y--E^N^T^R^Y^^^^
		} else {
			throw new Exception("Withdraw is not allowed");
		}
	} // updateAccountDetails

	// ---------W-i-t-h-d-r-a-w-a-l--F-r-o-m--A-c-c-o-u-n-t--R-e-a-d-y------

	// ---------D-E-P-O-S-I-T--T-o--A-c-c-o-u-n-t--R-e-a-d-y------
	public void depositToAccount(long account_id, double dSum) {
		double commissionRate = Double.parseDouble(MBankST.m.get(
				"commission_rate").replace("$", ""));
		Calendar cal = new GregorianCalendar();
		cal = Calendar.getInstance();
		Date d = new Date();
		cal.setTime(d);
		Timestamp today = new Timestamp(cal.getTime().getTime());

		AccountManager am = new AccountDBManager();
		AccountDto adt = am.selectAccount(account_id);
		ActivityManager actman = new ActivityDBManeger();
		ActivityDto dto = new ActivityDto();

		adt.setBalance(adt.getBalance() + (dSum) - (commissionRate));
		am.updateAccount(adt);
		dto.setClient_id(adt.getClient_id());
		dto.setAmount(dSum);
		dto.setActivity_date(today);
		dto.setCommission(commissionRate);
		dto.setDescription("Deposit to Account");

		actman.addActivity(dto);

	}// depositToAccount()

	// ---------D-E-P-O-S-I-T--T-o--A-c-c-o-u-n-t--R-e-a-d-y------

	// ---------C-r-e-a-t--n-e-w--D-E-P-O-S-I-T--R-e-a-d-y------
	public void createNewDeposit(long client_id, double pakamSum, int months) {
		String depType;

		if (months > 12 && months < 480) {
			depType = "Long";

		}// if
		else if (months > 0 && months <= 12) {
			depType = "Short";

		}// else if

		else {
			depType = null;
		}// else

		Calendar cal = new GregorianCalendar();
		cal = Calendar.getInstance();
		Date d = new Date();
		cal.setTime(d);
		Timestamp today = new Timestamp(cal.getTime().getTime());

		DepositDto ddto = new DepositDto();
		DepositManager dm = new DepositDBManager();
		ActivityDto adto = new ActivityDto();
		ActivityManager am = new ActivityDBManeger();
		ClientManager cm = new ClientDBManager();
		double depositInterest = Double.valueOf(MBankST.m.get(
				cm.selectClient(client_id).getType() + "_daily_interest")
				.trim());
		double commission = Double.valueOf(MBankST.m
				.get(cm.selectClient(client_id).getType()
						+ "_deposit_commission").trim().replace("%", ""));
		ddto.setClient_id(client_id);
		ddto.setBalance(pakamSum);
		ddto.setType(depType);
		ddto.setOpening_date(today);
		cal.add(Calendar.MONTH, months);
		Timestamp tomorrow = new Timestamp(cal.getTime().getTime());

		long differenceInDays = ((tomorrow.getTime() - today.getTime()) / 86400000)/*
																					 * <
																					 * --
																					 * millieconds
																					 * 
																					 * --
																					 * --
																					 * -
																					 * 
																					 * in
																					 * a
																					 * day
																					 */;

		ddto.setClosing_date(tomorrow);

		ddto.setEstimated_balance((long) (pakamSum + (pakamSum
				* (depositInterest / 100) * differenceInDays)));

		dm.addDeposit(ddto);
		adto.setActivity_date(today);
		adto.setAmount(pakamSum);
		adto.setClient_id(client_id);
		adto.setCommission(commission);
		adto.setDescription(depType + " Term Deposit.");
		am.addActivity(adto);

	}// createNewDeposit

	// ---------C-r-e-a-t--n-e-w--D-E-P-O-S-I-T--R-e-a-d-y------

	// ---------P-r-e--O-p-e-n--D-E-P-O-S-I-T-------
	public boolean preOpenDeposit(DepositDto ddto, AccountDto adto) {

		Calendar cal = new GregorianCalendar();
		cal = Calendar.getInstance();
		Date d = new Date();
		cal.setTime(d);
		Timestamp today = new Timestamp(cal.getTime().getTime());

		DepositManager dm = new DepositDBManager();
		ActivityDto actdto = new ActivityDto();
		ActivityManager am = new ActivityDBManeger();
		ClientManager cm = new ClientDBManager();
		AccountManager amn = new AccountDBManager();
		long totalDays = today.getTime() - ddto.getOpening_date().getTime();
		double preOpenFee = (Double.valueOf(MBankST.m.get("pre_open_fee")
				.trim().replace("%", ""))) / 100;// <---Converts prop % into
													// decimal %
		double depositDailyInterest = Double.valueOf(MBankST.m.get(
				cm.selectClient(ddto.getClient_id()).getType()
						+ "_daily_interest").trim());
		double depositBalance = ddto.getBalance();
		double depositAmountAfterOpen = depositBalance
				+ (totalDays * depositDailyInterest)
				- (depositBalance * preOpenFee);

		if (ddto.getType().equals("Long")
				&& ddto.getClient_id() == adto.getClient_id()) {// <-------Code
																// will execute
																// only if
																// deposit is
																// long term &&
																// deposit+account
																// belong to
																// same client
			adto.setBalance(adto.getBalance() + depositBalance
					+ (totalDays * depositDailyInterest)
					- (depositBalance * preOpenFee));
			amn.updateAccount(adto);
			actdto.setClient_id(ddto.getClient_id());
			actdto.setAmount(depositAmountAfterOpen);
			actdto.setActivity_date(today);
			actdto.setCommission(depositBalance * preOpenFee);
			actdto.setDescription("Pre-Open Deposit");
			am.addActivity(actdto);

			dm.removeDeposit(ddto, adto);
			return true;
		}// if
		else {
			System.err
					.println("Sorry, Short Term Deposits Cannot Be Pre-Opened");
			return false;
		}// else

		// ---------P-r-e--O-p-e-n--D-E-P-O-S-I-T-------

	}

	// ---------P-r-e-O-p-e-n--D-E-P-O-S-I-T-------

	public DepositDto getDepositDtoByDepositId(long depositId) {

		DepositManager dm = new DepositDBManager();
		DepositDto dto = new DepositDto();
		dto = dm.selectDepositByDepositId(depositId);
		return dto;

	}
}// class
