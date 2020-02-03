package demo.securitycomms.rateservice.common.response;

import java.util.List;

import demo.securitycomms.rateservice.common.dto.ItemRateDto;

public class GetRateResponse {
	
	private List<ItemRateDto> items;
	
	public void setItems(List<ItemRateDto> value) {
		this.items = value;
	}
	
	public List<ItemRateDto> getItems(){
		return this.items;
	}

}
