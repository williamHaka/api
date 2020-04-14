package com.hakalab.api.Service;

import java.util.List;

import com.hakalab.api.entity.Feature;

public interface FeatureService {
	public List<Feature> findAll();
	public Feature finByName(String name);
	public void save(Feature feature);
	public void deleteByName(String name);
}
