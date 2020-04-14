package com.hakalab.api.dao;

import java.util.List;

import com.hakalab.api.entity.Feature;

public interface FeatureDAO {

	public List<Feature> findAll();
	public Feature finByName(String name);
	public void save(Feature feature);
	public void deleteByName(String name);
}
