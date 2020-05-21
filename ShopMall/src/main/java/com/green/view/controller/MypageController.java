package com.green.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.biz.member.MemberVO;
import com.green.biz.order.CartService;
import com.green.biz.order.CartVO;
import com.green.biz.order.OrderService;
import com.green.biz.order.OrderVO;

@Controller
public class MypageController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/cart_list", method = RequestMethod.GET)
	public String listCart(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");

		if (mVo == null) {
			return "member/login";
		} else {
			List<CartVO> list = cartService.listCart(mVo.getId());
			int price = 0;

			for (CartVO cVo : list) {
				price += (cVo.getPrice2() * cVo.getQuantity());
			}

			model.addAttribute("cartList", list);
			model.addAttribute("totalPrice", price);
			return "mypage/cartList";
		}
	}

	@RequestMapping(value = "/cart_insert", method = RequestMethod.POST)
	public String goCart(CartVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO) session.getAttribute("loginUser");

		if (user == null) {
			return "member/login";
		} else {
			vo.setId(user.getId());
			cartService.insertCart(vo);
			System.out.println(vo);
			return "redirect:cart_list";
		}
	}

	@RequestMapping(value = "/cart_delete")
	public String cartDelete(@RequestParam(value = "cseq") int[] cseq, Model model) {

		for (int i = 0; i < cseq.length; i++) {
			cartService.deleteCart(cseq[i]);
		}
		
		return "redirect:cart_list";
	}
	
	@RequestMapping(value="order_insert")
	public String orderInsert(HttpSession session, OrderVO vo, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("loginUser");
		if(user == null) {
			return "member/login";
		} else {
			vo.setId(user.getId());
			
			// 주문 테이블에 주문내역을 저장한다.
			int oseq = orderService.insertOrder(vo);
			vo.setResult("1");
			model.addAttribute("oseq", oseq);
		}
		
		return "redirect:order_list";
	}
	
	@RequestMapping(value = "order_list")
	public String orderListAction(@RequestParam(value = "oseq") int oseq, Model model, HttpSession session, OrderVO vo) {
		MemberVO user = (MemberVO) session.getAttribute("loginUser");

		if (user == null) {
			return "member/login";
		} else {
			vo.setId(user.getId());
			vo.setOseq(oseq);
			vo.setResult("1");
			List<OrderVO> orderList = orderService.listOrderById(vo);
			int price = 0;

			for (OrderVO order : orderList) {
				price += (order.getQuantity() * order.getPrice2());
			}

			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", price);

			return "mypage/orderList";

		}
	}
	
	@RequestMapping(value = "mypage")
	public String myPageView(HttpSession session, Model model, OrderVO order) {
		MemberVO user = (MemberVO) session.getAttribute("loginUser");

		if (user == null) {
			return "member/login";
		} else {
			List<Integer> oseqList = orderService.selectSeqOrdering(user.getId());
			List<OrderVO> orderList = new ArrayList<OrderVO>();

			for (int oseq : oseqList) {
				order.setId(user.getId());
				order.setOseq(oseq);
				
				// 주문 변호별 주문내역 조회
				List<OrderVO> listBySeq = orderService.listOrderById(order);
				
				// 각 주문별 내용 축약
				OrderVO vo = new OrderVO();
				vo.setIndate(listBySeq.get(0).getIndate());
				vo.setOseq(listBySeq.get(0).getOseq());
				if (listBySeq.size() > 1) {
					vo.setPname(listBySeq.get(0).getPname() + " 외 " + (listBySeq.size() - 1) + "건");
				} else {
					vo.setPname(listBySeq.get(0).getPname());
				}
				
				int totalPrice = 0;
				for (int i=0; i<listBySeq.size(); i++) {
					totalPrice += listBySeq.get(i).getPrice2() * listBySeq.get(i).getQuantity();
				}
				
				vo.setPrice2(totalPrice);
				
				orderList.add(vo);
				
			}

			model.addAttribute("title", "진행 중인 주문 내역");
			model.addAttribute("orderList", orderList);

			return "mypage/mypage";
		}
	}
	
	@RequestMapping(value="/order_detail")
	public String orderDetailVeiw(HttpSession session, OrderVO vo, Model model) {

		MemberVO user = (MemberVO) session.getAttribute("loginUser");

		if (user == null) {
			return "member/login";
		} else {
			vo.setId(user.getId());
			vo.setResult("1");
			List<OrderVO> orderList = orderService.listOrderById(vo);
			int price = 0;
			
			for (OrderVO order : orderList) {
				price += (order.getPrice2() * order.getQuantity());
			}
			
			model.addAttribute("orderDetail", orderList.get(0));
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", price);
			
			return "mypage/orderDetail";
		}
	}

	@RequestMapping("order_all")
	public String orderAll(HttpSession session, OrderVO order, Model model) {

		MemberVO user = (MemberVO) session.getAttribute("loginUser");

		if (user == null) {
			return "member/login";
		} else {
			List<Integer> oseqList = orderService.allOrder(user.getId());
			List<OrderVO> orderList = new ArrayList<OrderVO>();

			for (int oseq : oseqList) {
				order.setId(user.getId());
				order.setOseq(oseq);
				
				// 주문 변호별 주문내역 조회
				List<OrderVO> listBySeq = orderService.listOrderById(order);
				
				// 각 주문별 내용 축약
				OrderVO vo = new OrderVO();
				vo.setIndate(listBySeq.get(0).getIndate());
				vo.setOseq(listBySeq.get(0).getOseq());
				if (listBySeq.size() > 1) {
					vo.setPname(listBySeq.get(0).getPname() + " 외 " + (listBySeq.size() - 1) + "건");
				} else {
					vo.setPname(listBySeq.get(0).getPname());
				}
				
				int totalPrice = 0;
				for (int i=0; i<listBySeq.size(); i++) {
					totalPrice += listBySeq.get(i).getPrice2() * listBySeq.get(i).getQuantity();
				}
				
				vo.setPrice2(totalPrice);
				
				orderList.add(vo);
				
			}

			model.addAttribute("title", "총 주문 내역");
			model.addAttribute("orderList", orderList);

			return "mypage/mypage";
		}
	}

}
