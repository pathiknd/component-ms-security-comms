package demo.securitycomms.apicommon.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import demo.securitycomms.apicommon.service.IResourceServerSecurityConfigurer;

@Configuration
@Order(5)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
    @Autowired
    private CustomAccessTokenConverter customAccessTokenConverter;
    
    @Autowired(required = false)
    private List<IResourceServerSecurityConfigurer> securityConfigurers;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
    	
    	try
    	{
	    	http.httpBasic().disable();
	    	
	    	if(this.securityConfigurers != null) {
	    		this.securityConfigurers.forEach(c -> {
					try {
						
						System.out.println("Calling configure on " + c.getClass().getSimpleName());
						c.configure(http);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
	    	} else {
	    		System.out.println("No security configureres found...");
	    	}
	    	
	    	http.authorizeRequests().anyRequest().authenticated();
    	}
    	catch(Exception ex)
    	{
    		//log and re-throw
    	}
    	
    	
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(customAccessTokenConverter);

        converter.setSigningKey("key123#@");
        // final Resource resource = new ClassPathResource("public.txt");
        // String publicKey = null;
        // try {
        // publicKey = IOUtils.toString(resource.getInputStream());
        // } catch (final IOException e) {
        // throw new RuntimeException(e);
        // }
        // converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }	

}
