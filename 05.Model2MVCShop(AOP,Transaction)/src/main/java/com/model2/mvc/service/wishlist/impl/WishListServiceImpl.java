package com.model2.mvc.service.wishlist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.WishList;
import com.model2.mvc.service.wishlist.WishListDao;
import com.model2.mvc.service.wishlist.WishListService;

@Service("wishListServiceImpl")
public class WishListServiceImpl implements WishListService {
	
	@Autowired
	@Qualifier("wishListDaoImpl")
	WishListDao wishListDao;
	
	public void setWishListDao(WishListDao wishListDao) {
		this.wishListDao = wishListDao;		
	}

	@Override
	public void addWishList(WishList wishList) throws Exception {
		wishListDao.addWishList(wishList);
	}

	@Override
	public WishList getWishList(int prodNo) throws Exception {
		return wishListDao.getWishList(prodNo);
	}

	@Override
	public Map<String, Object> getAllWishList(Search search, String userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> list = (List<Object>)wishListDao.getAllWishList(search, userId);
		int totalCount = wishListDao.getTotalCount(userId);
		
		map.put("list", list);
		map.put("totalCount", totalCount);
		return map;
	}

	@Override
	public void deleteWishList(int wishNo) throws Exception {
		// TODO Auto-generated method stub

	}

}
