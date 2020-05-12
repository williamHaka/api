package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Project;

@Repository
public class ProjectDAO extends BaseDAO {

	public List<Project> getAll() {
		Query<Project> selectProject = getSession().createQuery("select a from Project a", Project.class);
		return selectProject.getResultList();
	}
	
	public Project getByName(String name) {
		Project project = null;
		try {
			Query<Project> query = getSession().createQuery("select a from Project a where a.nameProject=:nombre", Project.class);
			query.setParameter("nombre", name);
			project  = query.getSingleResult();
		} catch (Exception e) {
		}
		return project;
		
	}
	
	public Project getById(Integer idProject) {
		Project project = null;
		try {
			Query<Project> query = getSession().createQuery("select a from Project a where a.idProject=:idProject", Project.class);
			query.setParameter("idProject", idProject);
			project = query.getSingleResult();
		} catch (Exception e) {
		}
		return project;	
	}
	
	public void save(Project project) {
		getSession().save(project);
	}

	@Transactional
	@Modifying
	public void update(Project project) {
		getSession().update(project);
	}
	
	@Transactional
	@Modifying
	public void delete(Project project) {
		getSession().delete(project);
	}
}
