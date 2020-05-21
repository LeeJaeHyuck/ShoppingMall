package com.green.biz.qna.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.qna.QnaService;
import com.green.biz.qna.QnaVO;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaDAO dao;
	
	@Override
	public List<QnaVO> listQna(String id) {
		return dao.listQna(id);
	}

	@Override
	public QnaVO getQna(int qseq) {
		return dao.getQna(qseq);
	}

	@Override
	public void insertQna(QnaVO vo) {
		dao.insertQna(vo);
	}

	@Override
	public List<QnaVO> listAllQna() {
		return dao.listAllQna();
	}

	@Override
	public void updateQna(QnaVO vo) {
		dao.updateQna(vo);
	}

}
