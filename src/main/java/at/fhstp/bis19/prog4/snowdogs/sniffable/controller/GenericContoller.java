package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.BaseEntity;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.BaseCrudRepository;

public class GenericContoller<T extends BaseEntity> {
	@Autowired
	private BaseCrudRepository<T> repo;
	
	
	@GetMapping
	public List<T> read() {
		return (List<T>) repo.findAll();
	}
	
	@GetMapping(value = "{id}")
	public T readByID(@PathVariable(value = "id") int id) {
		return repo.findById(id).orElse(null);
	}
	
	@PostMapping
	public T create(@RequestBody T entity) {
		return repo.save(entity);
	}
	
	@PutMapping(value = "{id}")
	public T update(@PathVariable(value = "id") int id, @RequestBody T entity) {
		return repo.save(entity);
	}
	
	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable(value = "id") int id) {
		repo.deleteById(id);
	}
	
}
