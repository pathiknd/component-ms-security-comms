package demo.securitycomms.authserver.config;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public class CustomUserDetailsService implements UserDetailsService {
	
	private Map<String, User> users;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public CustomUserDetailsService() {
	}
	
	private void loadUsers() {
		Collection<String> adminRoles = new ArrayList<String>();
		adminRoles.add("ROLE_ADMIN");
		Collection<String> userRoles = new ArrayList<String>();
		userRoles.add("ROLE_USER");
		
		this.users = new HashMap<String, User>();
		this.users.put("admin", new User("admin", passwordEncoder.encode("password123"), adminRoles, false, false, true, false, "IND" , "Ram", "1100", "Asia\\Calcutta"));
		this.users.put("app_user", new User("app_user", passwordEncoder.encode("password123"), userRoles, false, false, true, false, "GBR", "John", "2200", "Britain\\London" ));		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(this.users == null) {
			this.loadUsers();
		}
		
		if(this.users.containsKey(username)) {
			return this.users.get(username);
		} else {
			System.out.println("not found");
			throw new UsernameNotFoundException("Username not found");
		}
	}

}
