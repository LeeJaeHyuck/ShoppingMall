package com.green.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.biz.member.MemberVO;
import com.green.biz.qna.QnaService;
import com.green.biz.qna.QnaVO;

@Controller
public class QnaController {

	@Autowired
	private QnaService service;

	@RequestMapping(value = "qna_list")
	public String qna_list(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("loginUser");

		if (user == null) {
			return "member/login";
		} else {
			model.addAttribute("qnaList", service.listQna(user.getId()));
			return "qna/qnaList";
		}
	}

	@RequestMapping(value = "qna_view")
	public String qnaView(@RequestParam(value = "qseq") int qseq, Model model, HttpSession session) {
		MemberVO user = (MemberVO) session.getAttribute("loginUser");

		if (user == null) {
			return "member/login";
		} else {
			QnaVO qna = service.getQna(qseq);
			model.addAttribute("qnaVO", qna);
			return "qna/qnaView";
		}
	}
	
	@RequestMapping(value="qna_write_form")
	public String qnaWriteVeiw(HttpSession session) {
		MemberVO user = (MemberVO) session.getAttribute("loginUser");

		if (user == null) {
			return "member/login";
		} else {
			return "qna/qnaWrite";
		}
	}

	@RequestMapping(value = "qna_write")
	public String qnaWrite(QnaVO vo, HttpSession session) {
		MemberVO user = (MemberVO) session.getAttribute("loginUser");
		if (user == null) {
			return "member/login";
		} else {
			vo.setId(user.getId());
			service.insertQna(vo);
			return "redirect:qna_list";
		}
	}
}
