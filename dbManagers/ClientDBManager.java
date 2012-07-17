package dbManagers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.dbMngST;
import dtos.ClientDto;

public class ClientDBManager implements ClientManager {
	private final String TABLE_NAME = "Clients";
	private final String KEY_FLD = "client_id";

	private final String selectallqry = "SELECT * FROM " + TABLE_NAME;
	private final String selectclientqry = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + KEY_FLD + " = ?";
	private final String removeqry = "DELETE FROM " + TABLE_NAME + " where "
			+ KEY_FLD + " = ?";
	private final String addclientqry = "INSERT INTO "
			+ TABLE_NAME
			+ " ( "
			+ "client_name, password, type, address, email, phone, comment ) values (?,?,?,?,?,?,?)";
	private final String updateqry = " UPDATE " + TABLE_NAME
			+ " SET client_name =?, password=?, type=?, address=?, email=?, "
			+ "phone=?," + " comment=?  WHERE " + KEY_FLD + " = ?";

	@Override
	public ClientDto selectClient(long client_id) {
		ClientDto dto = new ClientDto();
		try {
			Connection c1 = dbMngST.instance().getCon();
			PreparedStatement pstm = c1.prepareStatement(selectclientqry);
			pstm.setLong(1, client_id);
			ResultSet rs = pstm.executeQuery();
			rs.next();

			dto.setClient_id(rs.getLong("client_id"));
			dto.setClient_name(rs.getString("client_name"));
			dto.setPassword(rs.getString("password"));
			dto.setType(rs.getString("type"));
			dto.setAddress(rs.getString("address"));
			dto.setEmail(rs.getString("email"));
			dto.setPhone(rs.getString("phone"));
			dto.setComment(rs.getString("comment"));
			return dto;
		}// try
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}// catch
	}// selectClient

	@Override
	public void removeClient(long client_id) {
		try {
			ClientManager cm = new ClientDBManager();
			ClientDto dto = cm.selectClient(client_id);
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(removeqry);
			pstm.setLong(1, client_id);
			System.out.println(dto.getClient_name() + "(id=" + client_id
					+ ") Has Been Removed Successfully..");

			pstm.execute();

		}// try
		catch (SQLException e) {

			System.err.println("Problem Removing Client: " + client_id);
			e.printStackTrace();

		}// catch
	}// removeClient

	@Override
	public void addClient(ClientDto dto) {
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(addclientqry);

			pstm.setString(1, dto.getClient_name());
			pstm.setString(2, dto.getPassword());
			pstm.setString(3, dto.getType());
			pstm.setString(4, dto.getAddress());
			pstm.setString(5, dto.getEmail());
			pstm.setString(6, dto.getPhone());
			pstm.setString(7, dto.getComment());
			pstm.execute();
			System.out.println(dto.getClient_name()
					+ ", Thank You For Joining MBank!");

		}// try
		catch (SQLException e) {

			e.printStackTrace();
		}// catch
	}// addClient

	@Override
	public void updateClient(ClientDto dto) {
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(updateqry);

			pstm.setString(1, dto.getClient_name());
			pstm.setString(2, dto.getPassword());
			pstm.setString(3, dto.getType());
			pstm.setString(4, dto.getAddress());
			pstm.setString(5, dto.getEmail());
			pstm.setString(6, dto.getPhone());
			pstm.setString(7, dto.getComment());
			pstm.setLong(8, dto.getClient_id());
			pstm.execute();
			System.out.println("The record successfully updated!");

		}// try
		catch (SQLException e) {
			e.printStackTrace();
		}// catch

	}// updateClient

	@Override
	public void getClientList() {// <-------------This method needs to return an
									// activityDtoList..??
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(selectallqry);
			ResultSet rs = pstm.executeQuery(selectallqry);

			while (rs.next()) {

				ClientDto cdto = new ClientDto();
				cdto.setClient_id(rs.getLong("client_id"));
				cdto.setClient_name(rs.getString("client_name"));
				cdto.setPassword(rs.getString("password"));
				cdto.setAddress(rs.getString("address"));
				cdto.setEmail(rs.getString("email"));
				cdto.setPhone(rs.getString("phone"));
				cdto.setComment(rs.getString("comment"));
				System.out.println(cdto.getAll());
				System.out.println("==============================");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}// getClientList

	@Override
	public ResultSet getResultSet() {
		PreparedStatement pstm;
		try {
			pstm = dbMngST.instance().getCon().prepareStatement(selectallqry);
			ResultSet rs = pstm.executeQuery(selectallqry);
			return rs;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}// getResultSet

}// class
