package com.hakalab.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakalab.api.entity.Feature;
import com.hakalab.api.service.FeatureService;

//Indicamos que es un controlador rest
@RestController
@RequestMapping(value = "/hakalab") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/hakalab/
public class FeatureRestController {
	
		@Autowired
		private FeatureService featureService;
		
		@GetMapping(value = "/features",produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> findAll(){
			List<Feature> features = featureService.findAll();
			if(features.isEmpty()) 
				 return new ResponseEntity<String>("Features not found", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.OK).body(features.toString());
		}

		@GetMapping(value = "/features/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> getFeature(@PathVariable String name){
			Feature feature = featureService.finByName(name);		
			if(feature==null) 
				 return new ResponseEntity<String>("Features not found with name: "+name, HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.OK).body(feature.toString());
		}
		
		@PostMapping(value = "/features",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> addFeature(@RequestBody Feature feature) {
			featureService.saveOrUpdateFeature(feature);
			return ResponseEntity.status(HttpStatus.OK).body(feature.toString());
		}
		
		
//		@PutMapping(value = "/features",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//		public ResponseEntity<String> updateFeature(@RequestBody Feature feature) {
//				featureService.update(feature);
//				return ResponseEntity.status(HttpStatus.OK).body(feature.toString());
//		}
//		
		@DeleteMapping("features/{name}")
		public ResponseEntity<String> deteteFeature(@PathVariable String name) {
			Feature feature = featureService.deleteFeature(name);
			if(feature!=null)
				return ResponseEntity.status(HttpStatus.OK).body("Elimated feature with name: "+name);
			return new ResponseEntity<String>("Features not found with name: "+name, HttpStatus.NOT_FOUND);
		}
		
}
