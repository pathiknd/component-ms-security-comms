package demo.securitycomms.orderservice.common.dto;

public class OrderItemDto {
	
	private Integer itemId;
	private String description;
	private Boolean giftWrap;
	private Double rate;
	
	public void setItemId(Integer value) {
		this.itemId = value;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public void setGiftWrap(Boolean value) {
		this.giftWrap = value;
	}
	
	public void getRate(Double value) {
		this.rate = value;
	}
	
	public Integer getItemId() {
		return this.itemId;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Boolean getGiftWrap() {
		return this.giftWrap;
	}
	
	public Double getRate() {
		return this.rate;
	}

}
