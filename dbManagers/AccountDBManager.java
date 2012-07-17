package dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.dbMngST;
import dtos.AccountDto;

public class AccountDBManager implements AccountManager {

	private final String TABLE_NAME = "Accounts";
	private final String KEY_FLD = "account_id";
	private final String selectallqry = "SELECT * FROM " + TABLE_NAME;
	private final String selectaccountqry = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + KEY_FLD + " = ?";
	private final String removeqry = "DELETE FROM " + TABLE_NAME + " where "
			+ KEY_FLD + " = ?";
	private final String addaccountqry = "INSERT INTO " + TABLE_NAME + " ("
			+ "client_id, balance, credit_limit, comment) values (?,?,?,?)";
	private final String updateqry = " UPDATE "
			+ TABLE_NAME
			+ " SET account_id =?, client_id=?, balance=?, credit_limit=?, comment=?  WHERE "
			+ KEY_FLD + " = ?";
	@SuppressWarnings("unused")
	private final String queryStr = "select LAST_INSERT_ID()";
	private final String selectaccountqrybyclient = "SELECT * FROM "
			+ TABLE_NAME + " WHERE client_id = ?";

	@Override
	public AccountDto selectAccount(long account_id) {
		AccountDto dto = new AccountDto();
		try {
			Connection c1 = dbMngST.instance().getCon();
			PreparedStatement pstm = c1.prepareStatement(selectaccountqry);
			pstm.setLong(1, account_id);
			ResultSet rs = pstm.executeQuery();
			rs.next();

			dto.setAccount_id(rs.getLong("account_id"));
			dto.setClient_id(rs.getLong("client_id"));
			dto.setBalance(rs.getDouble("balance"));
			dto.setCredit_limit(rs.getDouble("credit_limit"));
			dto.setComment(rs.getString("comment"));

		}// try
		catch (Exception e) {
			e.printStackTrace();
		}// catch
		return dto;

	}// selectAccount

	@Override
	public void removeAccount(long account_id) {
		try {

			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(removeqry);
			pstm.setLong(1, account_id);
			pstm.execute();
			System.out.println(account_id + " Has Been Removed Successfully..");
		}// try
		catch (SQLException e) {
			System.err.println("Problem Removing Account: " + account_id);
			e.printStackTrace();
		}// catch
	}// removeAccountt

	@Override
	public void addAccount(AccountDto dto) {
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(addaccountqry);

			pstm.setLong(1, dto.getClient_id());
			pstm.setDouble(2, dto.getBalance());
			pstm.setDouble(3, dto.getCredit_limit());
			pstm.setString(4, dto.getComment());
			pstm.execute();
			System.out.println("The record successfully added!");

		}// try
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// catch
	}// addAccount

	@Override
	public void updateAccount(AccountDto dto) {
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(updateqry);

			pstm.setLong(1, dto.getAccount_id());
			pstm.setLong(2, dto.getClient_id());
			pstm.setDouble(3, dto.getBalance());
			pstm.setDouble(4, dto.getCredit_limit());
			pstm.setString(5, dto.getComment());
			pstm.setLong(6, dto.getAccount_id());
			pstm.execute();
			System.out.println("Account " + dto.getAccount_id()
					+ " Was Successfully Updated!");
		}// try
		catch (SQLException e) {
			e.printStackTrace();
		}// catch

	}// updateAccount

	public void getAccountList() {//<-----------This method should return an accountDtoList..??
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(selectallqry);
			ResultSet rs = pstm.executeQuery(selectallqry);

			while (rs.next()) {
				while (rs.next()) {

					AccountDto adto = new AccountDto();
					adto.setAccount_id(rs.getLong("account_id"));
					adto.setClient_id(rs.getLong("client_id"));
					adto.setBalance(rs.getDouble("balance"));
					adto.setCredit_limit(rs.getDouble("credit_limit"));
					adto.setComment(rs.getString("comment"));
					System.out.println(adto.getAll());
					System.out.println("==============================");

				}// inner while
			}// outer while
		}// try
		catch (SQLException e) {

			e.printStackTrace();
		}

	}// getAccountList

	@Override
	public AccountDto selectAccountByClientId(long client_id) {

		AccountDto dto = new AccountDto();
		try {
			Connection c1 = dbMngST.instance().getCon();
			PreparedStatement pstm = c1
					.prepareStatement(selectaccountqrybyclient);
			pstm.setLong(1, client_id);
			ResultSet rs = pstm.executeQuery();
			rs.next();

			dto.setAccount_id(rs.getLong("account_id"));
			dto.setClient_id(rs.getLong("client_id"));
			dto.setBalance(rs.getDouble("balance"));
			dto.setCredit_limit(rs.getDouble("credit_limit"));
			dto.setComment(rs.getString("comment"));

		}// try
		catch (Exception e) {
			e.printStackTrace();
		}// catch
		return dto;

	}//selectAccountByClientId

	@Override
	public ResultSet getResultSet() {
		try {
			Connection c1 = dbMngST.instance().getCon();
			PreparedStatement pstm = c1.prepareStatement(selectallqry);
			ResultSet rs = pstm.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}// catch
		return null;
	}
}// class

