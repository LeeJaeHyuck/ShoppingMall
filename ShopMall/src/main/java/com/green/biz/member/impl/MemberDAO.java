package com.green.biz.member.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.address.AddressVO;
import com.green.biz.member.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public MemberVO getMember(String id) {
		System.out.println("==> Mybatis�� getMember() ��� ó��");
		return mybatis.selectOne("MemberDAO.getMember", id);
	}

	public void insertMember(MemberVO vo) {
		System.out.println("==> Mybatis�� insertMember() ��� ó��");
		mybatis.insert("MemberDAO.insertMember", vo);
	}
	
	public List<AddressVO> selectAddressByDong(AddressVO vo) {
		System.out.println("==> Mybatis�� selectAddressByDong() ��� ó��");
		return mybatis.selectList("MemberDAO.selectAddressByDong", vo);
	}
	
	public MemberVO loginMember(MemberVO vo) {
		System.out.println("==> Mybatis�� loginMember() ��� ó��");
		return mybatis.selectOne("MemberDAO.loginMember", vo);
	}
	
	public MemberVO getMemberByNameAndEmail(MemberVO vo) {
		System.out.println("==> Mybatis�� getMemberByNameAndEmail() ��� ó��");
		return mybatis.selectOne("MemberDAO.getMemberByNameAndEmail", vo);
	}
	
	public MemberVO findPassword(MemberVO vo) {
		System.out.println("==> Mybatis�� findPassword() ��� ó��");
		return mybatis.selectOne("MemberDAO.findPassword", vo);
	}
	
	public List<MemberVO> listMember(String member_name){
		System.out.println("==> Mybatis�� listMember() ��� ó��");
		return mybatis.selectList("MemberDAO.listMember", member_name);
	}
}
