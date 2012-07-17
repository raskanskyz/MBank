package dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
import DB.dbMngST;
import dtos.PropertiesDto;

public class PropertiesDBManager implements PropertiesManager {

	private final String TABLE_NAME = "Properties";
	private final String KEY_FLD = "prop_key";
	private final String selectallqry = " SELECT * FROM " + TABLE_NAME;
	private final String selectpropertiesqry = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + KEY_FLD + " = ?";
	private final String removeqry = "DELETE FROM " + TABLE_NAME + " where "
			+ KEY_FLD + " = ?";
	private final String addpropertiesqry = "INSERT INTO " + TABLE_NAME
			+ " (prop_key, " + "prop_value ) values (?,?)";
	private final String updateqry = " UPDATE " + TABLE_NAME
			+ " SET prop_key =?, prop_value=? WHERE " + KEY_FLD + " = ?";

	@Override
	public PropertiesDto selectProperties(String prop_key) {
		PropertiesDto dto = new PropertiesDto();
		try {
			Connection c1 = dbMngST.instance().getCon();
			PreparedStatement pstm = c1.prepareStatement(selectpropertiesqry);
			pstm.setString(1, prop_key);
			ResultSet rs = pstm.executeQuery();
			rs.next();

			dto.setProp_key(rs.getString("prop_key"));
			dto.setProp_value(rs.getString("prop_value"));

		}// try
		catch (Exception e) {
			e.printStackTrace();
		}// catch
		return dto;

	}// selectAccount

	@Override
	public void removeProperties(String prop_key) {
		try {

			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(removeqry);
			pstm.setString(1, prop_key);
			pstm.execute();
			System.out.println(prop_key + " Has Been Removed Successfully..");
		}// try
		catch (SQLException e) {
			System.err.println("Problem Removing Account: " + prop_key);
			e.printStackTrace();
		}// catch
	}// remove

	@Override
	public void addProperties(PropertiesDto dto) {
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(addpropertiesqry);
			pstm.setString(1, dto.getProp_key());
			pstm.setString(2, dto.getProp_value());
			pstm.execute();
			System.out.println("The record successfully added!");

		}// try
		catch (SQLException e) {

			e.printStackTrace();
		}// catch
	}// add

	@Override
	public void updateProperties(PropertiesDto dto) {
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(updateqry);

			pstm.setString(1, dto.getProp_key());
			pstm.setString(2, dto.getProp_value());
			pstm.setString(3, dto.getProp_key());
			pstm.execute();
			System.out.println("The record successfully updated!");
		}// try
		catch (SQLException e) {
			e.printStackTrace();
		}// catch

	}// update

	public Map<String, String> selectAllProperties() {
		Map<String, String> tm = new TreeMap<String, String>();
		try {

			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(selectallqry);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				String key1 = rs.getString(1);
				String val1 = rs.getString(2);
				tm.put(key1, val1);

			}// while

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tm;

	}

	@Override
	public ResultSet getResultSet() {
		try {

			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(selectallqry);
			ResultSet rs = pstm.executeQuery();
			return rs;
		}// try
		catch (SQLException e) {
			e.printStackTrace();
		}// catch
		return null;
	}// getResultSet
}// class
