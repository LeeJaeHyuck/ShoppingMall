package com.green.view.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.green.biz.admin.EmployeeVO;
import com.green.biz.admin.WorkerService;
import com.green.biz.member.MemberService;
import com.green.biz.order.OrderService;
import com.green.biz.order.OrderVO;
import com.green.biz.product.dto.Paging;
import com.green.biz.product.dto.ProductService;
import com.green.biz.product.dto.ProductVO;
import com.green.biz.qna.QnaService;
import com.green.biz.qna.QnaVO;

@Controller
@SessionAttributes("loginAdmin")
public class AdminController {

	@Autowired
	private WorkerService service;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private QnaService qnaService;

	@RequestMapping(value = "admin_login_form")
	public String adminLoginView() {
		return "admin/main";
	}

	@RequestMapping(value = "admin_login", method = RequestMethod.POST)
	public String adminLogin(@RequestParam(value = "workerId") String id, @RequestParam(value = "workerPwd") String pwd,
			Model model, HttpSession session) {
		int n = service.workerCheck(id, pwd);

		if (n == 1) {
			session.setAttribute("loginAdmin", service.goEmployee(id));
			return "redirect:admin_product_list";
		} else if (n == 0) {
			model.addAttribute("message", "비밀번호를 확인하세요.");
			return "admin/main";
		} else {
			model.addAttribute("message", "아이디를 확인하세요.");
			return "admin/main";
		}
	}

	@RequestMapping("admin_logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "admin/main";
	}

//	@RequestMapping("admin_product_list")
//	public String adminProductList(@RequestParam(value = "key", defaultValue = "") String key, ProductVO vo,
//			Model model, HttpSession session) {
//		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");
//
//		if (admin == null) {
//			return "admin/main";
//		} else {
//			vo.setName(key);
//			model.addAttribute("productList", productService.listProduct(vo));
//			return "admin/product/productList";
//		}
//	}
	
	@RequestMapping("admin_product_list")
	public String adminProductList(@RequestParam(value = "key", defaultValue = "") String key, ProductVO vo,
			Model model, HttpSession session,
			@RequestParam(value="nowPage", required=false) String nowPage,
			@RequestParam(value="cntPerPage", required=false) String cntPerPage) {
		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");

		if (admin == null) {
			return "admin/main";
		} else {
			vo.setName(key);
			int total = productService.countProductList(vo);
			
			if (nowPage == null && cntPerPage == null) {
				nowPage = "1";
				cntPerPage = "5";
			} else if (nowPage == null) {
				nowPage = "1";
			} else if (cntPerPage == null) {
				cntPerPage = "5";
			}
			
			Paging paging = new Paging(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			paging.setName(key);
			model.addAttribute("paging", paging);
			model.addAttribute("list", productService.pagingProduct(paging));
			return "admin/product/productList_org";
		}
	}

	@RequestMapping("admin_product_write_form")
	public String adminProductWrtieForm(Model model, HttpSession session) {
		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");

		if (admin == null) {
			return "admin/main";
		} else {
			String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sale" };
			model.addAttribute("kindList", kindList);
			return "admin/product/productWrite";
		}
	}

	@RequestMapping(value = "admin_product_write")
	public String adminProductWrite(ProductVO vo,
			@RequestParam(value = "images", required = false) MultipartFile images, HttpSession session)
			throws Exception {
		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");

		if (admin == null) {
			return "admin/main";
		} else {
			if (!images.isEmpty()) {
				String fileName = images.getOriginalFilename();
				images.transferTo(new File(
						"D:spring_workspace/ShopMall/src/main/webapp/WEB-INF/resources/product_images/" + fileName));
				vo.setImage(fileName);
			}

			productService.insertProduct(vo);

			return "redirect:admin_product_list";
		}
	}

	@RequestMapping(value = "admin_product_detail")
	public String adminProductDetail(ProductVO vo, Model model, HttpSession session) {
		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");

		if (admin == null) {
			return "admin/main";
		} else {
			ProductVO product = productService.getProduct(vo);
			int kind = Integer.parseInt(product.getKind());
			String[] kindList = { "o", "Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sale" };
			model.addAttribute("productVO", product);
			model.addAttribute("kind", kindList[kind]);
			return "admin/product/productDetail";
		}
	}

	@RequestMapping(value = "admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model, HttpSession session) {
		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");

		if (admin == null) {
			return "admin/main";
		} else {
			ProductVO product = productService.getProduct(vo);
			String[] kindList = { "Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sale" };
			model.addAttribute("productVO", product);
			model.addAttribute("kindList", kindList);
			return "admin/product/productUpdate";
		}
	}

	@RequestMapping(value = "admin_product_update")
	public String adminUpdateProduct(ProductVO vo, @RequestParam(value = "images") MultipartFile images,
			HttpSession session) throws Exception {
		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");

		if (admin == null) {
			return "admin/main";
		} else {
			if (vo.getBestyn() == null) {
				vo.setBestyn("n");
			}

			if (vo.getUseyn() == null) {
				vo.setUseyn("n");
			}

			if (!images.isEmpty()) {
				String fileName = images.getOriginalFilename();
				images.transferTo(new File(
						"D:spring_workspace/ShopMall/src/main/webapp/WEB-INF/resources/product_images/" + fileName));
				vo.setImage(fileName);
			}

			productService.updateProduct(vo);
			return "redirect:admin_product_list";
		}
	}
	
	@RequestMapping(value = "admin_order_list")
	public String adminOrderList(@RequestParam(value = "key", defaultValue = "") String key, Model model,
			HttpSession session) {
		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");

		if (admin == null) {
			return "admin/main";
		} else {
			List<OrderVO> orderList = orderService.listOrder(key);
			model.addAttribute("orderList", orderList);

			return "admin/order/orderList";
		}
	}

	@RequestMapping(value = "admin_member_list")
	public String adminMemberList(@RequestParam(value = "key", defaultValue = "") String key, Model model,
			HttpSession session) {
		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");

		if (admin == null) {
			return "admin/main";
		} else {
			model.addAttribute("memberList", memberService.listMember(key));
			return "admin/member/memberList";
		}
	}

	@RequestMapping(value = "admin_order_save")
	public String adminOrderSave(@RequestParam(value = "result", required = false) String[] result,
			HttpSession session) {
		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");

		if (admin == null) {
			return "admin/main";
		} else {
			for (int i = 0; i < result.length; i++) {
				orderService.updateOrderResult(result[i]);
			}
			return "redirect:admin_order_list";
		}
	}

	@RequestMapping(value = "admin_qna_list")
	public String adminQnaList(Model model, HttpSession session) {
		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");

		if (admin == null) {
			return "admin/main";
		} else {
			model.addAttribute("qnaList", qnaService.listAllQna());
			return "admin/qna/qnaList";
		}
	}

	@RequestMapping(value = "admin_qna_detail")
	public String adminQnaDetail(QnaVO qna, Model model, HttpSession session) {
		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");

		if (admin == null) {
			return "admin/main";
		} else {
			model.addAttribute("qnaVO", qnaService.getQna(qna.getQseq()));
			return "admin/qna/qnaDetail";
		}
	}

	@RequestMapping(value = "admin_qna_resave")
	public String adminQnaResave(QnaVO qna, HttpSession session) {
		EmployeeVO admin = (EmployeeVO) session.getAttribute("loginAdmin");

		if (admin == null) {
			return "admin/main";
		} else {
			qnaService.updateQna(qna);
			return "redirect:admin_product_list";
		}
	}
}
