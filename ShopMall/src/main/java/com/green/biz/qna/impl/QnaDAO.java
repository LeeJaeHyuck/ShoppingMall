package com.green.biz.qna.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.qna.QnaVO;

@Repository
public class QnaDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public List<QnaVO> listQna(String id) {
		System.out.println("==> Mybatis�� listQna() ���ó��");
		return mybatis.selectList("qnaDAO.listQna", id);
	}

	public QnaVO getQna(int qseq) {
		System.out.println("==> Mybatis�� getQna() ���ó��");
		return mybatis.selectOne("qnaDAO.getQna", qseq);
	}

	public void insertQna(QnaVO vo) {
		System.out.println("==> Mybatis�� insertQna() ���ó��");
		mybatis.insert("qnaDAO.insertQna", vo);
	}
	
	public List<QnaVO> listAllQna() {
		System.out.println("==> Mybatis�� listAllQna() ���ó��");
		return mybatis.selectList("qnaDAO.listAllQna");
	}
	
	public void updateQna(QnaVO vo) {
		System.out.println("==> Mybatis�� updateQna() ���ó��");
		mybatis.update("qnaDAO.updateQna", vo);
	}
}
