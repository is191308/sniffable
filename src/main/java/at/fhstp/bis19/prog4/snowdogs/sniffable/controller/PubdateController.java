package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.CommentDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewCommentDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewPubdateDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.PubdateDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.PudateService;

@RestController
@RequestMapping("/pubdate")
public class PubdateController extends BaseController<Pubdate, PubdateDTO>{

	@Autowired
	private PudateService cPubdateService;
	
	
	/**
	 * CREATE
	 * @param pubdate Pubdate
	 * @return Pubdate
	 */
	@PostMapping()
	public PubdateDTO createPubdate(@RequestBody(required = true) final NewPubdateDTO pubdate) {
		try {
			return cPubdateService.createPubdate(pubdate);
		} catch (SniffableException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
	
	/**
	 * ADD Comment
	 * @param comment comment
	 * @return comment
	 */
	@PostMapping(value = "{id}/comment")
	public CommentDTO commentPubdate(@PathVariable(value = "id", required = true) int id, @RequestBody(required = true) final NewCommentDTO comment) {
		try {
			return cPubdateService.addComment(id, comment);
		} catch (SniffableException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
	
}
