package demo.securitycomms.rateservice.comon.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.securitycomms.common.proxy.ServiceProxyBase;
import demo.securitycomms.rateservice.common.service.IRateService;

@Component
public class RateServiceProxy extends ServiceProxyBase {
	
	@Autowired
	public RateServiceProxy(RateServiceEndpointConfiguration endpoint) {
		super("IRateService", endpoint.getName(), endpoint.getUrl(), IRateService.class);
	}
		
	public IRateService getObject() {
		return (IRateService) this.getService();
	}

}
