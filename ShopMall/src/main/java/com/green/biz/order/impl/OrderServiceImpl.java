package com.green.biz.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.order.CartService;
import com.green.biz.order.CartVO;
import com.green.biz.order.OrderService;
import com.green.biz.order.OrderVO;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO dao;
	
	@Autowired
	private CartService cartService;

	@Override
	public int selectMaxOseq() {
		return dao.selectMaxOseq();
	}
	
	// return��: �ֹ� oseq
	@Override
	public int insertOrder(OrderVO vo) {
		// �ֹ� ��ȣ�� �Ҵ�޴´�.
		int oseq = selectMaxOseq(); 
		vo.setOseq(oseq);
		
		// �ֹ� ���̺� �ֹ���ȣ�� ���̵� �����Ѵ�.
		dao.insertOrder(vo);
		
		// ��ٱ��Ͽ��� ����� �����´�.
		List<CartVO> cartList = cartService.listCart(vo.getId());
		
		// ��ٱ��Ͽ� �ִ� �ֹ��� ������ order_detail ���̺� �����Ѵ�.
		for (CartVO cVo : cartList) {
			System.out.println("��ٱ��� ����: " + cVo);
			
			OrderVO oVo = new OrderVO();
			oVo.setOseq(vo.getOseq()); // �ֹ���ȣ ����
			oVo.setPseq(cVo.getPseq()); // ��ǰ��ȣ ����
			oVo.setQuantity(cVo.getQuantity()); // ��ǰ���� ����
			
			insertOrderDetail(oVo);
			
			// cart ���̺� �ֹ�ó���� �Ϸ�� �����Ѵ�.
			cartService.updateCart(cVo.getCseq());
		}
		
		return oseq;
	}

	@Override
	public void insertOrderDetail(OrderVO vo) {
		dao.insertOrderDetail(vo);
	}

	@Override
	public List<OrderVO> listOrderById(OrderVO vo) {
		return dao.listOrderById(vo);
	}

	@Override
	public List<Integer> selectSeqOrdering(String id) {
		return dao.selectSeqOrdering(id);
	}

	@Override
	public List<OrderVO> listOrder(String member_name) {
		return dao.listOrder(member_name);
	}

	@Override
	public void updateOrderResult(String oseq) {
		dao.updateOrderResult(oseq);
	}

	@Override
	public List<Integer> allOrder(String id) {
		return dao.allOrder(id);
	}
}
