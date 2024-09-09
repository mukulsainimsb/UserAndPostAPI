package com.api.play.play_with_api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.play.play_with_api.Post;
import com.api.play.play_with_api.Userr;
import com.api.play.play_with_api.Exception.UserNotFoundException;
import com.api.play.play_with_api.repository.PostRepository;
import com.api.play.play_with_api.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserrController {
	@Autowired
	private UserRepository userRepository;
	private PostRepository postRepository;
		
		public UserrController(UserRepository userRepository,PostRepository postRepository) {
		super();
		this.userRepository = userRepository;
		this.postRepository=postRepository;
	}

		@GetMapping("/users")
		public List<Userr> getAllUsers(){
			return userRepository.findAll();
		}
		
		@GetMapping("/users/{id}")
		public EntityModel<Optional<Userr>> getOneUser(@PathVariable int id) {
			Optional<Userr> user= userRepository.findById(id);
			if(user==null) {
				throw new UserNotFoundException("id="+id);
			}
			// adding link to all users retrieval
			EntityModel<Optional<Userr>> entityModel=EntityModel.of(user);
			WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).getAllUsers());
			entityModel.add(link.withRel("all-users"));
			return entityModel;
		}
		
		@DeleteMapping("users/{id}")
		public void deleteOneUser(@PathVariable int id){
			userRepository.deleteById(id);
		}
		
		@PostMapping("/users")
		public ResponseEntity<Object> postUser(@Valid @RequestBody Userr user) {
		Userr savedUser=userRepository.save(user);
		   URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				   .path("/{id}")
				   .buildAndExpand(savedUser.getId())
				   .toUri();
		   return ResponseEntity.created(location).build();
		}
		@GetMapping("users/{id}/posts")
		public List<Post> retrievePost(@PathVariable int id){
			Optional<Userr> user=userRepository.findById(id);
			if(user.isEmpty()) {
				throw new UserNotFoundException("id="+id);
			}
			return user.get().getPosts();
			
		}
		
		@PostMapping("users/{id}/posts")
		public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post){
			Optional<Userr> user= userRepository.findById(id);
			if(user.isEmpty()) {
				throw new UserNotFoundException("id="+id);
			}
			post.setUser(user.get());
			Post savedPost=postRepository.save(post);
			 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					   .path("/{id}")
					   .buildAndExpand(savedPost.getId())
					   .toUri();
			   return ResponseEntity.created(location).build();
			
		}		
		
}