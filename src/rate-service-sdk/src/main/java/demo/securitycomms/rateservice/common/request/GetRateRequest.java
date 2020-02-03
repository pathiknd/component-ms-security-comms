package demo.securitycomms.rateservice.common.request;

public class GetRateRequest {
	
	private Integer userId;
	private Integer[] items;
	private String type;
	
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	public void setItems(Integer[] value) {
		this.items = value;
	}
	
	public void setType(String value) {
		this.type = value;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public Integer[] getItems() {
		return this.items;
	}
	
	public String getType() {
		return this.type;

	}

}
