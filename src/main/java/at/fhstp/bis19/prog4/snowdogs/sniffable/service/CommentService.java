package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.CommentDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.RegisterDogDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableIllegalValueException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.CommentRepo;

@Service
public class CommentService {
	@Autowired
	CommentRepo commentRepo;
	
	//private static final Logger log = LoggerFactory.getLogger(CommentService.class);
		
	public Set<CommentDTO> getAll() {
		Set<CommentDTO> dogs = new HashSet<>();
		for (Comment c : commentRepo.findAll()) {
			dogs.add(new CommentDTO(c));
		}
		return dogs;
	}
	
	public CommentDTO getById(int id) throws SniffableNotFoundException {
		if (commentRepo.existsById(id)) {
			return new CommentDTO(commentRepo.findById(id).get());
		} else {
			throw new SniffableNotFoundException("comment with id \"" + id + "\" + not exists");
		}
	}
	
	public void delete(int id) throws SniffableNotFoundException {
		if (commentRepo.existsById(id)) {
			commentRepo.deleteById(id);
		} else {
			throw new SniffableNotFoundException("comment with id \"" + id + "\" + not exists");
		}		
	}
	
}
