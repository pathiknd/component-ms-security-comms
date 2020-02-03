package demo.securitycomms.common.proxy;

import demo.securitycomms.apicommon.utils.SecurityUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignTokenRelayInterceptor  implements RequestInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate template) {
    	template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, SecurityUtil.getTokenValue()));
    }	

}
