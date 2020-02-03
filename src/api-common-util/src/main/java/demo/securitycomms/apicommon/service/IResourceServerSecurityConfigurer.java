package demo.securitycomms.apicommon.service;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface IResourceServerSecurityConfigurer {
	void configure(HttpSecurity http) throws Exception;
}
