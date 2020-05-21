package com.green.biz.product.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.product.dto.Paging;
import com.green.biz.product.dto.ProductVO;

@Repository
public class ProductDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public List<ProductVO> getNewProductList() {
		System.out.println("==> Mybatis�� getNewProductList() ���ó��");

		return mybatis.selectList("ProductDAO.getNewProductList");
	}

	public List<ProductVO> getBestProductList() {
		System.out.println("==> Mybatis�� getBestProductList() ���ó��");

		return mybatis.selectList("ProductDAO.getBestProductList");
	}

	public ProductVO getProduct(ProductVO vo) {
		System.out.println("==> Mybatis�� getProduct() ���ó��");

		return mybatis.selectOne("ProductDAO.getProduct", vo);
	}

	public List<ProductVO> getProductListByKind(ProductVO vo) {
		System.out.println("==> Mybatis�� getProductListByKind() ���ó��");

		return mybatis.selectList("ProductDAO.getProductListByKind", vo);
	}
	
	public int countProductList(ProductVO vo) {
		System.out.println("==> Mybatis�� countProductList() ���ó��");
		return mybatis.selectOne("ProductDAO.countProductList", vo);
	}
	
	public List<ProductVO> listProduct(ProductVO vo){
		System.out.println("==> Mybatis�� listProduct() ���ó��");
		return mybatis.selectList("ProductDAO.listProduct", vo);
	}
	
	public void insertProduct(ProductVO vo) {
		System.out.println("==> Mybatis�� insertProduct() ���ó��");
		mybatis.insert("ProductDAO.insertProduct", vo);
	}
	
	public void updateProduct(ProductVO vo) {
		System.out.println("==> Mybatis�� updateProduct() ���ó��");
		mybatis.update("ProductDAO.updateProduct", vo);
	}
	
	public List<ProductVO> pagingProduct(Paging paging){
		return mybatis.selectList("ProductDAO.pagingProduct", paging);
	}
}
