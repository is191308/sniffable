package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.CommentDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService cCommentService;
	
	@GetMapping()
	public Set<CommentDTO> getDogs() {
		return cCommentService.getAll();
	}
	
	@GetMapping(value = "{id}")
	public CommentDTO getCommentByID(@PathVariable(value = "id", required = true) int id) {
		try {
			return cCommentService.getById(id);
		} catch (SniffableNotFoundException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
	@DeleteMapping(value = "{id}")
	public void deleteCommentById(@PathVariable(value = "id", required = true) int id) {
		try {
			cCommentService.delete(id);
		} catch (SniffableNotFoundException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
}
