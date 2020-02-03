package demo.securitycomms.orderservice.common.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import demo.securitycomms.orderservice.common.response.GetOrderDetailsResponse;
import demo.securitycomms.orderservice.common.response.OrderGetRateResponse;

public interface IOrderService {
	
	@GetMapping("/orders/get-order-details")
	GetOrderDetailsResponse getOrderDetails(@PathVariable Integer orderId);
	
	@GetMapping("/orders/get-rate/{userId}")
	OrderGetRateResponse getRate(@PathVariable Integer userId, @RequestParam("itemId") Integer itemId);
}
