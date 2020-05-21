package com.green.biz.order.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.order.OrderVO;

@Repository
public class OrderDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int selectMaxOseq() {
		System.out.println("==> Mybatis로 selectMaxOseq() 기능처리");
		return mybatis.selectOne("OrderDAO.selectMaxOseq");
	}
	
	public void insertOrder(OrderVO vo) {
		System.out.println("==> Mybatis로 insertOrder() 기능처리");
		mybatis.insert("OrderDAO.insertOrder", vo);
	}
	
	public void insertOrderDetail(OrderVO vo) {
		System.out.println("==> Mybatis로 insertOrderDetail() 기능처리");
		mybatis.insert("OrderDAO.insertOrderDetail", vo);
	}
	
	public List<OrderVO> listOrderById(OrderVO vo){
		System.out.println("==> Mybatis로 listOrderById() 기능처리");
		return mybatis.selectList("OrderDAO.listOrderById", vo);
	}
	
	public List<Integer> selectSeqOrdering(String id){
		System.out.println("==> Mybatis로 selectSeqOrdering() 기능처리");
		return mybatis.selectList("OrderDAO.selectSeqOrdering", id);
	}
	
	public List<OrderVO> listOrder(String member_name){
		System.out.println("==> Mybatis로 listOrder() 기능처리");
		return mybatis.selectList("OrderDAO.listOrder", member_name);
	}
	
	public void updateOrderResult(String oseq) {
		System.out.println("==> Mybatis로 updateOrderResult() 기능처리");
		mybatis.update("OrderDAO.updateOrderResult", oseq);
	}
	
	public List<Integer> allOrder(String id){
		System.out.println("==> Mybatis로 allOrder() 기능처리");
		return mybatis.selectList("OrderDAO.allOrder", id);
	}
	
	
}

