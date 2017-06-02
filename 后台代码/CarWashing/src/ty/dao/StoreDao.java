package ty.dao;

import java.util.List;

import ty.entity.Store;

public interface StoreDao {

	//返回全部店面信息
	List<Store> findAllStore();
	
	//插入店面信息 返回是否成功
	boolean insertStore(Store store);
	
	//根据storeid删除店面信息 返回是否成功
	boolean deleteStore(int storeid);
	
	//根据storeid查找店面信息 返回Store
	Store findByStoreId(int storeid);
	
}
