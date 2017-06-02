package ty.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ty.entity.Message;
import ty.service.MessageService;
import ty.service.impl.MessageServiceImpl;

/**
 * Servlet implementation class ShowMessageServlet
 */
public class ShowMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMessageServlet() {
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
		
		MessageService messageservice=new MessageServiceImpl();
		List<Message> list=messageservice.displayMessageList(Integer.parseInt(userid));
		
		if(list!=null){
			//查找成功
			Gson gson = new Gson();
			
			String messagelist = gson.toJson(list);
							
			out.println(messagelist);
							
			out.flush();
		}else{
			//查找失败
			
			
		}
	}

}
