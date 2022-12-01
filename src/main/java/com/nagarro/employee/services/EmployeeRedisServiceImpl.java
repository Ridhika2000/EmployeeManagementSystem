package com.nagarro.employee.services;

import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.nagarro.employee.entity.EmployeeRedis;
import com.nagarro.employee.exceptions.ResourceNotFoundException;

@Service
public class EmployeeRedisServiceImpl implements EmployeeRedisService {

	private static final String KEY = "EMPLOYEE";
	private HashOperations hashOperations;

	public EmployeeRedisServiceImpl(RedisTemplate redisTemplate) {
		super();
		this.hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void create(EmployeeRedis employeeRedis) {
		
			hashOperations.put(KEY, employeeRedis.getEmp_id(), employeeRedis);
		
		 
	}

	
	@Override
	public void updateEmp(EmployeeRedis employeeRedis) throws Exception {
		
			if(hashOperations.get(KEY, employeeRedis.getEmp_id())==null) {
				throw new ResourceNotFoundException("Employee not found for this id in redis:: " + employeeRedis.getEmp_id());
			}
			
			hashOperations.put(KEY, employeeRedis.getEmp_id(), employeeRedis);
		
		
	}

	@Override
	public void deleteEmp(Integer id) throws Exception {
		
			if(hashOperations.get(KEY, id)==null) {
				throw new ResourceNotFoundException("Employee not found for this id in redis:: " + id);
			}
			hashOperations.delete(KEY, id);
		

	}
	
	
	@Override
	public String getDept(Integer id) throws Exception {
		
		
			if(hashOperations.get(KEY, id)==null) {
				
				throw new ResourceNotFoundException("Employee not found for this id :: " + id);
			}

			EmployeeRedis employeeRedis = (EmployeeRedis)hashOperations.get(KEY, id);

			return employeeRedis.getEmp_dept();
		

		
	}

	
}
