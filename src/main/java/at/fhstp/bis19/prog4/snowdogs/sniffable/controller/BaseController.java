package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private ResponseEntity<Object>handleMethodArgumentNotValidException(final HttpServletRequest request, final MethodArgumentNotValidException e) {
	    Map<String, Object> map = new HashMap<String, Object>();
        map.put("timestamp", new Date());
        map.put("status", 400);
        map.put("error", "Bad Request");
        map.put("message", e.getAllErrors().stream().map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString());
        map.put("path", request.getRequestURI());

        return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

}
