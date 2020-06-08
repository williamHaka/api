package com.hakalab.api.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.CicloTest;

@Repository
public class CicloTestDAO extends BaseDAO {

	public List<CicloTest> getAll() {
		Query<CicloTest> selectCiclo = getSession().createQuery("select a from CicloTest a", CicloTest.class);
		return selectCiclo.getResultList();
	}
	
}
