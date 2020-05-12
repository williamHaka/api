package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.ProjectDAO;
import com.hakalab.api.entity.Project;

@Service
public class ProjectService {

	@Autowired
	private ProjectDAO projectDAO;
	
	public List<Project> getAll() {
		List<Project> projects = projectDAO.getAll();
		return projects;
	}
	
	public Project getByName(String name) {
		Project project = projectDAO.getByName(name);
		return project;
	}
	
	public Project getById(Integer Id) {
		Project project = projectDAO.getById(Id);
		return project;
	}
	
	public Integer saveProject(Project project) {
		Integer status = 0;
		try {
			Project projectExist = projectDAO.getById(project.getIdProject());
			
		} catch (Exception e) {
		}
		return status;
	}
	
	public Integer update(Project project) {
		Integer status = 0;
		try {
			Project projectExist = projectDAO.getById(project.getIdProject());
			if (projectExist != null) {
				projectExist.setNameProject(project.getNameProject());
				projectExist.setDescriptionProject(project.getDescriptionProject());
				projectDAO.update(projectExist);
				status = 1;
			}
		} catch (Exception e) {
		}return status;
	}
	
	public Project deleteProject(String name) {
		Project project = projectDAO.getByName(name);
		if(project!=null) {
			projectDAO.delete(project);
			}
		return project;
		}
}
