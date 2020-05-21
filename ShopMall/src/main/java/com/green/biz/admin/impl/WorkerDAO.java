package com.green.biz.admin.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.admin.EmployeeVO;

@Repository
public class WorkerDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public String workerCheck(String id) {
		System.out.println("==> Mybatis�� workerCheck() ���ó��");
		return mybatis.selectOne("workerDAO.workerCheck", id);
	}
	
	public EmployeeVO goEmployee(String id) {
		System.out.println("==> Mybatis�� goEmployee() ���ó��");
		return mybatis.selectOne("workerDAO.getEmployee", id);
	}
}
