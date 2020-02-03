package demo.securitycomms.apigateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PublicAPIConfiguration {
	
	private List<String> publicUrls;
	
	public PublicAPIConfiguration() {
		this.publicUrls = new ArrayList<String>();
		
		publicUrls.add("**/orders/random");		
	}
	
	public List<String> getPublicUrls(){
		return this.publicUrls;
	}

}
