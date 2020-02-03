package demo.securitycomms.common.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import com.netflix.client.ClientFactory;
import com.netflix.loadbalancer.ILoadBalancer;

import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.ribbon.LBClientFactory;
import feign.ribbon.RibbonClient;

public class ServiceProxyBase{

	protected Object serviceReference;
	
	private String serviceBeanName;
	private String serviceName;
	private String serviceUrl;
	private Class type;
	protected Boolean initialized;
	
	@Value("${service.eureka.enabled:#{true}}")
	private Boolean eurekaEnabled;
	
	@Autowired
	private ApplicationContext context;

	@Autowired
	private Encoder encoder;
	
	@Autowired
	private Decoder decoder;
	
	@Autowired
	private Contract contract;
	
	
	private void init() {
		System.out.println("ServiceProxyBase.init...");
		this.initialized = true;
		
		if(context.containsBean(serviceBeanName)) {
			System.out.println("Bean Found: " + serviceBeanName);
			this.serviceReference = context.getBean(serviceBeanName);
		} else {
			System.out.println("Building FeignClient..");
			this.serviceReference = this.buildFeignClient(this.serviceName, this.serviceUrl, this.type);
		}
		
	}
	
	public ServiceProxyBase(String serviceBeanName,
			String serviceName,
			String serviceUrl,
			Class type) {
		
		this.serviceBeanName = serviceBeanName;
		this.serviceName = serviceName;
		this.serviceUrl = serviceUrl;
		this.type = type;
		this.initialized = false;
		
		//init();
	}
	
	protected Object getService(){
		if(!this.initialized) {
			this.init();
		}
		
		return this.serviceReference;
	}
	
	public Object buildFeignClient(String serviceName, String serviceUrl, Class type) {
			
		if(this.eurekaEnabled) {
			
			System.out.println("Building eureka enabled client...");
			
			ILoadBalancer lb = null;
			try {
				lb = ClientFactory.getNamedLoadBalancer(serviceName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LBClientFactory f = new LBClientFactory.Default();		
			f.create(serviceName).setLoadBalancer(lb);
				
			return type.cast(Feign.builder()
					.encoder(this.encoder)
					.decoder(this.decoder)
					.contract(contract)
					.requestInterceptor(requestInterceptor())
					.client(RibbonClient.builder().lbClientFactory(f).build())
					.target(type, serviceUrl));
		} else {
			
			System.out.println("Building non-eureka enabled client...");
			
			return type.cast(Feign.builder()
					.encoder(this.encoder)
					.decoder(this.decoder)
					.contract(contract)
					.requestInterceptor(requestInterceptor())
					//.client(RibbonClient.builder().lbClientFactory(f).build())
					.target(type, serviceUrl));
		}
		
	}
	
	private RequestInterceptor requestInterceptor() {
		return new FeignTokenRelayInterceptor();
	}		
}
