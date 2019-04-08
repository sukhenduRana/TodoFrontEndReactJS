package com.todoapp.rest.webservices.restfulwebservices;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todoapp.rest.webservices.restfulwebservices.todo.Todo;
import com.todoapp.rest.webservices.restfulwebservices.todo.TodoHardCodedService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoResource {
	
	@Autowired
	private TodoHardCodedService todoService;
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
		
		return todoService.findAll();
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
		
		Todo todo = todoService.deleteById(id);
		if(todo !=null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build(); 
	}

	@GetMapping("/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
		Todo todo = todoService.findById(id);
		if(todo != null) {
			return todo;
		}
		return null;
		
	}
	
	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
		 Todo updatedTodo = todoService.save(todo);
		return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo){
		 Todo createdTodo = todoService.save(todo);
		 URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		 
		 return ResponseEntity.created(uri).build();
		
	}
}
