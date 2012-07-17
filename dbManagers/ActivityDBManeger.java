package dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.dbMngST;
import dtos.ActivityDto;

public class ActivityDBManeger implements ActivityManager {
	private final String TABLE_NAME = "Activity";
	private final String KEY_FLD = "client_id";

	private final String selectallqry = "SELECT * FROM " + TABLE_NAME;
	private final String selectactivityqry = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + KEY_FLD + " = ?";
	private final String removeqry = "DELETE FROM " + TABLE_NAME + " where "
			+ KEY_FLD + " = ?";
	private final String addactivityqry = "INSERT INTO "
			+ TABLE_NAME
			+ " (id, "
			+ "client_id, amount, activity_date, commission, description ) values (?,?,?,?,?,?)";
	private final String updateqry = " UPDATE "
			+ TABLE_NAME
			+ " SET id =?, client_id=?, amount=?, activity_date=?, commission=?, "
			+ "description=? WHERE " + KEY_FLD + " = ?";
	private final String queryStr = "select LAST_INSERT_ID()";

	@Override
	public List<ActivityDto> selectActivities(long client_id) {
		
		
		try {
			
			Connection c1 = dbMngST.instance().getCon();
			PreparedStatement pstm = c1.prepareStatement(selectactivityqry);
			pstm.setLong(1, client_id);
			ResultSet rs = pstm.executeQuery();
			
			List<ActivityDto> clientActivityList = new ArrayList<ActivityDto>();
			
			while (rs.next()) {
				ActivityDto dto = new ActivityDto();
				dto.setId(rs.getLong("id"));
				dto.setClient_id(rs.getLong("client_id"));
				dto.setAmount(rs.getDouble("amount"));
				dto.setActivity_date(rs.getTimestamp("activity_date"));
				dto.setCommission(rs.getDouble("commission"));
				dto.setDescription(rs.getString("description"));
				clientActivityList.add(dto);

			}// while
			return clientActivityList;
		}// try
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}// catch
	}// selectActivity

	@Override
	public void removeActivity(long id) {
		try {

			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(removeqry);
			pstm.setLong(1, id);
			pstm.execute();
			System.out.println(id + " Has Been Removed Successfully..");
		}// try
		catch (SQLException e) {
			System.err.println("Problem Removing Activity: " + id);
			e.printStackTrace();
		}// catch
	}// removeActivity

	@Override
	public void addActivity(ActivityDto dto) {
		try {
			int ID = 0;
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(addactivityqry);
			Statement stmt = dbMngST.instance().getCon().createStatement();
			pstm.setLong(1, dto.getId());
			pstm.setLong(2, dto.getClient_id());
			pstm.setDouble(3, dto.getAmount());
			pstm.setTimestamp(4, dto.getActivity_date());
			pstm.setDouble(5, dto.getCommission());
			pstm.setString(6, dto.getDescription());
			pstm.execute();

			ResultSet rs = stmt.executeQuery(queryStr);

			if (rs.next())
				ID = rs.getInt(1);

			System.out
					.println("Activity(id=" + ID + ") successfully recorded!");

		}// try
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// catch
	}// addActivity

	@Override
	public void updateActivity(ActivityDto dto) {
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(updateqry);

			pstm.setLong(1, dto.getId());
			pstm.setLong(2, dto.getClient_id());
			pstm.setDouble(3, dto.getAmount());
			pstm.setTimestamp(4, dto.getActivity_date());
			pstm.setDouble(5, dto.getCommission());
			pstm.setString(6, dto.getDescription());
			pstm.setLong(7, dto.getId());
			pstm.execute();
			System.out.println("The record successfully updated!");

		}// try
		catch (SQLException e) { 
			e.printStackTrace();
		}// catch

	}// updateActivity

	@Override
	public void getActivityList() {//<-------------This method needs to return an activityDtoList..??
		try {
			PreparedStatement pstm = dbMngST.instance().getCon()
					.prepareStatement(selectallqry);
			ResultSet rs = pstm.executeQuery(selectallqry);

			while (rs.next()) {
				ActivityDto adto = new ActivityDto();
				adto.setId(rs.getLong("id"));
				adto.setClient_id(rs.getLong("client_id"));
				adto.setAmount(rs.getDouble("amount"));
				adto.setActivity_date(rs.getTimestamp("activity_date"));
				adto.setCommission(rs.getDouble("commission"));
				adto.setDescription(rs.getString("description"));
				System.out.println(adto.getAll());
				System.out
						.println("===============================================");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}// getClientList

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
	}

}// class
