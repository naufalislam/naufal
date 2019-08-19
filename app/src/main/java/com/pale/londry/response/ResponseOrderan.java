package com.pale.londry.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseOrderan{

	@SerializedName("status")
	private boolean status;

	@SerializedName("order")
	private List<OrderItem> order;

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public void setOrder(List<OrderItem> order){
		this.order = order;
	}

	public List<OrderItem> getOrder(){
		return order;
	}

	@Override
 	public String toString(){
		return 
			"ResponseOrderan{" + 
			"status = '" + status + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}