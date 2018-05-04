package com.model2.mvc.service.product.impl;

import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	
	
	public ProductDaoImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addProduct(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProduct",product);
		
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct",prodNo);
	}

	@Override
	public Product getProduct2(int tranNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct2",tranNo);
	}
	
	@Override
	public List<Product> getProductList(Search search) throws Exception {
		List<Product> list = sqlSession.selectList("ProductMapper.getProductList",search);
		return list;
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProduct",product);
		
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
			return sqlSession.selectOne("ProductMapper.getTotalCount",search);
	}




}
