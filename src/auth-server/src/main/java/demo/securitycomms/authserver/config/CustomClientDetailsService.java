package demo.securitycomms.authserver.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CustomClientDetailsService {

	public Map<String, Object> getClientDetailsById(String id){
		Map<String, Object> additionalInfo = new HashMap();
		additionalInfo.put("country_code", "IND");
		additionalInfo.put("name", "TOML");
		additionalInfo.put("id", "2250");
		return additionalInfo;						
	}
}