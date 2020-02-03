package demo.securitycomms.apicommon.utils;

public class UserDetails {
	
	public String username;
	public String countryCode;
	public String userId;
	public String name;
	public String timezone;
	public String type;

	public Boolean isFromCountry(String country) {
		return this.countryCode.equals(country);
	}
	
	public Boolean isClient() {
		return this.type.equalsIgnoreCase("client");
	}
	
	public Boolean isSameUser(Integer id) {
		//System.out.println(userId + ":" + id);
		return this.userId.equals(id.toString());
	}
	
	@Override
	public String toString() {
		return "Id: " + userId + ", Type: " + type + ", Name: " + name + ", Country: " + countryCode + ", Timezone: " + timezone;
		
	}
}
