package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.BaseDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.BaseEntity;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.BaseService;

public class BaseController <T extends BaseEntity, D extends BaseDto> {
	@Autowired
	BaseService<T, D> cBaseService;
	
	/**
	 * SELECT ALL
	 */
	@GetMapping()
	public Set<D> getComments() {
		return cBaseService.getAll();
	}
	
	/**
	 * SELECT by ID
	 * @param id ID
	 */
	@GetMapping(value = "{id}")
	public D getByID(@PathVariable(value = "id", required = true) int id) {
		try {
			return cBaseService.getById(id);
		} catch (SniffableNotFoundException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
	/**
	 * DELETE by ID
	 */
	@DeleteMapping(value = "{id}")
	public void deleteById(@PathVariable(value = "id", required = true) int id) {
		try {
			cBaseService.delete(id);
		} catch (SniffableNotFoundException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
}
