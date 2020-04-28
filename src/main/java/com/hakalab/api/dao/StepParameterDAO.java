package com.hakalab.api.dao;

import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.StepParameter;

@Repository
public class StepParameterDAO extends BaseDAO{

	public void save(StepParameter stepParameter) {
		getSession().save(stepParameter);
	}
}
