package ty.service;

import java.util.List;

import ty.entity.OrderList;

public interface OrderlistService {

	List<OrderList> displayFutureOrder(int userid);
	
	List<OrderList> displayHistoryOrder(int userid);
	
	boolean cancelOrder(String orderid);
}
