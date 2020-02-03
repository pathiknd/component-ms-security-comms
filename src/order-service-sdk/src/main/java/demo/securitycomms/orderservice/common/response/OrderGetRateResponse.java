package demo.securitycomms.orderservice.common.response;

public class OrderGetRateResponse {
	
	private Integer userId;
	private Integer itemId;
	private Double rate;
	
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	public void setItemId(Integer value) {
		this.itemId =value;
	}
	
	public void setRate(Double value) {
		this.rate = value;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public Integer getItemId() {
		return this.itemId;
	}
	
	public Double getRate() {
		return this.rate;
	}

}
