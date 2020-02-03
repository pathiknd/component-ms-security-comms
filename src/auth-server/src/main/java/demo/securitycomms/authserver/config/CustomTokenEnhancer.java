package demo.securitycomms.authserver.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {
	
	@Autowired
	private CustomClientDetailsService clientService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		System.out.println("enhancing token..");
		System.out.println(authentication.getOAuth2Request().getGrantType());
		if(authentication.getOAuth2Request().getGrantType().equals("password")) {
			User u = (User) authentication.getPrincipal();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("country_code", u.getCountryCode());
			params.put("id", u.getUserId());
			params.put("name", u.getName());
			params.put("tz", u.getTimezone());
			params.put("type", "user");
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(params);
			return accessToken;
		} else {
			//System.out.println("client credentials");
			Map<String, Object> params = this.clientService.getClientDetailsById(authentication.getPrincipal().toString());
			//params.put("country_code", accessToken.getAdditionalInformation().get("country_code").toString());
			params.put("type", "client");
			//System.out.println("client credentials 2");
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(params);
			return accessToken;
		}
		
	}

}
