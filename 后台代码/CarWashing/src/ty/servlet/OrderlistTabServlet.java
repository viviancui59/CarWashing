package ty.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ty.entity.OrderList;
import ty.service.OrderlistService;
import ty.service.impl.OrderlistServiceImpl;

/**
 * Servlet implementation class OrderlistTabServlet
 */
public class OrderlistTabServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderlistTabServlet() {
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
		
		//传入flag 1代表未完成  2代表历史
		int flag = Integer.parseInt(request.getParameter("flag"));
		int userid=Integer.parseInt(request.getParameter("userid"));

		OrderlistService orderlistService=new OrderlistServiceImpl();
		List<OrderList> list=new ArrayList<OrderList>();
		if(flag==1){
			//查看未完成
			list=orderlistService.displayFutureOrder(userid);
		}else{
			//查看历史
			list=orderlistService.displayHistoryOrder(userid);
		}
		
		if(list!=null){
			//查找成功
			Gson gson = new Gson();
			
			String orderlistlist = gson.toJson(list);
							
			out.println(orderlistlist);
							
			out.flush();
		}else{
			//查找失败
			
			
		}

	}

}
