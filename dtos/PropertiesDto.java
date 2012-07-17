package dtos;

public class PropertiesDto {
	String prop_key, prop_value;

	public String getProp_key() {
		return prop_key;
	}

	public void setProp_key(String prop_key) {
		this.prop_key = prop_key;
	}

	public String getProp_value() {
		return prop_value;
	}

	public void setProp_value(String prop_value) {
		this.prop_value = prop_value;
	}
	
	public String getAll() {
		return "prop_key : " + prop_key 
				+ "\nprop_value : " + prop_value;

	}// getAll
}// class

