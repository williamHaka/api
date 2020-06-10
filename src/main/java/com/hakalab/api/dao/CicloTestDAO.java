package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.CicloTest;

@Repository
public class CicloTestDAO extends BaseDAO {

	public List<CicloTest> getAll() {
		Query<CicloTest> selectCiclo = getSession().createQuery("select a from CicloTest a", CicloTest.class);
		return selectCiclo.getResultList();
	}
	
	public CicloTest getByName(String name) {
		CicloTest ciclo = null;
		try {
			Query<CicloTest> query = getSession().createQuery("select a from CicloTest a where a.nameCiclo=;nombre", CicloTest.class);
			query.setParameter("nombreCiclo", name);
			ciclo = query.getSingleResult();
		} catch (Exception e) {
		}
		return ciclo;
	}
	
	public CicloTest getById(Integer id) {
		CicloTest cicloTest = null;
		try {
			Query<CicloTest> query = getSession().createQuery("select a from CicloTest a where a.idCiclo=:id", CicloTest.class);
			query.setParameter("id", id);
			cicloTest = query.getSingleResult();
		} catch (Exception e) {
		}
		return cicloTest;
	}
	
	public void save(CicloTest ciclo) {
		getSession().save(ciclo);
	}
	
	@Transactional
	@Modifying
	public void update(CicloTest ciclo) {
		getSession().update(ciclo);
	}
	
}
