package ty.service.impl;

import java.util.List;

import ty.dao.GoodsDao;
import ty.dao.impl.GoodsDaoImpl;
import ty.entity.Goods;
import ty.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {

	GoodsDao goodsDao=new GoodsDaoImpl();
	
	public List<Goods> displayAllGoods() {
		// TODO Auto-generated method stub
		return goodsDao.findAllGoods();
	}

	public List<Goods> displayGoodsByType(int type) {
		// TODO Auto-generated method stub
		return goodsDao.findByGoodsType(type);
	}

}
