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
		System.out.println("==> mybatis�� insertCart() ���ó��");
		mybatis.insert("CartDAO.insertCart", vo);
	}

	public List<CartVO> listCart(String id) {
		System.out.println("==> mybatis�� listCart() ���ó��");
		return mybatis.selectList("CartDAO.listCart", id);
	}

	public void deleteCart(int cseq) {
		System.out.println("==> mybatis�� deleteCart() ���ó��");
		mybatis.delete("CartDAO.deleteCart", cseq);
	}

	public void updateCart(int cseq) {
		System.out.println("==> mybatis�� updateCart() ���ó��");
		mybatis.update("CartDAO.updateCart", cseq);
	}
}
