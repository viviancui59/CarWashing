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

import ty.entity.Goods;
import ty.service.GoodsService;
import ty.service.impl.GoodsServiceImpl;

/**
 * Servlet implementation class GoodsTabServlet
 */
public class GoodsTabServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsTabServlet() {
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
		
		int type = Integer.parseInt(request.getParameter("type"));

		GoodsService goodsService=new GoodsServiceImpl();
		List<Goods> list=new ArrayList<Goods>();
		if(type==0){
			//查看全部
			list=goodsService.displayAllGoods();
		}else{
			//查看某类别
			list=goodsService.displayGoodsByType(type);
		}
		
		if(list!=null){
			//查找成功
			Gson gson = new Gson();
			
			String goodslist = gson.toJson(list);
							
			out.println(goodslist);
							
			out.flush();
		}else{
			//查找失败
			
			
		}

	}

}
