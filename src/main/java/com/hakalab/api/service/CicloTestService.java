package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.CicloTestDAO;
import com.hakalab.api.dao.ScenarioCicloDAO;
import com.hakalab.api.dao.ScenarioDAO;
import com.hakalab.api.entity.CicloTest;
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.ScenarioCiclo;

@Service
public class CicloTestService {

	@Autowired
	private CicloTestDAO cicloTestDAO;
	@Autowired
	private ScenarioDAO scenarioDAO;
	@Autowired
	private ScenarioCicloDAO scenariocicloDAO;
	
	public List<CicloTest> getAll() {
		List<CicloTest> ciclos = cicloTestDAO.getAll();
		return ciclos;
	}
	
	public CicloTest getById(Integer id) {
		CicloTest cicloTest = cicloTestDAO.getById(id);
		return cicloTest;
	}
	
	public Integer save(Scenario scenario) {
		Integer status = 0;
		try {
			Scenario scenarioExist = scenarioDAO.getById(scenario.getIdScenario());
			if (scenarioExist != null) {
				for (CicloTest cicloTest : scenario.getCiclos()) {
					CicloTest cicloExist = cicloTestDAO.getByName(cicloTest.getNameCiclo());
					if (cicloExist == null) {
						cicloTestDAO.save(cicloTest);
						CicloTest ciclo2 = cicloTestDAO.getByName(cicloTest.getNameCiclo());
						ScenarioCiclo scenarioCiclo = new ScenarioCiclo();
						scenarioCiclo.setIdScenario(scenario.getIdScenario());
						scenarioCiclo.setIdCiclo(ciclo2.getIdCiclo());
						scenariocicloDAO.save(scenarioCiclo);
						status = 1;
					}else {
						return 0;
					}
				}
			}
		} catch (Exception e) {
		}
		return status;
	}
	
	public Integer updateCiclo(CicloTest ciclo) {
		Integer status = 0;
		try {
			CicloTest cicloExist = cicloTestDAO.getById(ciclo.getIdCiclo());
			if (cicloExist != null) {
				cicloExist.setNameCiclo(ciclo.getNameCiclo());
				cicloExist.setDescriptionCiclo(ciclo.getDescriptionCiclo());
				cicloTestDAO.update(cicloExist);
				status = 1;
			}
		} catch (Exception e) {
		}
		return status;
	}
	
//	public CicloTest deleteCiclo(String name) {
//		CicloTest ciclo = cicloTestDAO.getByName(name);
//		if (ciclo != null) {
//			List<ScenarioCiclo> scenarioCiclos = scenariocicloDAO.getIdScenario(scenario);
//			if (scenarioCiclos != null ) {
//				for (ScenarioCiclo scenarioCiclo : scenarioCiclos) {
//					scenariocicloDAO.delete(scenarioCiclo);
//				}
//			}
//			scenarioDAO.delete(ciclo);
//		}
//		return ciclo;
//	}
	
}
