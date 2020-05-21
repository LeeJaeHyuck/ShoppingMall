package com.green.biz.product.dto;

import java.util.List;

public interface ProductService {
	
	List<ProductVO> getNewProductList();
	
	List<ProductVO> getBestProductList();
	
	ProductVO getProduct(ProductVO vo);
	
	List<ProductVO> getProductListByKind(ProductVO vo);
	
	int countProductList(ProductVO vo);
	
	List<ProductVO> listProduct(ProductVO vo);
	
	void insertProduct(ProductVO vo);
	
	void updateProduct(ProductVO vo);
	
	List<ProductVO> pagingProduct(Paging paging);
	
	
}
