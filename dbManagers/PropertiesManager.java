package dbManagers;

import java.sql.ResultSet;
import java.util.Map;

import dtos.PropertiesDto;

public interface PropertiesManager {
	
	public PropertiesDto selectProperties(String prop_key);

	void addProperties(PropertiesDto dto);

	void removeProperties(String prop_key);

	void updateProperties(PropertiesDto dto);

	public Map<String, String> selectAllProperties();

	ResultSet getResultSet();
	
}// class
