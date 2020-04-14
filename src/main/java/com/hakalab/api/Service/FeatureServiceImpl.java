package com.hakalab.api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.FeatureDAO;
import com.hakalab.api.entity.Feature;

@Service
public class FeatureServiceImpl implements FeatureService{
	
	@Autowired	
	private FeatureDAO featureDAO;

	@Override
	public List<Feature> findAll() {
		List<Feature> features = featureDAO.findAll();
		return features;
	}

	@Override
	public Feature finByName(String name) {
		Feature feature = featureDAO.finByName(name) ;
		return feature;
	}

	@Override
	public void save(Feature feature) {
		featureDAO.save(feature);
		
	}

	@Override
	public void deleteByName(String name) {
		featureDAO.deleteByName(name);
	}

}
