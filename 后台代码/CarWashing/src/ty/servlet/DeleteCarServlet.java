package ty.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ty.entity.Car;
import ty.service.UserService;
import ty.service.impl.UserServiceImpl;

public class DeleteCarServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");	

		PrintWriter out = response.getWriter();	   	

		request.setCharacterEncoding("utf-8"); 
				
		int userid = Integer.parseInt(request.getParameter("userid"));
		int carid =Integer.parseInt(request.getParameter("carid"));
		System.out.println(carid);
		
		UserService carService=new UserServiceImpl();
		//UserService userService=new UserServiceImpl();
	//	List<OrderList> orderlist=orderlistService.getMyOrderList(userid);
		
      
		if(carService.deleteCar(carid, userid))
		{
			List<Car> carlist=carService.findUserCar(userid);
			Gson gson = new Gson();
			String orderJson = gson.toJson(carlist);
			out.println(orderJson);
			System.out.println(orderJson);		
		}else{
			Gson gson = new Gson();	
	  		String orderJson =gson.toJson(null);
	  		
			out.println(orderJson);
		}
		
	}

}
