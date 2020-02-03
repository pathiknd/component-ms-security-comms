package demo.securitycomms.orderservice.web.rest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.securitycomms.orderservice.common.dto.OrderItemDto;
import demo.securitycomms.orderservice.common.response.GetOrderDetailsResponse;
import demo.securitycomms.orderservice.common.response.OrderGetRateResponse;
import demo.securitycomms.orderservice.common.service.IOrderService;
import demo.securitycomms.rateservice.common.request.GetRateRequest;
import demo.securitycomms.rateservice.common.response.GetRateResponse;
import demo.securitycomms.rateservice.common.service.IRateService;
import demo.securitycomms.rateservice.comon.client.RateServiceProxy;
import demo.securitycomms.userservice.common.client.GroupServiceProxy;
import demo.securitycomms.userservice.common.client.UserServiceProxy;
import demo.securitycomms.userservice.common.response.GetGroupDetailsResponse;
import demo.securitycomms.userservice.common.response.GetUserDetailsResponse;

@RestController
@RequestMapping("orders")
public class OrderController implements IOrderService {
	
	@Autowired
	private RateServiceProxy rateServiceClient;
	
	@Autowired
	private UserServiceProxy userServiceClient;
	
	@Autowired
	private GroupServiceProxy groupServiceClient;
	
	@Override
	@GetMapping("get-order-details/{orderId}")
	@PostAuthorize("!@userDetails.isClient()")
	public GetOrderDetailsResponse getOrderDetails(@PathVariable Integer orderId) {
		// TODO Auto-generated method stub
		System.out.println("get-order-details/" + orderId.toString());
		
		GetOrderDetailsResponse response = new GetOrderDetailsResponse();
		
		GetUserDetailsResponse udResponse = this.userServiceClient.getObject().getUserDetails(orderId);
		response.setUserId(udResponse.getUser().getUserId());
		response.setUserName(udResponse.getUser().getName());
		
		List<OrderItemDto> items = new ArrayList<OrderItemDto>();
		OrderItemDto item1 = new OrderItemDto();
		item1.setDescription("Item 1");
		item1.setGiftWrap(true);
		item1.setItemId(11);
		
		items.add(item1);
		response.setOrderItems(items);
		
		return response;
	}

	@Override
	@GetMapping("get-rate/{userId}")
	@PreAuthorize("hasRole('USER')")
	public OrderGetRateResponse getRate(@PathVariable Integer userId, @RequestParam("itemId") Integer itemId) {
		// TODO Auto-generated method stub
		System.out.println("get-rate/" + userId.toString() + "&" + itemId.toString());
		
		GetRateRequest request = new GetRateRequest();
		Integer[] items = new Integer[1];
		items[0] = itemId;
		
		request.setUserId(userId);
		request.setItems(items);
		request.setType(null);
		GetRateResponse grResponse = this.rateServiceClient.getObject().GetRate(request);
		
		OrderGetRateResponse response = new OrderGetRateResponse();
		response.setItemId(itemId);
		response.setUserId(userId);
		response.setRate(grResponse.getItems().get(0).getRate());
		
		return response;
	}
	
	@GetMapping("get-group-size/{groupName}")
	public Integer getGroupMemberCount(@PathVariable String groupName) {
		GetGroupDetailsResponse gdResponse = this.groupServiceClient.getObject().getGroupDetails(groupName);
		return gdResponse.getGroup().getMemberCount();
	}
	
	@GetMapping("random")
	public String getRandom() {
		return "Order " + Instant.now().toString();
	}	
	
	
//	@GetMapping("rate-for-order")
//	public Double getRateForOrder() {
//		Object o = rateServiceClient.getObject();
//		if(o == null) {
//			System.out.println("rateServiceClient.getClient() is null");
//		}
//		return rateServiceClient.getObject().getExchangeRate();
//	}
//	
//	@PostMapping("push-order")
//	@PreAuthorize("@userDetails.isClient()")
//	public String pushOrder() {
//		return "Success";
//	}
//	

//	
//	@GetMapping("order-user/{orderRef}")
//	public String getUserForOrder(@PathVariable String orderRef) {
//		return this.userServiceClient.getObject().getUserByUserName(orderRef);
//	}
}
