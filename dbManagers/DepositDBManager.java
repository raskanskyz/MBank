package dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import Concrete.MBankST;
import DB.dbMngST;
import dtos.AccountDto;
import dtos.DepositDto;

public class DepositDBManager implements DepositManager {

	private final String TABLE_NAME = "Deposits";
	private final String KEY_FLD = "deposit_id";
	private final String selectallqry = "SELECT * FROM " + TABLE_NAME;
	private final String selectdepositqry = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + KEY_FLD + " = ?";
	private final String removeqry = "DELETE FROM " + TABLE_NAME + " where "
			+ KEY_FLD + " = ?";
	private final String adddepositqry = "INSERT INTO "
			+ TABLE_NAME
			+ " ( "
			+ "client_id, balance, type, estimated_balance, opening_date, closing_date ) values (?,?,?,?,?,?)";
	private final String updateqry = " UPDATE "
			+ TABLE_NAME
			+ " SET client_id =?, client_id=?, balance=?, type=?, estimated_balance=?, opening_date=?, closing_date=?   WHERE "
			+ KEY_FLD + " = ?";
	private final String closeddeposits = "SELECT * FROM " + TABLE_NAME
			+ " WHERE closing_date > ? AND closing_date < ?";
	private final String clientAllDeposits = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + "client_id" + " = ?";
	
	@Override
	public DepositDto selectDeposit(long client_id) {
		DepositDto dto = new DepositDto();
		try {
			Connection c1 = dbMngST.instance().getCon();
			PreparedStatement pstm = c1.prepareStatement(selectdepositqry);
			pstm.setLong(1, client_id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {

				dto.setDeposit_id(rs.getLong("deposit_id"));
				dto.setClient_id(rs.getLong("client_id"));
				dto.setBalance(rs.getDouble("balance"));
				dto.setType(rs.getString("type"));
				dto.setEstimated_balance(rs.getLong("Estimated_balance"));
				dto.setOpening_date(rs.getTimestamp("Opening_date"));
				dto.setClosing_date(rs.getTimestamp("Closing_date"));

			}// while
		}// try
		catch (Exception e) {
			e.printStackTrace();

		}// catch
		return dto;

	}// selectDeposit

	@Override
	public void removeDeposit(DepositDto ddto, AccountDto adto) {
		ClientManager cm = new ClientDBManager();
		double depositInterest = Double.parseDouble(MBankST.m.get(
				cm.selectClient(ddto.getClient_id()).getType()
						+ "_daily_interest").trim());
		Calendar cal = new GregorianCalendar();
		cal = Calendar.getInstance();
		Date d = new Date();
		cal.setTime(d);
		Timestamp today = new Timestamp(cal.getTime().getTime());
		long depositActivePeriod = today.getTime()
				- ddto.getOpening_date().getTime() / 86400000;

		adto.setBalance(adto.getBalance()
				+ (ddto.getBalance() + ((ddto.getBalance() * depositInterest) * depositActivePeriod)));

		try {

			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(removeqry);
			pstm.setLong(1, ddto.getDeposit_id());
			pstm.execute();
			System.out.println(ddto.getDeposit_id()
					+ " Has Been Removed Successfully..");
		}// try
		catch (SQLException e) {
			System.err.println("Problem Removing Account: "
					+ ddto.getDeposit_id());
			e.printStackTrace();
		}// catch

	}// remove Deposit

	@Override
	public void addDeposit(DepositDto dto) {
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(adddepositqry);

			pstm.setLong(1, dto.getClient_id());
			pstm.setDouble(2, dto.getBalance());
			pstm.setString(3, dto.getType());
			pstm.setLong(4, dto.getEstimated_balance());
			pstm.setTimestamp(5, (Timestamp) dto.getOpening_date());
			pstm.setTimestamp(6, (Timestamp) dto.getClosing_date());
			pstm.execute();
			System.out.println("Your " + dto.getType() + " Term Deposit Of $"
					+ dto.getBalance() + " Was Successful!");

		}// try
		catch (SQLException e) {
			
			e.printStackTrace();
		}// catch
	}// add account

