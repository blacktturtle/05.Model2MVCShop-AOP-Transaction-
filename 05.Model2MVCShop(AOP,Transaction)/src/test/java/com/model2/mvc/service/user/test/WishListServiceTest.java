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
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
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
		
		System.out.println("찾아온 wishList : " + wishList);

	}

	@Test
	public void testGetAllWishList() throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(5);		
		
		Map<String, Object> map =wishListService.getAllWishList(search, "user05");
		List<WishList> list = (List<WishList>)map.get("list");
		
		for (WishList wishList : list) {
			System.out.println("리스트 출력 : " + wishList);
		}
		
		int totalCount = (int) map.get("totalCount");
		
		System.out.println("토탈카운트 : " + totalCount);
	}
	//@Test
	public void testGetDeliveryList() throws Exception{




	}

}