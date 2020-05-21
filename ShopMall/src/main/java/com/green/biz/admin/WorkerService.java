package com.green.biz.admin;

public interface WorkerService {

	int workerCheck(String id, String pwd);

	EmployeeVO goEmployee(String id);

}