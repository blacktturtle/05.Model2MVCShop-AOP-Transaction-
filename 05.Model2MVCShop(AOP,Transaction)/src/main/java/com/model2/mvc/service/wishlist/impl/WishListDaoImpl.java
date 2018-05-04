package com.model2.mvc.service.wishlist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.WishList;
import com.model2.mvc.service.wishlist.WishListDao;

@Repository("wishListDaoImpl")
public class WishListDaoImpl implements WishListDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlsession;

	public void setWishListDao(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	public WishListDaoImpl() {
	}

	@Override
	public void addWishList(WishList wishList) throws Exception {
		sqlsession.insert("WishListMapper.addWishList", wishList);
	}

	@Override
	public WishList getWishList(int prodNo) throws Exception {
		return sqlsession.selectOne("WishListMapper.getWishList", prodNo);
	}

	@Override
	public List<Object> getAllWishList(Search search, String userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("userId", userId);
		return sqlsession.selectList("WishListMapper.getAllWishList", map);
	}

	@Override
	public void deleteWishList(int wishNo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTotalCount(String buyerId) throws Exception {
		return sqlsession.selectOne("WishListMapper.getTotalCount",buyerId);
	}

}
