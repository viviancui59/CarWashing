package ty.app;




import ty.entity.OrderList;
import ty.entity.User;
import android.app.Application;

public class Myapp extends Application {

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
