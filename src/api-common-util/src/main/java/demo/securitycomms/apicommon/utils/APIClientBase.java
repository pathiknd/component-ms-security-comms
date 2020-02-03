//package demo.securitycomms.apicommon.utils;
//
//import org.bouncycastle.asn1.x509.Target;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
////import org.springframework.cloud.netflix.ribbon.RibbonClient;
//import com.netflix.client.config.*;
//import com.netflix.loadbalancer.ILoadBalancer;
//import com.netflix.client.*;
//
//import com.netflix.ribbon.Ribbon;
//
//import demo.securitycomms.apicommon.service.IAPIServiceBase;
//import demo.securitycomms.common.proxy.APIEndpointConfiguration;
//import feign.Client;
//import feign.Contract;
//import feign.Feign;
//import feign.ribbon.LBClient;
//import feign.ribbon.LBClientFactory;
//import feign.ribbon.LoadBalancingTarget;
//import feign.ribbon.RibbonClient;
//import feign.RequestInterceptor;
//import feign.jackson.JacksonDecoder;
//import feign.jackson.JacksonEncoder;
//
////@Component
//public class APIClientBase<T1> {
//	
//	//@Autowired
//	//private ApplicationContext context;
//	@Autowired(required = false)
//	private T1 serviceReference;
//	
//	//@Autowired
//	private T1 feignClient;
//	
//	//private String name;
//	//private String url;
//	private Class<T1> feignType;
//	private APIEndpointConfiguration endpoint;
//	
//	public APIClientBase(APIEndpointConfiguration endpoint, Class<T1> clientType) {
//		this.endpoint = endpoint;
//		this.feignType = clientType;
//	}
//	
//	public T1 getClient() {
//		if(this.serviceReference != null) {
//			System.out.println("serviceReference not null");
//			return this.serviceReference;
//		} else {
//			
//			if(this.feignClient == null) {
//				System.out.println("feignClient null");
//				this.buildFeignClient();
//			}
//			return this.feignClient;
//		}
//	}
//	
//	private void buildFeignClient() {
//		System.out.println("building feignClient");
//		
//		//Object o =  DefaultClientConfigImpl
//
//		//DefaultClientConfigImpl c = DefaultClientConfigImpl.getClientConfigWithDefaultValues(this.endpoint.getName());
//		//c.loadDefaultValues();
//		//c.setProperty(CommonClientConfigKey.VipAddress, this.endpoint.getName());
//		//c.setProperty(CommonClientConfigKey.NIWSServerListClassName, "com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList");
//		ILoadBalancer lb = null;
//		try {
//			//lb = ClientFactory.registerNamedLoadBalancerFromclientConfig(this.endpoint.getName(), c);
//			lb = ClientFactory.getNamedLoadBalancer(this.endpoint.getName());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		LBClientFactory f = new LBClientFactory.Default();		
//		
//		f.create(this.endpoint.getName()).setLoadBalancer(lb);
//		
//		//LBClient client = LBClient.create(lb, c);
//		
//			
//		this.feignClient = Feign.builder()
//				//.encoder(new JacksonEncoder())
//				//.decoder(new JacksonDecoder())
//				.requestInterceptor(requestInterceptor())
//				.client(RibbonClient.builder().lbClientFactory(f).build())
//				.target(this.feignType, this.endpoint.getUrl());
//				//.client(RibbonClient.create())
//				//.target(LoadBalancingTarget.create(this.feignType, this.endpoint.getUrl()));
//				//.target(this.feignType, this.endpoint.getUrl());
//				//.target(new FeignOAuth2Target<T1>(this.feignType, this.endpoint.getName(), this.endpoint.getUrl()));
//	}
//	
//	private RequestInterceptor requestInterceptor() {
//		return new FeignRequestInterceptor();
//	}
//}
