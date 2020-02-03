package demo.securitycomms.apicommon.utils;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class SecurityUtil {
	
	public static <T> T as(Class<T> t, Object o) {
		  return t.isInstance(o) ? t.cast(o) : null;
	}
	
	public static UserDetails getUserDetailsFromPrincipal() {
		System.out.println("Retreiving user details from token..");
		 OAuth2Authentication auth = SecurityUtil.as(OAuth2Authentication.class, SecurityContextHolder.getContext().getAuthentication());
		 if(auth != null) {
				//System.out.println("retreiving user details 2..");
			 OAuth2AuthenticationDetails authDetails = SecurityUtil.as(OAuth2AuthenticationDetails.class, auth.getDetails());
			 if(authDetails != null) {
					//System.out.println("retreiving user details 3..");
				 Map<String, Object> attributes = (Map<String, Object>) authDetails.getDecodedDetails();
				 
				 UserDetails ud = new UserDetails();
				 if(attributes.containsKey("country_code")) {
					 ud.countryCode = attributes.get("country_code").toString();
				 }
				 
				 if(attributes.containsKey("user_name")) {
					 ud.username = attributes.get("user_name").toString();
				 }
				 
				 if(attributes.containsKey("name")) {
					 ud.name = attributes.get("name").toString();
				 }
				 
				 if(attributes.containsKey("id")) {
					 ud.userId = attributes.get("id").toString();
				 }
				 
				 if(attributes.containsKey("tz")) {
					 ud.timezone = attributes.get("tz").toString();
				 }
				 
				 if(attributes.containsKey("type")) {
					 ud.type = attributes.get("type").toString();
				 }
				 
				 System.out.println(ud.toString());
				 return ud;				 
			 }

		 } else {
			 System.out.println("Authentication not found..");
		 }
		 return null;
	}
	
	public static String getTokenValue() {
		 OAuth2Authentication auth = SecurityUtil.as(OAuth2Authentication.class, SecurityContextHolder.getContext().getAuthentication());
		 if(auth != null) {
			 OAuth2AuthenticationDetails authDetails = SecurityUtil.as(OAuth2AuthenticationDetails.class, auth.getDetails());
			 if(authDetails != null) {
				 return authDetails.getTokenValue();			 
			 }
		 }
		 return null;
	}

}
