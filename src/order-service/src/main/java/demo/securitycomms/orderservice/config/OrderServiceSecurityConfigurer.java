package demo.securitycomms.orderservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import demo.securitycomms.apicommon.service.IResourceServerSecurityConfigurer;


@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OrderServiceSecurityConfigurer implements IResourceServerSecurityConfigurer  {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub	
		System.out.println("Orders: Configuring public urls...");
		http.authorizeRequests().antMatchers("/orders/random").permitAll();
	}

}
