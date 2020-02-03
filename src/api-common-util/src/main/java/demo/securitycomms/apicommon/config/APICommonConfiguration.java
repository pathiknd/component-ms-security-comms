package demo.securitycomms.apicommon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

import demo.securitycomms.apicommon.utils.SecurityUtil;
import demo.securitycomms.apicommon.utils.UserDetails;

@Configuration
public class APICommonConfiguration {

	@Bean(name = "userDetails")
	//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@RequestScope
	public UserDetails getUserDetails() {
		UserDetails ud = SecurityUtil.getUserDetailsFromPrincipal();
		System.out.println(ud.toString());
		return ud;
	}
}
