package com.green.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.biz.product.dto.ProductService;
import com.green.biz.product.dto.ProductVO;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;

	// RequestParam으로 pseq값 넘겨주기
//	@RequestMapping(value = "/product_detail", method = RequestMethod.GET)
//	public String productDetailAction(@RequestParam(value="pseq", defaultValue="0", required=true) String pseq, Model model) {
//		
//		ProductVO pVo = new ProductVO();
//		pVo.setPseq(Integer.parseInt(pseq));
//		model.addAttribute("productVO", service.getProduct(pVo));
//
//		return "product/productDetail";
//	}
	
	@RequestMapping(value = "/product_detail", method = RequestMethod.GET)
	public String productDetailAction(ProductVO vo, Model model) {
		
		model.addAttribute("productVO", service.getProduct(vo));

		return "product/productDetail";
	}
	
	@RequestMapping(value="category")
	public String ProductKindAction(ProductVO vo, Model model) {
		List<ProductVO> list = service.getProductListByKind(vo);
		model.addAttribute("productKindList", list);
		
		return "product/productKind";
	}
	
	
}
