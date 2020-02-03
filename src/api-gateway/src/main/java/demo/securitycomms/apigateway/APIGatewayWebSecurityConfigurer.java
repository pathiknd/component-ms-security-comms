package demo.securitycomms.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class APIGatewayWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PublicAPIConfiguration publicUrls;

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		
		this.publicUrls.getPublicUrls().forEach(u -> this.addPublicUrl(http, u));
	}
	
	private void addPublicUrl(HttpSecurity http, String url) {
		try {
			http.authorizeRequests().antMatchers(url).permitAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
