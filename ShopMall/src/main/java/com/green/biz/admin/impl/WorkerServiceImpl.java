package com.green.biz.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.admin.EmployeeVO;
import com.green.biz.admin.WorkerService;

@Service("workerService")
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerDAO dao;
	
	@Override
	public int workerCheck(String id, String pwd) {
		String pass = dao.workerCheck(id);
		
		if(pass == null) {
			return -1;
		} else if (pass.equals(pwd)) {
			return 1;
		} else {
			return 0;
		}
		
	}

	@Override
	public EmployeeVO goEmployee(String id) {
		return dao.goEmployee(id);
	}

}
