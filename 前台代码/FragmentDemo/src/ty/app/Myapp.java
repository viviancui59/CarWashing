package ty.app;




import ty.entity.OrderList;
import ty.entity.User;
import android.app.Application;

public class Myapp extends Application {

	private String url="http://47.93.187.232:8080/CarWashing/";
	public String getUrl() {
		return url;
	}

	private User user;
	
	private OrderList orderlist;
	
	public OrderList getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(OrderList orderlist) {
		this.orderlist = orderlist;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
