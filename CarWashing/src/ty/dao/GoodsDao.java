package ty.dao;

import java.util.List;

import ty.entity.Goods;

public interface GoodsDao {

	//增加商品信息 传入Goods 返回是否成功
	boolean insertGoods(Goods goods);
	
	//根据goodsid删除商品信息 返回是否成功
	boolean deleteGoods(int goodsid);
	
	//传入goods 对某goodsid的商品更改商品信息 返回是否成功
	boolean updateGoods(Goods goods);
	
	//查找全部商品信息
	List<Goods> findAllGoods();
	
	//返回某类型的商品信息	  1-配件         2-内饰  	3电子
	List<Goods> findByGoodsType(int type);
	
}
