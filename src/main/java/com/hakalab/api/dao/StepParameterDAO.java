package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.StepParameter;

@Repository
public class StepParameterDAO extends BaseDAO{

	public List<StepParameter> getByIdStepAndIdParameter(StepParameter stepParameter) {
		Query<StepParameter> query = getSession().createQuery("select a from StepParameter a where a.idStep=:idStep and a.idParameter=:idParameter",StepParameter.class);
		query.setParameter("idStep", stepParameter.getIdStep());
		query.setParameter("idParameter", stepParameter.getIdParameter());
		return query.getResultList();
	}
	
	public void save(StepParameter stepParameter) {
		getSession().save(stepParameter);
	}
	
	@Transactional
	@Modifying
	public void delete(StepParameter stepParameter) {
		getSession().delete(stepParameter);
	}
}
