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
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

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
public class PurchaseServiceTest {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	PurchaseService purchaseService;

	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;

	@Autowired
	@Qualifier("productServiceImpl")
	ProductService productService;

	// @Test
	public void testAddPurchase() throws Exception {

		Purchase purchase = new Purchase();
		purchase.setBuyer(userService.getUser("user05"));
		purchase.setDivyAddr("서울 송파구");
		purchase.setDivyDate("18/05/05");
		purchase.setDivyRequest("부재시경비실");
		purchase.setPaymentOption("1"); // 현금구매
		purchase.setReceiverName("이장원");
		purchase.setReceiverPhone("010-6666-5555");
		purchase.setIsPurchaseCode(0); // 구매 안한 상태
		purchase.setPurchaseProd(productService.getProduct(10247));
		purchase.setTranCode("1"); // 구매완료
		purchase.setQuantity(30);

		purchaseService.addPurchase(purchase);

		// purchase = purchaseService.getPurchase(tranNo);
		//
		//
		// // ==> console 확인
		// // System.out.println(user);
		//
		// // ==> API 확인
		// Assert.assertEquals("fiileName", product.getFileName());
		// Assert.assertEquals("20180411", product.getManuDate());
		// Assert.assertEquals(3000, product.getPrice());
		// Assert.assertEquals("괜찮은상품", product.getProdDetail());
		// Assert.assertEquals("갤럭시", product.getProdName());
		// Assert.assertEquals(10260, product.getProdNo());
		// Assert.assertEquals(50, product.getQuantity());
	}

	 //@Test
	public void testGetPurchase() throws Exception {

		Purchase purchase = new Purchase();
		purchase = purchaseService.getPurchase(10295);

		System.out.println("들어온 purchase : " + purchase);
	}

	// @Test
	public void testUpdatePurchase() throws Exception {
		Purchase purchase = purchaseService.getPurchase(10000);

		purchase.setDivyAddr("강남 비트캠프");
		purchase.setDivyDate("18/07/07");
		purchase.setDivyRequest("재주문");
		purchase.setPaymentOption("2");

		purchaseService.updatePurcahse(purchase);

		System.out.println("업데이트 된  purchase : " + purchase);

	}

	//@Test
	public void testGetPurchaseList() throws Exception {
		Purchase purchase = new Purchase();
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(5);
		
		System.out.println("서치정보 : " + search);

		Map<String, Object> map = purchaseService.getPurchaseList(search, "user05");
		List<Object> list = (List<Object>) map.get("list");

		for (Object object : list) {
			System.out.println("구매정보 : " + object);
		}

		System.out.println("토탈카운트 : " + map.get("totalCount"));

	}
	@Test
	public void testGetDeliveryList() throws Exception{
		Purchase purchase = new Purchase();
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(5);
		
		Map<String, Object> map = purchaseService.getDeliveryList(search, 10284);
		List<Object> list = (List<Object>)map.get("list");
		for (Object object : list) {
			System.out.println("구매정보 : " + object);
		}



	}

}