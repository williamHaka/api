package com.hakalab.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakalab.api.Service.FeatureService;
import com.hakalab.api.entity.Feature;

//Indicamos que es un controlador rest
@RestController
@RequestMapping("/hakalab") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/api/
public class FeatureRestController {
	
	//Inyectamos el servicio para poder hacer uso de el
		@Autowired
//		private IFeatureService featureService;
		private FeatureService featureService;
		
		/*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url 
		http://127.0.0.1:8080/api/users*/
		@GetMapping("/findAllFeature")
		public List<Feature> findAll(){
			List<Feature> features = featureService.findAll();
			if(features.isEmpty()) 
				throw new RuntimeException("features not found");	
			
			return featureService.findAll();
		}
		
		/*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id de un usuario
		http://127.0.0.1:8080/api/users/1*/
		@GetMapping("/feature/{name}")
		public Feature getFeature(@PathVariable String name){
			Feature feature = featureService.finByName(name);		
			if(feature == null) {
				throw new RuntimeException("feature name not found -"+name);
			}
			return feature;
		}
		
		/*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
		http://127.0.0.1:8080/api/users/  */
		@PostMapping("/feature")
		public Feature addFeature(@RequestBody Feature feature) {
			feature.setId(0);
			featureService.save(feature);
			return feature;
			
		}
		
		/*Este método se hará cuando por una petición PUT (como indica la anotación) se llame a la url
		http://127.0.0.1:8080/api/users/  */
		@PutMapping("/feature")
		public Feature updateFeature(@RequestBody Feature feature) {
			featureService.save(feature);
			return feature;
		}
		
		/*Este método se hará cuando por una petición DELETE (como indica la anotación) se llame a la url + id del usuario
		http://127.0.0.1:8080/api/users/1  */
		@DeleteMapping("feature/{name}")
		public String deteteUser(@PathVariable String name) {
			
			Feature user = featureService.finByName(name);
			
			if(user == null) {
				throw new RuntimeException("User id not found -"+name);
			}
			
			featureService.deleteByName(name);
			
			//Esto método, recibira el id de un usuario por URL y se borrará de la bd.
			return "Deleted feature name - "+name;
		}
}
