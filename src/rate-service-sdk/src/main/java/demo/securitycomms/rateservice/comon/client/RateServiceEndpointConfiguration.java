package demo.securitycomms.rateservice.comon.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import demo.securitycomms.common.proxy.APIEndpointConfiguration;

@Component
public class RateServiceEndpointConfiguration implements APIEndpointConfiguration {
	

	@Value("${services.rate-service.name:#{null}}")
	private String name;	
	
	@Value("${services.rate-service.url:#{null}}")
	private String url;
	
	public RateServiceEndpointConfiguration() {
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return this.url;
	}
	
}
