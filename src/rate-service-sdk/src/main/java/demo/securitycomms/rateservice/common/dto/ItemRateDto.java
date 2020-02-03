package demo.securitycomms.rateservice.common.dto;

public class ItemRateDto {
	
	private Integer itemId;
	private Double rate;
	
	public void setItemId(Integer value) {
		this.itemId = value;
	}
	
	public void setRate(Double value) {
		this.rate = value;
	}
	
	public Integer getItemId() {
		return this.itemId;
	}
	
	public Double getRate() {
		return this.rate;
	}

}
