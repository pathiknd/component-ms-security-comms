package demo.securitycomms.rateservice.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.securitycomms.apicommon.utils.UserDetails;
import demo.securitycomms.rateservice.common.dto.ItemRateDto;
import demo.securitycomms.rateservice.common.request.GetRateRequest;
import demo.securitycomms.rateservice.common.response.GetRateResponse;
import demo.securitycomms.rateservice.common.service.IRateService;
import demo.securitycomms.userservice.common.client.UserServiceProxy;
import demo.securitycomms.userservice.common.response.GetUserDetailsResponse;
import demo.securitycomms.userservice.common.service.IUserService;

@RestController("IRateService")
@RequestMapping("rates")
public class RateServiceController implements IRateService {

	@Autowired
	private UserServiceProxy userServiceClient;
	
	//@Override
	@GetMapping("get-rate")
	@PreAuthorize("hasRole('ADMIN')")
	public Double getExchangeRate() {
		// TODO Auto-generated method stub
		return 104.35;
	}
	
	@GetMapping("closing-rate")
	public Double getClosingRate() {
		return 100.54;
	}
	
	//("#user.countryCode == 'IND'")
	@GetMapping("tx-rate/{ref}")
	@PreAuthorize("@userDetails.isFromCountry('IND')")
	public Double getUserTxRate(@PathVariable(name = "ref", required = false) String txRef) {
		return 100.00;		
	}
	
	@GetMapping("user-rate/{username}")
	public String getRateForUser(@PathVariable(name="username", required = true) String username) {
		return this.userServiceClient.getObject().getUserByUserName(username);
	}

	@Override
	@PostMapping("get-rates")
	public GetRateResponse GetRate(@RequestBody GetRateRequest request) {
		// TODO Auto-generated method stub
		System.out.println("get-rates/" + request.getUserId().toString());
		
		GetUserDetailsResponse udResponse = this.userServiceClient.getObject().getUserDetails(request.getUserId());
		GetRateResponse response = new GetRateResponse();
		
		List<ItemRateDto> list = new ArrayList<ItemRateDto>();
		Integer[] items = request.getItems();
		
		for(int i=0; i < items.length; i++) {
			ItemRateDto d = new ItemRateDto();
			d.setItemId(items[i]);
			d.setRate(udResponse.getUser().getPremiumCustomer() ? items[i] * 1.10 : items[i] * 1.25);
			list.add(d);
		}
		
		response.setItems(list);
		return response;
	}
}
