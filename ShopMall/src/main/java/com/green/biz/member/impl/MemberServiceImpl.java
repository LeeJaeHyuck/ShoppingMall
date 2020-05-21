package com.green.biz.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.address.AddressVO;
import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;

@Service("memeberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao;

	@Override
	public MemberVO getMember(MemberVO vo) {
		return dao.getMember(vo.getId());
	}

	@Override
	public void insertMember(MemberVO vo) {
		dao.insertMember(vo);
	}

	@Override
	public List<AddressVO> selectAddressByDong(AddressVO vo) {
		return dao.selectAddressByDong(vo);
	}

	@Override
	public MemberVO loginMember(MemberVO vo) {
		return dao.loginMember(vo);
	}

	@Override
	public MemberVO getMemberByNameAndEmail(MemberVO vo) {
		return dao.getMemberByNameAndEmail(vo);
	}

	@Override
	public MemberVO findPassword(MemberVO vo) {
		return dao.findPassword(vo);
	}

	@Override
	public List<MemberVO> listMember(String member_name) {
		return dao.listMember(member_name);
	}

}
