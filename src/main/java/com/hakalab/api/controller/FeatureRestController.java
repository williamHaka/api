package com.hakalab.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakalab.api.entity.Feature;
import com.hakalab.api.entity.Usuario;
import com.hakalab.api.service.FeatureService;
import com.hakalab.api.service.UsuarioService;

//Indicamos que es un controlador rest
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/hakalab") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/hakalab/
public class FeatureRestController {
		@Autowired
		private FeatureService featureService;
		
		@RequestMapping(value ="/")  
		public String hello() {   
		 return "Hello, world"; 
		 }
		
		@GetMapping(value = "/features",produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> findAll(){
			List<Feature> features = featureService.getAll();
			if(features.isEmpty()) 
				 return new ResponseEntity<String>("Features not found", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.OK).body(features.toString());
		}

		@GetMapping(value = "/featureByName",produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> getFeatureByName(@RequestBody Feature feature){
			Feature feature2 = featureService.getByName(feature.getNameFeature());		
			if(feature2==null) 
				 return new ResponseEntity<String>("Features not found with name: "+feature.getNameFeature(), HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.OK).body(feature2.toString());
		}

		@GetMapping(value = "/featureById",produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> getFeatureById(@RequestBody Feature feature){
			Feature featureExist = featureService.getById(feature.getIdFeature());		
			if(featureExist.equals(null)) 
				 return new ResponseEntity<String>("Features not found with name: "+ feature.getIdFeature(), HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.OK).body(featureExist.toString());
		}
		
		@PostMapping(value = "/features",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> addFeature(@RequestBody Feature feature) {
			Integer status = featureService.saveFeature(feature);
			if(status==0) 
				return new ResponseEntity<String>("Features exist with name: "+feature.getNameFeature(), HttpStatus.FOUND);
			return ResponseEntity.status(HttpStatus.CREATED).body(feature.toString());
		}
		
		@PutMapping(value = "/updatefeature", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> updateScenario(@RequestBody Feature feature) {
			Integer status = 0;
			status = featureService.update(feature);
			if(status==0)
				 return new ResponseEntity<String>("Feature not found with name: " + feature.getNameFeature(), HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.OK).body(feature.toString());
		}
		
		@DeleteMapping("/features")
		public ResponseEntity<String> deteleFeature(@RequestBody Feature feature) {
			Feature feature2 = featureService.deleteFeature(feature.getNameFeature());
			if(feature2!=null)
				return ResponseEntity.status(HttpStatus.OK).body("Eliminated feature with name: "+feature.getNameFeature());
			return new ResponseEntity<String>("Feature not found with name: "+feature.getNameFeature(), HttpStatus.NOT_FOUND);
		}
}
