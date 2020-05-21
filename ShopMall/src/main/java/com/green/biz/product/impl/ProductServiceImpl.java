package com.green.biz.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.product.dto.Paging;
import com.green.biz.product.dto.ProductService;
import com.green.biz.product.dto.ProductVO;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO dao;
	
	@Override
	public List<ProductVO> getNewProductList() {
		return dao.getNewProductList();
	}

	@Override
	public List<ProductVO> getBestProductList() {
		return dao.getBestProductList();
	}

	@Override
	public ProductVO getProduct(ProductVO vo) {
		return dao.getProduct(vo);
	}

	@Override
	public List<ProductVO> getProductListByKind(ProductVO vo) {
		return dao.getProductListByKind(vo);
	}

	@Override
	public int countProductList(ProductVO vo) {
		return dao.countProductList(vo);
	}

	@Override
	public List<ProductVO> listProduct(ProductVO vo) {
		return dao.listProduct(vo);
	}

	@Override
	public void insertProduct(ProductVO vo) {
		dao.insertProduct(vo);
	}

	@Override
	public void updateProduct(ProductVO vo) {
		dao.updateProduct(vo);
	}

	@Override
	public List<ProductVO> pagingProduct(Paging paging) {
		return dao.pagingProduct(paging);
	}
	

}
