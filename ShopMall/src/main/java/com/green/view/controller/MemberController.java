package com.green.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.green.biz.address.AddressVO;
import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;

@Controller
@SessionAttributes("loginUser")
public class MemberController {

	@Autowired
	private MemberService service;

	@RequestMapping(value = "/contract", method = RequestMethod.GET)
	public String contractView() {
		return "member/contract";
	}

	@RequestMapping(value = "/join_form", method = RequestMethod.GET)
	public String joinView() {
		return "member/join";
	}

	@RequestMapping(value = "/join_form", method = RequestMethod.POST)
	public String joinForm() {
		return "member/join";
	}
	
	@RequestMapping(value = "/join", method=RequestMethod.POST)
	public String joinAction(MemberVO vo, Model model, @RequestParam(value="addr1")String addr1, @RequestParam(value="addr2")String addr2) {
		vo.setAddress(addr1 + " " + addr2);
		service.insertMember(vo);
		model.addAttribute("id", vo.getId());
		return "member/login";
	}

	@RequestMapping(value = "/id_check_form", method = RequestMethod.GET)
	public String idCheckView(@RequestParam(value = "id", defaultValue = "", required = false) String id, Model model) {
		model.addAttribute("id", id);
		return "member/idcheck";
	}

	@RequestMapping(value = "/id_check_form", method = RequestMethod.POST)
	public String idCheck(@RequestParam(value = "id", defaultValue = "", required = false) String id, Model model) {
		
		MemberVO vo = new MemberVO();
		vo.setId(id);

		if (service.getMember(vo) == null) {
			model.addAttribute("message", -1);
		} else {
			model.addAttribute("message", 1);
		}
		
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	@RequestMapping(value = "id_check_confirmed", method=RequestMethod.GET)
	public String idCheckConfirmed(MemberVO vo, Model model) {
		model.addAttribute("reid", vo.getId());
		System.out.println("123");
		return "member/join";
	}
	
	@RequestMapping(value="find_zip_num", method=RequestMethod.GET)
	public String findZipNum() {
		return "member/findZipNum";
	}
	
	@RequestMapping(value="find_zip_num", method=RequestMethod.POST)
	public String selectAddressByDong(AddressVO vo, Model model) {
		
		List<AddressVO> addressList = service.selectAddressByDong(vo);
		model.addAttribute("addressList", addressList);
		return "member/findZipNum";
	}
	
	@RequestMapping(value="/login_form", method=RequestMethod.GET)
	public String loginForm() {
		return "member/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginAction(MemberVO vo, Model model) {
		MemberVO user = service.getMember(vo);
		if (user != null) {
			if (user.getPwd().equals(vo.getPwd())) {
				model.addAttribute("loginUser", user);
				return "redirect:index";
			} else {
				return "member/login_fail";
			}
		} else {
			return "member/login_fail2";
		}
	}
	
       /* 	선생님표 로그인    */
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String loginAction(MemberVO vo, Model model) {
//		MemberVO user = service.loginMember(vo);
//		if (user != null) {
//			model.addAttribute("loginUser", user);
//				return "redirect:index";
//		} else {
//			return "member/login_fail2";
//		}
//	}
	
	@RequestMapping(value="/logout")
	public String logoutAction(SessionStatus status) {
		status.setComplete();
		
		return "redirect:/login_form";
	}
	
	@RequestMapping(value="find_id_form")
	public String findIdAndPassword() {
		return "member/findIdAndPassword";
	}
	
	@RequestMapping(value = "find_id", method = RequestMethod.GET)
	public String findIdAction(MemberVO vo, Model model) {
		
		MemberVO user = service.getMemberByNameAndEmail(vo);
		
		if (user != null) {
			model.addAttribute("id", user.getId());
			model.addAttribute("message", 1);
		} else {
			model.addAttribute("message", -1);
		}
		return "member/findResult";
	}
	
	@RequestMapping(value="find_password", method=RequestMethod.GET)
	public String findPassword(MemberVO vo, Model model) {
		MemberVO user = service.findPassword(vo);
		
		if (user != null) {
			model.addAttribute("pwd", user.getPwd());
			model.addAttribute("message", 1);
		}else {
			model.addAttribute("message", -1);
		}
		
		return "member/findPwResult";
	}
}












































