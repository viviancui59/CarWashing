package ty.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ty.dao.CarDao;
import ty.dao.OrderListDao;
import ty.dao.impl.CarDaoImpl;
import ty.dao.impl.OrderListDaoImpl;
import ty.entity.OrderList;
import ty.util.StringUtil;

public class OrderWashServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");	

		PrintWriter out = response.getWriter();	   	

		request.setCharacterEncoding("utf-8"); 
		
		CarDao cardao=new CarDaoImpl();
		OrderListDao orderlistdao = new OrderListDaoImpl();
		
		String orderid=StringUtil.convertFilename();
		String plate = request.getParameter("plate");
		String time = request.getParameter("time");
		int userid = Integer.parseInt(request.getParameter("userid"));
		float price = Integer.parseInt(request.getParameter("price"));
		int type = Integer.parseInt(request.getParameter("type"));
		int storeid = Integer.parseInt(request.getParameter("storeid"));
		
		int carid=cardao.findCarIdByPlate(plate);
		
		OrderList orderlist=new OrderList();
		orderlist.setOrderid(orderid);
		orderlist.setCarid(carid);
		orderlist.setPrice(price);
		orderlist.setType(type);
		orderlist.setUserid(userid);
		orderlist.setStoreid(storeid);
		orderlist.setTime(time);
		
		if(orderlistdao.insertOrder(orderlist))
		{
			Gson gson = new Gson();
			String orderJSON = gson.toJson(orderlist);		
			out.println(orderJSON);
		}
		else{
			System.out.println("1");
			Gson gson = new Gson();	
	  		String orderJSON =gson.toJson(null);
	  		
			out.println(orderJSON);
		}
	}

}