	@Override
	public void updateDeposit(DepositDto dto) {
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(updateqry);

			pstm.setLong(1, dto.getDeposit_id());
			pstm.setLong(2, dto.getClient_id());
			pstm.setDouble(3, dto.getBalance());
			pstm.setString(4, dto.getType());
			pstm.setLong(5, dto.getEstimated_balance());
			pstm.setTimestamp(6, dto.getOpening_date());
			pstm.setTimestamp(7, dto.getClosing_date());
			pstm.setLong(8, dto.getDeposit_id());
			pstm.execute();
			System.out.println("The record successfully updated!");
		}// try
		catch (SQLException e) {
			e.printStackTrace();
		}// catch

	}// updateAccount

	@Override
	public LinkedList<DepositDto> getDepositList() {
		try {
			LinkedList<DepositDto> depositList = new LinkedList<DepositDto>();
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(selectallqry);
			ResultSet rs = pstm.executeQuery(selectallqry);

			while (rs.next()) {
				DepositDto ddto = new DepositDto();
				ddto.setClient_id(rs.getInt("client_id"));
				ddto.setDeposit_id(rs.getLong("deposit_id"));
				ddto.setBalance(rs.getDouble("balance"));
				ddto.setEstimated_balance(rs.getInt("estimated_balance"));
				ddto.setType(rs.getString("type"));
				ddto.setOpening_date(rs.getTimestamp("opening_date"));
				ddto.setClosing_date(rs.getTimestamp("closing_date"));
				depositList.add(ddto);
				System.out.println(ddto.getAll());
				System.out
						.println("=============================================");

			}// while
			return depositList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}// getDepositList

	@Override
	public LinkedList<DepositDto> getClosedDepositList() {

		Calendar cal = new GregorianCalendar();
		cal = Calendar.getInstance();
		Date d = new Date();
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 00);
		Timestamp beginningOfDay = new Timestamp(cal.getTime().getTime());
		LinkedList<DepositDto> closedDepositList = new LinkedList<DepositDto>();

		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(closeddeposits);
			pstm.setTimestamp(1, beginningOfDay);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);

			Timestamp endOfDay = new Timestamp(cal.getTime().getTime());
			pstm.setTimestamp(2, endOfDay);
			ResultSet rs = pstm.executeQuery();
			System.out.println(beginningOfDay + " ," + endOfDay);
			while (rs.next()) {
				DepositDto dto = new DepositDto();
				dto.setDeposit_id(rs.getLong("deposit_id"));
				dto.setClient_id(rs.getLong("client_id"));
				dto.setBalance(rs.getDouble("balance"));
				dto.setType(rs.getString("type"));
				dto.setEstimated_balance(rs.getInt("estimated_balance"));
				dto.setOpening_date(rs.getTimestamp("opening_date"));
				dto.setClosing_date(rs.getTimestamp("closing_date"));

				closedDepositList.add(dto);

			}// while
		}// try

		catch (SQLException e) {
			e.printStackTrace();
		}// catch
		return closedDepositList;

	}// getClosedDepositList()

	@Override
	public ResultSet getResultSet() {
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(selectallqry);
			ResultSet rs = pstm.executeQuery(selectallqry);
			return rs;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}//getResultSet

	@Override
	public List<DepositDto> getClientDepositList(long client_id){
			
			try {
				List<DepositDto> list = new ArrayList<DepositDto>();
				Connection c1 = dbMngST.instance().getCon();
				PreparedStatement pstm = c1.prepareStatement(clientAllDeposits);
				pstm.setLong(1, client_id);
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					DepositDto dto = new DepositDto();
					dto.setDeposit_id(rs.getLong("deposit_id"));
					dto.setClient_id(rs.getLong("client_id"));
					dto.setBalance(rs.getDouble("balance"));
					dto.setType(rs.getString("type"));
					dto.setEstimated_balance(rs.getLong("Estimated_balance"));
					dto.setOpening_date(rs.getTimestamp("Opening_date"));
					dto.setClosing_date(rs.getTimestamp("Closing_date"));
					list.add(dto);
				}// while
				return list;
			}// try
			catch (Exception e) {
				e.printStackTrace();

			}// catch
			return null;
			

		}// selectDeposit

	@Override
	public DepositDto selectDepositByDepositId(long deposit_id) {
		DepositDto dto = new DepositDto();
		try {
			Connection c1 = dbMngST.instance().getCon();
			PreparedStatement pstm = c1.prepareStatement(selectdepositqry);
			pstm.setLong(1, deposit_id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {

				dto.setDeposit_id(rs.getLong("deposit_id"));
				dto.setClient_id(rs.getLong("client_id"));
				dto.setBalance(rs.getDouble("balance"));
				dto.setType(rs.getString("type"));
				dto.setEstimated_balance(rs.getLong("Estimated_balance"));
				dto.setOpening_date(rs.getTimestamp("Opening_date"));
				dto.setClosing_date(rs.getTimestamp("Closing_date"));

			}// while
		}// try
		catch (Exception e) {
			e.printStackTrace();

		}// catch
		return dto;

	}// selectDeposit
}// class
