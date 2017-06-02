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

public class FindCarServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");	

		PrintWriter out = response.getWriter();	   	

		request.setCharacterEncoding("utf-8"); 
				
		
		int userid = Integer.parseInt(request.getParameter("userid"));
		// System.out.println(userid+"");
		//int  userid=1;
		UserService carService=new UserServiceImpl();
		List<Car> carlist=carService.findUserCar(userid);
		
      
		if(carlist!=null)
		{
			Gson gson = new Gson();
			
			String carJson = gson.toJson(carlist);
			out.println(carJson);
			System.out.println(carJson);		
		}else{
			System.out.println("1");
			Gson gson = new Gson();	
	  		String carJson =gson.toJson(null);
	  		
			out.println(carJson);
		}
		
	}

}
