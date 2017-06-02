package ty.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ty.dao.CarBrandDao;
import ty.dao.CarDao;
import ty.dao.impl.CarBrandDaoImpl;
import ty.dao.impl.CarDaoImpl;
import ty.entity.Car;
import ty.entity.CarBrand;
import ty.service.CarService;
import ty.service.UserService;
import ty.service.impl.CarServiceImpl;
import ty.service.impl.UserServiceImpl;

public class InsertCarServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");	

		PrintWriter out = response.getWriter();	   	

		request.setCharacterEncoding("utf-8"); 
				
		int userid = Integer.parseInt(request.getParameter("userid"));
		String txtBrand= request.getParameter("txtBrand");
		String plate= request.getParameter("txtPlate");
	//	System.out.println(carid);
		Car car=new Car();
		
		CarService carService=new CarServiceImpl();
		UserService userService=new UserServiceImpl();
		CarDao carservice=new CarDaoImpl();
		//UserService userService=new UserServiceImpl();
	//	List<OrderList> orderlist=orderlistService.getMyOrderList(userid);
		
      CarBrandDao carbrandservice=new CarBrandDaoImpl();
      CarBrand carbrand=carbrandservice.findByCarBrandtype(txtBrand);
      car.setCarbrandid(carbrand.getCarbrandid());
      car.setBrandtype(carbrand.getType());
    car.setPlate(plate);
      
		if(carservice.insertCar(car))
		{
			car.setCarid(carservice.findCarIdByPlate(car.getPlate()));
			if(userService.insertCar(car, userid)){
			List<Car> carlist=userService.findUserCar(userid);
			Gson gson = new Gson();
			String orderJson = gson.toJson(carlist);
			out.println(orderJson);
			System.out.println(orderJson);	
			}
			else
			{
				Gson gson = new Gson();	
		  		String orderJson =gson.toJson(null);
		  		
				out.println(orderJson);
			}
		}else{
			Gson gson = new Gson();	
	  		String orderJson =gson.toJson(null);
	  		
			out.println(orderJson);
		}
		
	}

}
