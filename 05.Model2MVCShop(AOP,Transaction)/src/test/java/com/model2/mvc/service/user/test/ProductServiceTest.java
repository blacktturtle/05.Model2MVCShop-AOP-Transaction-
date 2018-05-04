package com.model2.mvc.service.user.test;

import java.sql.Date;
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
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

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
public class ProductServiceTest {
	
	@Autowired
	@Qualifier("productServiceImpl")
	ProductService productService;

	@Test
	public void testAddProduct() throws Exception {

		Product product = new Product();
		product.setFileName("fiileName");
		product.setManuDate("20180411");
		product.setPrice(3000);
		product.setProdDetail("��������ǰ");
		product.setProdName("������");
		product.setQuantity(50);

		productService.addProduct(product);
//
//		product = productService.getProduct(10260);
//		

		// ==> console Ȯ��
		// System.out.println(user);

		// ==> API Ȯ��
//		Assert.assertEquals("fiileName", product.getFileName());
//		Assert.assertEquals("20180411", product.getManuDate());
//		Assert.assertEquals(3000, product.getPrice());
//		Assert.assertEquals("��������ǰ", product.getProdDetail());
//		Assert.assertEquals("������", product.getProdName());
//		Assert.assertEquals(10260, product.getProdNo());
//		Assert.assertEquals(50, product.getQuantity());
	}
	
	//@Test
	public void testGetProduct() throws Exception{
		Product product = new Product();
		
		product = productService.getProduct(10260);
		
		Assert.assertEquals("fiileName", product.getFileName());
		Assert.assertEquals("20180411", product.getManuDate());
		Assert.assertEquals(3000, product.getPrice());
		Assert.assertEquals("��������ǰ", product.getProdDetail());
		Assert.assertEquals("������", product.getProdName());
		Assert.assertEquals(10260, product.getProdNo());
		Assert.assertEquals(50, product.getQuantity());
		
		Assert.assertNotNull(productService.getProduct(10260));
		Assert.assertNotNull(productService.getProduct(10264));
		
	}
	
	//@Test
	public void testGetProduct2() throws Exception{
		Product product = new Product();
		
		product = productService.getProduct2(10256);
		System.out.println("���δ�Ʈ ��� : " + product);
		
		
		Assert.assertNotNull(productService.getProduct2(10256));
		
	}
	//@Test
	public void testUpdateProduct() throws Exception {
		Product product = productService.getProduct(10260);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("fiileName", product.getFileName());
		Assert.assertEquals("20180411", product.getManuDate());
		Assert.assertEquals(3000, product.getPrice());
		Assert.assertEquals("��������ǰ", product.getProdDetail());
		Assert.assertEquals("������", product.getProdName());
		Assert.assertEquals(10260, product.getProdNo());
		Assert.assertEquals(50, product.getQuantity());
		
		product.setFileName("changeName");
		product.setManuDate("20180520");
		product.setPrice(5555);
		product.setProdDetail("��ǰ");
		product.setProdName("������");
		product.setQuantity(30);
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10260);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("changeName", product.getFileName());
		Assert.assertEquals("20180520", product.getManuDate());
		Assert.assertEquals(5555, product.getPrice());
		Assert.assertEquals("��ǰ", product.getProdDetail());
		Assert.assertEquals("������", product.getProdName());
		Assert.assertEquals(10260, product.getProdNo());
		Assert.assertEquals(30, product.getQuantity());
	}
	
	//@Test
	public void testGetProductListAll() throws Exception{
		Search search = new Search();
		search.setCurrentPage(2);
		search.setPageSize(5);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		search.setPriceUpDown(0);
		
		Map<String, Object> map = productService.getProductList(search);
		List<Object> list = (List<Object>)map.get("list");
		
		for (Object object : list) {
			System.out.println(object);
			
		}
		
		//Assert.assertEquals(5, list.size());
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println("��Żī��Ʈ ��  ?" + totalCount);

	}
	
	//@Test
	public void testGetProductListByKeyword() throws Exception{
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(5);
		search.setSearchCondition("2");
		search.setSearchKeyword("00");
		
		Map<String, Object> map = productService.getProductList(search);
		List<Object> list = (List<Object>)map.get("list");
		for (Object object : list) {
			System.out.println(object);
		}
		//Assert.assertEquals(1, list.size());
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println("��Żī��Ʈ ��  ?" + totalCount);
		
	}

}