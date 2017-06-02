package ty.service.impl;

import java.util.List;

import ty.dao.CarDao;
import ty.dao.OrderListDao;
import ty.dao.StoreDao;
import ty.dao.impl.CarDaoImpl;
import ty.dao.impl.OrderListDaoImpl;
import ty.dao.impl.StoreDaoImpl;
import ty.entity.OrderList;
import ty.service.OrderlistService;

public class OrderlistServiceImpl implements OrderlistService{

	private OrderListDao orderlistDao=new OrderListDaoImpl();
	private CarDao carDao=new CarDaoImpl();
	private StoreDao storeDao;
	
	public List<OrderList> displayFutureOrder(int userid) {
		// TODO Auto-generated method stub
		List<OrderList> list=orderlistDao.findFutureOrder(userid);
		for(OrderList o:list){
			storeDao=new StoreDaoImpl();
			o.setCarplate(carDao.findByCarId(o.getCarid()).getPlate());
			o.setStorename(storeDao.findByStoreId(o.getStoreid()).getName());
		}
		return list;
	}

	public List<OrderList> displayHistoryOrder(int userid) {
		// TODO Auto-generated method stub
		List<OrderList> list=orderlistDao.findHistoryOrder(userid);
		for(OrderList o:list){
			storeDao=new StoreDaoImpl();
			o.setCarplate(carDao.findByCarId(o.getCarid()).getPlate());
			o.setStorename(storeDao.findByStoreId(o.getStoreid()).getName());
		}
		return list;
	}

	public boolean cancelOrder(String orderid) {
		// TODO Auto-generated method stub
		return orderlistDao.cancelOrder(orderid);
	}

}
