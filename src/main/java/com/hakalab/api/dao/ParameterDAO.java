package com.hakalab.api.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Parameter;

@Repository
public class ParameterDAO extends BaseDAO{

	
	public void save(Parameter parameter) {
		getSession().save(parameter);
	}
	
	public void update(Parameter parameter) {
		getSession().update(parameter);
	}
	
	@Transactional
	@Modifying
	public void delete(Parameter parameter) {
		getSession().delete(parameter);
//		Query<StepParameter> query = getSession().createQuery("dfrom StepParameter a where a.idStep=:idStep and a.idParameter=:idParameter",StepParameter.class);
//		query.setParameter("idStep", stepParameter.getIdStep());
//		query.setParameter("idParameter", stepParameter.getIdParameter());
//		return query.getResultList();
	}
}
