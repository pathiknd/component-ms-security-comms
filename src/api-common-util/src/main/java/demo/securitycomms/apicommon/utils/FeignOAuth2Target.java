package demo.securitycomms.apicommon.utils;

import org.springframework.beans.factory.annotation.Autowired;

import feign.Request;
import feign.RequestTemplate;
import feign.Target;

public class FeignOAuth2Target<T> implements Target<T> {
	
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";	
	
    private Class<T> type;
    private String name;
    private String url;
    @Autowired
    private RibbonUrlRetriever urlRetriever;
  
    @SuppressWarnings("unchecked")
	public FeignOAuth2Target(Class<?> type, String name, String url) {
    	this.type = (Class<T>) type;
    	this.name = name;
    	this.url = url;
    }

	@Override
	public Class<T> type() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String url() {
		// TODO Auto-generated method stub
		return this.url;
	}

	@Override
	public Request apply(RequestTemplate input) {
//	      if (input.url().indexOf("http") != 0) {
//	          input.target(url());
//	        }
		
		if(this.urlRetriever == null) {
			this.urlRetriever = new RibbonUrlRetriever();
		}
		String baseUrl = this.urlRetriever.getUrlFromName(this.name);
		input.target(baseUrl);
		
		// TODO Auto-generated method stub
		input.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, SecurityUtil.getTokenValue()));
		return input.request();
	}

}
