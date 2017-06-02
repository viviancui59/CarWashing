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
import ty.dao.impl.CarBrandDaoImpl;
import ty.entity.Car;
import ty.entity.CarBrand;

public class FindCarBrandServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");	

		PrintWriter out = response.getWriter();	   	

		request.setCharacterEncoding("utf-8"); 
				
		
		String userid = request.getParameter("carbrand");
		// System.out.println(userid+"");
	//	int  userid=1;
		CarBrandDao CarBrandService=new CarBrandDaoImpl();
		List<CarBrand> carbrandlist=CarBrandService.findAllCarBrand();
		
      
		if(carbrandlist!=null)
		{
			Gson gson = new Gson();
			
			String carbrandJson = gson.toJson(carbrandlist);
			out.println(carbrandJson);
			System.out.println(carbrandJson);		
		}else{
			System.out.println("1");
			Gson gson = new Gson();	
	  		String carJson =gson.toJson(null);
	  		
			out.println(carJson);
		}
		
	}

}
