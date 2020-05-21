package com.green.biz.member;

import java.util.List;

import com.green.biz.address.AddressVO;

public interface MemberService {
	
	MemberVO getMember(MemberVO vo);

	void insertMember(MemberVO vo);
	
	List<AddressVO> selectAddressByDong(AddressVO vo);
	
	MemberVO loginMember(MemberVO vo);
	
	MemberVO getMemberByNameAndEmail(MemberVO vo);
	
	MemberVO findPassword(MemberVO vo);
	
	List<MemberVO> listMember(String member_name);
}
