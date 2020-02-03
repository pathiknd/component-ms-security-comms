package demo.securitycomms.orderservice.common.response;

import java.util.List;

import demo.securitycomms.orderservice.common.dto.OrderItemDto;

public class GetOrderDetailsResponse {
	
	private Integer userId;
	private String userName;
	private List<OrderItemDto> items;
	
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	public void setUserName(String value) {
		this.userName = value;
	}
	
	public void setOrderItems(List<OrderItemDto> value) {
		this.items = value;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public List<OrderItemDto> getItems(){
		return this.items;
	}

}
