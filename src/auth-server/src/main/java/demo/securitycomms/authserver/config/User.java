package demo.securitycomms.authserver.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements IAppUserDetails {
	
	private Collection<? extends GrantedAuthority> authorities;
	private Collection<String> roles;
	private String password;
	private String username;
	private Boolean accountExpired;
	private Boolean accountLocked;
	private Boolean accountEnabled;
	private Boolean credentialExpired;
	private String countryCode;
	private String name;
	private String userId;
	private String tz;
	
	public User() {
		
	}
	
	public User(String username,
			String password,
			Collection<String> roles,
			Boolean accountExpired,
			Boolean accountLocked,
			Boolean accountEnabled,
			Boolean credentialExpired,
			String countryCode,
			String name,
			String userId,
			String timezone
			) {
		
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.accountExpired = accountExpired;
		this.accountLocked = accountLocked;
		this.accountEnabled = accountEnabled;
		this.credentialExpired = credentialExpired;
		this.countryCode = countryCode;
		this.name = name;
		this.userId = userId;
		this.tz = timezone;
		
		
		this.setGrantedAuthority(this.roles);
	}
	
	public void setRoles(Collection<String> value) {
		this.roles = value;
	}
	
	private void setGrantedAuthority(Collection<String> value) {
		ArrayList<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
		value.forEach(s -> authList.add(new SimpleGrantedAuthority(s)));
		this.authorities = authList;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public void setAccountExpired(Boolean value) {
		this.accountExpired = value;
	}
	
	public void setAccountLocked(Boolean value) {
		this.accountLocked = value;
	}
	
	public void setAccountEnabled(Boolean value) {
		this.accountEnabled = value;
	}
	
	public void setCredentialExpired(Boolean value) {
		this.credentialExpired = value;
	}
	
	public void setCountryCode(String value) {
		this.countryCode = value;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public void setUserId(String value) {
		this.userId = value;
	}
	
	public void setTimezone(String value) {
		this.tz = value;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return !this.accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !this.accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return !this.credentialExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.accountEnabled;
	}

	@Override
	public String getCountryCode() {
		// TODO Auto-generated method stub
		return this.countryCode;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}
	
	public String getTimezone() {
		// TODO Auto-generated method stub
		return this.tz;
	}

}
