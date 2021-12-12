package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.BaseDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.BaseEntity;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.BaseService;

public class BaseController <T extends BaseEntity, D extends BaseDto> {
	BaseService<T, D> cBaseService;
	
	@Autowired
	public BaseController(BaseService<T, D> cBaseService) {
		this.cBaseService = cBaseService;
	}
	
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
		return cBaseService.getById(id);
	}
	
	/**
	 * DELETE by ID
	 */
	@DeleteMapping(value = "{id}")
	public void deleteById(@PathVariable(value = "id", required = true) int id) {
		cBaseService.delete(id);
	}
	
}
