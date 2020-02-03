package demo.securitycomms.authserver.config;

import org.springframework.security.core.userdetails.UserDetails;

public interface IAppUserDetails extends UserDetails {
	String getCountryCode();
}
