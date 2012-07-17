package dbManagers;

import java.sql.ResultSet;
import java.util.List;

import dtos.ActivityDto;

public interface ActivityManager {
	
	public List<ActivityDto> selectActivities(long client_id);

	void addActivity(ActivityDto dto);

	void removeActivity(long id);

	void updateActivity(ActivityDto dto);
	
	void getActivityList();

	public ResultSet getResultSet();
	




}// class
