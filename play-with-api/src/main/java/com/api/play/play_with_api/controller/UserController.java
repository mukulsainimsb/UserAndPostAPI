//package com.api.play.play_with_api.controller;
//
//import java.net.URI;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import com.api.play.play_with_api.Userr;
//import com.api.play.play_with_api.Exception.UserNotFoundException;
//import com.api.play.play_with_api.service.UserService;
//
//import jakarta.validation.Valid;
//
//@RestController
//public class UserController {
//
//	@Autowired
//	private UserService userService;
//	
//	@GetMapping("/users")
//	public List<Userr> getAllUsers(){
//		return userService.getAll();
//	}
//	
//	@GetMapping("/users/{id}")
//	public EntityModel<Userr> getOneUser(@PathVariable int id) {
//		Userr user= userService.getOne(id);
//		if(user==null) {
//			throw new UserNotFoundException("id="+id);
//		}
//		// adding link to all users retrieval
//		EntityModel<Userr> entityModel=EntityModel.of(user);
//		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).getAllUsers());
//		entityModel.add(link.withRel("all-users"));
//		return entityModel;
//	}
//	
//	@DeleteMapping("users/{id}")
//	public void deleteOneUser(@PathVariable int id){
//		userService.deleteOne(id);
//	}
//	
//	@PostMapping("/users")
//	public ResponseEntity<Object> postUser(@Valid @RequestBody Userr user) {
//	Userr savedUser=userService.postOne(user);
//	   URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//			   .path("/{id}")
//			   .buildAndExpand(savedUser.getId())
//			   .toUri();
//	   return ResponseEntity.created(location).build();
//	}
//}
