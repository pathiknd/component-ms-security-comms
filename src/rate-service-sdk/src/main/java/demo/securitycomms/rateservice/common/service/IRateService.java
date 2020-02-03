package demo.securitycomms.rateservice.common.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import demo.securitycomms.rateservice.common.request.GetRateRequest;
import demo.securitycomms.rateservice.common.response.GetRateResponse;
import feign.RequestLine;

public interface IRateService {
	
	@GetMapping("/rates/get-rate")
	Double getExchangeRate();
	
	@PostMapping("/rates/get-rates")
	GetRateResponse GetRate(@RequestBody GetRateRequest request);
}
