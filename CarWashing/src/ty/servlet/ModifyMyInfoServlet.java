package ty.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ty.entity.User;
import ty.service.UserService;
import ty.service.impl.UserServiceImpl;

/**
 * Servlet implementation class ModifyMyInfoServlet
 */
public class ModifyMyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("成功连接");
		
		// TODO Auto-generated method stub
		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");	

		//获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();	   	

		//获取session对象---需要session对象时加此句
		HttpSession session = request.getSession();	

		//设置字符编码---需要字符转码时加此句
		request.setCharacterEncoding("utf-8"); 	
		
		//只传递三个必要参数？？？？
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		String gender=request.getParameter("gender");
		String userage=request.getParameter("userage");
		
		User user=new User();
		user.setUserid(Integer.parseInt(userid));
		user.setName(username);
		user.setGender(gender);
		user.setAge(Integer.parseInt(userage));
		
		UserService userservice=new UserServiceImpl();
		boolean flag=userservice.updateUserInfo(user);
		
		if(flag){
			//修改成功
			out.print("修改成功");
		}else{
			//修改失败
			out.print("修改失败");
		}
	}

}
