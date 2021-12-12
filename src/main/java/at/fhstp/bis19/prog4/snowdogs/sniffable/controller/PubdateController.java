package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.CommentDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewCommentDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewPubdateDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.PubdateDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.PubdateService;

@RestController
@RequestMapping("/pubdate")
public class PubdateController extends BaseController<Pubdate, PubdateDto>{

	@Autowired
	private PubdateService cPubdateService;
	
	
	/**
	 * CREATE
	 * @param pubdate Pubdate
	 * @return Pubdate
	 */
	@PostMapping()
	public PubdateDto createPubdate(@RequestBody(required = true) final NewPubdateDto pubdate) {
		try {
			return cPubdateService.createPubdate(pubdate);
		} catch (SniffableException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
	/**
	 * ADD COMMENT
	 * @param id pubdateID
	 * @param did dogID
	 * @param comment Comment
	 * @return Comment
	 */
	@PostMapping(value = "{id}/comment/{did}")
	public CommentDto commentPubdate(@PathVariable(value = "id", required = true) int id, @PathVariable(value = "did", required = true) int did, @RequestBody(required = true) final NewCommentDto comment) {
		try {
			return cPubdateService.addComment(id, did, comment);
		} catch (SniffableException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
	
}
