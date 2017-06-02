package ty.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ty.entity.User;
import ty.service.UserService;
import ty.service.impl.UserServiceImpl;

import com.google.gson.Gson;

public class UserLoginServlet extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");	

		//获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();	   	

		//设置字符编码---需要字符转码时加此句
		request.setCharacterEncoding("utf-8"); 
				

		String phonenum = request.getParameter("userphonenum");
		String password = request.getParameter("password");
		
		//String phonenum="18202220722";
		//String password="123";
		
		System.out.println(phonenum);
		System.out.println(password);
		UserService userService=new UserServiceImpl();
		User user =userService.login(phonenum, password);
		
		
		
		if(user!=null)
		{
			user.setCar(userService.findUserCar(user.getUserid()));
			Gson gson = new Gson();
			
			String userJson = gson.toJson(user);
			
			System.out.println(userJson);
			out.println(userJson);
			//System.out.println(userJson);		
		}else{
			Gson gson = new Gson();	
	  		String userJSON =gson.toJson(null);
	  		
			out.println(userJSON);
		}
	}

}
