package ty.service;

import java.util.List;

import ty.entity.Goods;

public interface GoodsService {

	List<Goods> displayAllGoods();
	
	List<Goods> displayGoodsByType(int type);
}
