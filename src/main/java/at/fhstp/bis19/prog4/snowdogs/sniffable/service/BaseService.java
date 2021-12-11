package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.BaseEntity;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.BaseCrudRepository;

public class BaseService<T extends BaseEntity, D> {
	@Autowired
	BaseCrudRepository<T> repo;
	
	Class<D> typeParameterClass;
	ModelMapper mapper = new ModelMapper();
	
	public BaseService(Class<D> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }
	
	public Set<D> getAll() {
		return StreamSupport.stream(repo.findAll().spliterator(), false)
				.map(d -> mapper.map(d, typeParameterClass))
				.collect(Collectors.toSet());
	}
	
	public D getById(int id) throws SniffableNotFoundException {
		return mapper.
				map(repo.findById(id).
						orElseThrow(() -> new SniffableNotFoundException("entity with id \"" + id + "\" + not exists")), typeParameterClass);
	}
	
	public void delete(int id) throws SniffableNotFoundException {
		try {
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new SniffableNotFoundException("entity with id \"" + id + "\" + not exists");
		}		
	}
		
}
