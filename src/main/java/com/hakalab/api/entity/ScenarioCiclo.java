package com.hakalab.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "scenario_ciclo")
public class ScenarioCiclo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_scenario_ciclo")
	private Integer idScenarioCiclo;
	
	@Column(name = "id_scenario")
	private Integer idScenario;
	
	@Column(name = "id_ciclo")
	private Integer idCiclo;

	public Integer getIdScenarioCiclo() {
		return idScenarioCiclo;
	}

	public void setIdScenarioCiclo(Integer idScenarioCiclo) {
		this.idScenarioCiclo = idScenarioCiclo;
	}

	public Integer getIdScenario() {
		return idScenario;
	}

	public void setIdScenario(Integer idScenario) {
		this.idScenario = idScenario;
	}

	public Integer getIdCiclo() {
		return idCiclo;
	}

	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
	}
	
	
}
