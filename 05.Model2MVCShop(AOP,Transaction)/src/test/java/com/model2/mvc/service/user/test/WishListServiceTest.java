package com.model2.mvc.service.user.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.domain.WishList;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.wishlist.WishListService;

/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  
		"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml", 
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class WishListServiceTest {

	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;

	@Autowired
	@Qualifier("productServiceImpl")
	ProductService productService;
	
	@Autowired
	@Qualifier("wishListServiceImpl")
	WishListService wishListService;

	// @Test
	public void testAddWishList() throws Exception {
		WishList wishList = new WishList();
		wishList.setWishIsPurchaseCode(0);
		wishList.setWishListBuyer(userService.getUser("user05"));
		wishList.setWishListProd(productService.getProduct(10288));
		
		wishListService.addWishList(wishList);	
	}
	
	//@Test
	public void testGetWishList() throws Exception {
		WishList wishList = new WishList();
		wishList = wishListService.getWishList(10284);
		
		System.out.println("ã�ƿ� wishList : " + wishList);

	}

	@Test
	public void testGetAllWishList() throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(5);		
		
		Map<String, Object> map =wishListService.getAllWishList(search, "user05");
		List<WishList> list = (List<WishList>)map.get("list");
		
		for (WishList wishList : list) {
			System.out.println("����Ʈ ��� : " + wishList);
		}
		
		int totalCount = (int) map.get("totalCount");
		
		System.out.println("��Żī��Ʈ : " + totalCount);
	}
	//@Test
	public void testGetDeliveryList() throws Exception{




	}

}