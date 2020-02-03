package demo.securitycomms.apicommon.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

@Component
public class RibbonUrlRetriever {
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	public String getUrlFromName(String name) {
		ServiceInstance svcInstance = loadBalancer.choose(name);
		return svcInstance.getUri().toString();
	}

}
