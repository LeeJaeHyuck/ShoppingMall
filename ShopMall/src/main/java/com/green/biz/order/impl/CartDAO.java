package com.green.biz.order.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.order.CartVO;

@Repository
public class CartDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public void insertCart(CartVO vo) {
		System.out.println("==> mybatis로 insertCart() 기능처리");
		mybatis.insert("CartDAO.insertCart", vo);
	}

	public List<CartVO> listCart(String id) {
		System.out.println("==> mybatis로 listCart() 기능처리");
		return mybatis.selectList("CartDAO.listCart", id);
	}

	public void deleteCart(int cseq) {
		System.out.println("==> mybatis로 deleteCart() 기능처리");
		mybatis.delete("CartDAO.deleteCart", cseq);
	}

	public void updateCart(int cseq) {
		System.out.println("==> mybatis로 updateCart() 기능처리");
		mybatis.update("CartDAO.updateCart", cseq);
	}
}
