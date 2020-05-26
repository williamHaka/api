package com.hakalab.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping(value = "/hakalab") //esta sera la raiz de la url, es decir http://127.0.0.1:8082/hakalab/
public class FeatureRestController {
		
		@Autowired
		private FeatureService featureService;
		@Autowired
		private UsuarioService usuarioService;
		
		@RequestMapping(value ="/}")  
		public String hello() {   
		 return "Welcome Api Hakalab"; 
		}
		
		@GetMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> getToken(@RequestBody Usuario usuario) throws Exception{
			String token = usuarioService.getTokenByUsername(usuario);
			if(token!=null) 
				return ResponseEntity.status(HttpStatus.OK).body(token);
			return new ResponseEntity<String>("credential incorrect", HttpStatus.NOT_FOUND);
		}
		
		@GetMapping(value = "/features",produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> findAll(){
			List<Feature> features = featureService.getAll();
			if(features.isEmpty()) 
				 return new ResponseEntity<String>("Features not found", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.OK).body(features.toString());
		}

		@GetMapping(value = "/features/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> getFeature(@PathVariable String name){
			Feature feature = featureService.getByName(name);		
			if(feature==null) 
				 return new ResponseEntity<String>("Features not found with name: "+name, HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.OK).body(feature.toString());
		}

		
		@PostMapping(value = "/features",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> addFeature(@RequestBody Feature feature) {
			Integer status = featureService.save(feature);
			if(status==0) 
				return new ResponseEntity<String>("Features exist with name: "+feature.getNameFeature(), HttpStatus.FOUND);
			return ResponseEntity.status(HttpStatus.CREATED).body(feature.toString());
		}
		
		
//		@PutMapping(value = "/updatefeatures",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//		public void updateFeature(@RequestBody Feature feature) {
//			//TODO
//			Integer status = 0;
//			if (feature.getId()!=null || feature.getId()!=0) {
//				status = featureService.update(feature);
//			}
//			if(status==0)
//				 return new ResponseEntity<String>("Features not found with name: "+feature.getNameFeature(), HttpStatus.NOT_FOUND);
//			return ResponseEntity.status(HttpStatus.OK).body(feature.toString());
//		}
		
		@DeleteMapping("features/{name}")
		public ResponseEntity<String> deteteFeature(@PathVariable String name) {
			Feature feature = featureService.deleteFeature(name);
			if(feature!=null)
				return ResponseEntity.status(HttpStatus.OK).body("Eliminated feature with name: "+name);
			return new ResponseEntity<String>("Features not found with name: "+name, HttpStatus.NOT_FOUND);
		}
		
}
