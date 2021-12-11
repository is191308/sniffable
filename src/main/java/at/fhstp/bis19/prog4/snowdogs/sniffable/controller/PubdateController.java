package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewPubdateDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.PudateService;

@RestController
@RequestMapping("/pubdate")
public class PubdateController {

	@Autowired
	private PudateService cPubdateService;
	
	/**
	 * SELECT ALL
	 * @return pubdates
	 */
	@GetMapping
	public List<NewPubdateDTO> getAllPubdates() {
		return cPubdateService.getAll();
	}
	
	/**
	 * SELECT by ID
	 * @param id ID
	 * @return pubdate
	 */
	@GetMapping(value = "{id}")
	public NewPubdateDTO getPubdateById(@PathVariable(value = "id", required = true) int id) {
		try {
			return cPubdateService.getById(id);
		} catch (SniffableNotFoundException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
	/**
	 * CREATE
	 * @param pubdate Pubdate
	 * @return Pubdate
	 */
	@PostMapping()
	public NewPubdateDTO createPubdate(@RequestBody(required = true) final NewPubdateDTO pubdate) {
		try {
			return cPubdateService.createPubdate(pubdate);
		} catch (SniffableException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
	/**
	 * DELETE by ID
	 * @param id pubdate
	 */
	@DeleteMapping(value = "{id}")
	public void deleteDogById(@PathVariable(value = "id", required = true) int id) {
		try {
			cPubdateService.delete(id);
		} catch (SniffableNotFoundException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
}
