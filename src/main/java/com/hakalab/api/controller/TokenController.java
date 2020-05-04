//package com.hakalab.api.controller;
//
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hakalab.api.entity.Feature;
//
//@RestController
//public class TokenController {
//	
//	@GetMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> getToken(@RequestBody Feature feature){
//		Feature feature = featureService.getByName(name);		
//		if(feature==null) 
//			 return new ResponseEntity<String>("Features not found with name: "+name, HttpStatus.NOT_FOUND);
//		return ResponseEntity.status(HttpStatus.OK).body(feature.toString());
//	}
//}
