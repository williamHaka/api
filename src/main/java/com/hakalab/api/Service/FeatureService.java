package com.hakalab.api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.FeatureDAO;
import com.hakalab.api.entity.Feature;

@Service
public class FeatureService{
	
	@Autowired	
	private FeatureDAO featureDAO;

	public List<Feature> findAll() {
		List<Feature> features = featureDAO.findAll();
		return features;
	}

	public Feature finByName(String name) {
		Feature feature = featureDAO.finByName(name) ;
		return feature;
	}

	public void save(Feature feature) {
		featureDAO.save(feature);
		
	}

	public void deleteByName(String name) {
		featureDAO.deleteByName(name);
	}

}
