package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Parameter;

@Repository
public class ParameterDAO extends BaseDAO{

	public List<Parameter> getAll() {
		Query<Parameter> selectStep = getSession().createQuery("select a from Parameter a",Parameter.class);
		return selectStep.getResultList();
	}
	
	public Parameter getById(Integer idParameter) {
		Parameter parameter = null;
		try {
			Query<Parameter> query = getSession().createQuery("select a from Parameter a where a.idParameter=:idParameter",Parameter.class);
			query.setParameter("idParameter", idParameter);
			parameter = query.getSingleResult();
		} catch (Exception e) {
		}
		return parameter;
	}
	
	public List<Parameter> getByName(String nameParameter) {
		Query<Parameter> query = getSession().createQuery("select a from Parameter a where a.nameParameter=:nameParameter",Parameter.class);
		query.setParameter("nameParameter", nameParameter);
		return query.getResultList();
	}
	
	public Parameter getByNameAndValue(String nameParameter, String valueParameter) {
		Parameter parameter = null;
		try {
			Query<Parameter> query = getSession().createQuery("select a from Parameter a where a.nameParameter=:nameParameter and a.valueParameter=:valueParameter",Parameter.class);
			query.setParameter("nameParameter", nameParameter);
			query.setParameter("valueParameter", valueParameter);
			parameter = query.getSingleResult();
		} catch (Exception e) {
		}
		return parameter;
	}
	
	public void save(Parameter parameter) {
		getSession().save(parameter);
	}
	
	@Transactional
	@Modifying
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
