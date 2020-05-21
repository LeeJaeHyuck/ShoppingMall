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
	
	// return값: 주문 oseq
	@Override
	public int insertOrder(OrderVO vo) {
		// 주문 번호를 할당받는다.
		int oseq = selectMaxOseq(); 
		vo.setOseq(oseq);
		
		// 주문 테이블에 주문번호와 아이디를 저장한다.
		dao.insertOrder(vo);
		
		// 장바구니에서 목록을 가져온다.
		List<CartVO> cartList = cartService.listCart(vo.getId());
		
		// 장바구니에 있는 주문상세 내역을 order_detail 테이블에 저장한다.
		for (CartVO cVo : cartList) {
			System.out.println("장바구니 내역: " + cVo);
			
			OrderVO oVo = new OrderVO();
			oVo.setOseq(vo.getOseq()); // 주문번호 설정
			oVo.setPseq(cVo.getPseq()); // 상품번호 설정
			oVo.setQuantity(cVo.getQuantity()); // 상품개수 설정
			
			insertOrderDetail(oVo);
			
			// cart 테이블에 주문처리를 완료로 수정한다.
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
