package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Parameter;

@Repository
public class ParameterDAO extends BaseDAO{

	public List<Parameter> getByIdStep(Integer idStep) {
		Query<Parameter> query = getSession().createQuery("select a from Parameter a where a.step.id=:idStep",Parameter.class);
		query.setParameter("idStep", idStep);
		return query.getResultList();
	}
	
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
	}
}
