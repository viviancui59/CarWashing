package ty.dao;

import java.util.List;

import ty.entity.OrderList;


public interface OrderListDao {

	boolean insertOrder(OrderList orderlist);//添加一个订单
	boolean cancelOrder(String orderid);//取消一个订单，在表中删除
	
	OrderList findFutureOrder(int userid);//用户界面中，根据userid显示未完成的订单信息
	OrderList findHistoryOrder(int userid);//用户界面中，根据userid显示历史订单
	
	
    List<OrderList> findAllHistory();//在管理员界面中显示所有用户的历史订单
    List<OrderList> findAllFuture();//在管理员界面中显示所有用户的未完成订单
   
}
