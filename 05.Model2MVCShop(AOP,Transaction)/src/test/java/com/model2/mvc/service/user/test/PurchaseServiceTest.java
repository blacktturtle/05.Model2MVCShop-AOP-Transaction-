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
		purchase.setDivyAddr("���� ���ı�");
		purchase.setDivyDate("18/05/05");
		purchase.setDivyRequest("����ð���");
		purchase.setPaymentOption("1"); // ���ݱ���
		purchase.setReceiverName("�����");
		purchase.setReceiverPhone("010-6666-5555");
		purchase.setIsPurchaseCode(0); // ���� ���� ����
		purchase.setPurchaseProd(productService.getProduct(10247));
		purchase.setTranCode("1"); // ���ſϷ�
		purchase.setQuantity(30);

		purchaseService.addPurchase(purchase);

		// purchase = purchaseService.getPurchase(tranNo);
		//
		//
		// // ==> console Ȯ��
		// // System.out.println(user);
		//
		// // ==> API Ȯ��
		// Assert.assertEquals("fiileName", product.getFileName());
		// Assert.assertEquals("20180411", product.getManuDate());
		// Assert.assertEquals(3000, product.getPrice());
		// Assert.assertEquals("��������ǰ", product.getProdDetail());
		// Assert.assertEquals("������", product.getProdName());
		// Assert.assertEquals(10260, product.getProdNo());
		// Assert.assertEquals(50, product.getQuantity());
	}

	 //@Test
	public void testGetPurchase() throws Exception {

		Purchase purchase = new Purchase();
		purchase = purchaseService.getPurchase(10295);

		System.out.println("���� purchase : " + purchase);
	}

	// @Test
	public void testUpdatePurchase() throws Exception {
		Purchase purchase = purchaseService.getPurchase(10000);

		purchase.setDivyAddr("���� ��Ʈķ��");
		purchase.setDivyDate("18/07/07");
		purchase.setDivyRequest("���ֹ�");
		purchase.setPaymentOption("2");

		purchaseService.updatePurcahse(purchase);

		System.out.println("������Ʈ ��  purchase : " + purchase);

	}

	//@Test
	public void testGetPurchaseList() throws Exception {
		Purchase purchase = new Purchase();
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(5);
		
		System.out.println("��ġ���� : " + search);

		Map<String, Object> map = purchaseService.getPurchaseList(search, "user05");
		List<Object> list = (List<Object>) map.get("list");

		for (Object object : list) {
			System.out.println("�������� : " + object);
		}

		System.out.println("��Żī��Ʈ : " + map.get("totalCount"));

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
			System.out.println("�������� : " + object);
		}



	}

}