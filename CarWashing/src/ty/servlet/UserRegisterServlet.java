package ty.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ty.entity.User;
import ty.service.UserService;
import ty.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserRegisterServlet
 */
public class UserRegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");	

		//获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();	   	

		//设置字符编码---需要字符转码时加此句
		request.setCharacterEncoding("utf-8"); 
		
				
				String userphonenum=request.getParameter("userphonenum");
			    String username = request.getParameter("username");
			    String password= request.getParameter("password");
			//    String usertype= request.getParameter("usertype");  //int
			 //   int usertype=Integer.parseInt( request.getParameter("usertype"));  //int
			  
			    String gender=request.getParameter("usergender");
			    int age=Integer.parseInt(request.getParameter("userage"));
			 //   System.out.println(age+"");
			  //创建并填充实体bean
			    System.out.println("start");
		  		User user = new User();
		  		
		  	//	int i_userid=Integer.parseInt(userid);
		  		/*user.setUsername(username);
		  		user.setPassword(password);
		  		user.setUserid(i_userid);
		  		user.setUsertype(usertype);
		  		//user.setEmail(email);
		  		user.setEmail(phoneNum);*/
		  		
		  		user.setAge(age);
		  		user.setGender(gender);
		  		user.setName(username);
		  		user.setPassword(password);
		  		user.setPhonenum(userphonenum);
		 // 	user.setType(usertype);
		  		
		  		
		  		
		  		System.out.println(user.getPhonenum());

			  		  
			  //组合业务对象
			  	UserService userService = new UserServiceImpl();
			  //检测重复
			  	if(userService.checkUserPhoneNum(userphonenum))
			  	{
			  		 System.out.println("该用户已注册，请重新输入");	
					
				}else{
					//调用业务方法
				  	user = userService.register(user);
			        if(user!=null){   //注册成功
					
			    //    Gson gson = new Gson();		

					//String userJSON = gson.toJson(user);
					//String userJson = gson.toJson(user);
						   
					

					Gson gson = new Gson();
					
					String userJson = gson.toJson(user);
					
					
					out.println(userJson);
					 System.out.println(userJson);
					System.out.println(user.getUserid());
					
					 System.out.println("恭喜，注册成功，系统将在三秒后跳转到主页");
								
					}else{
						Gson gson = new Gson();	
				  		String userJSON =gson.toJson(null);
				  		
						out.println(userJSON);
						 System.	out.println("注册失败，请重新输入");		
					}
				}

		
	}

}
