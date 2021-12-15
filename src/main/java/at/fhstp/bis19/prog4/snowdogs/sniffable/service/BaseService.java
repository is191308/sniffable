package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.BaseDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.BaseEntity;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.BaseCrudRepository;

public class BaseService<T extends BaseEntity, D extends BaseDto> {
	BaseCrudRepository<T> repo;
	Class<D> typeParameterClass;
	ModelMapper mapper = new ModelMapper();
	
	@Autowired
	public BaseService(Class<D> typeParameterClass, BaseCrudRepository<T> repo) {
        this.typeParameterClass = typeParameterClass;
        this.repo = repo;
    }
	
	public Set<D> getAll() {
		return StreamSupport.stream(repo.findAll().spliterator(), false)
				.map(d -> mapper.map(d, typeParameterClass))
				.collect(Collectors.toSet());
	}
	
	public D getById(int id) {
		return mapper.
				map(repo.findById(id).
						orElseThrow(() -> new SniffableNotFoundException("entity with id \"" + id + "\" + not exists")), typeParameterClass);
	}
	
	public void delete(int id) {
		try {
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new SniffableNotFoundException("entity with id \"" + id + "\" + not exists");
		}		
	}
		
}
