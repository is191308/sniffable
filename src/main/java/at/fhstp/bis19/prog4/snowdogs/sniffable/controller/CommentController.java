package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.ClientCommentService;

@RestController("CommentController")
@RequestMapping("/restapi/comment")
public class CommentController extends GenericContoller<Comment> {
	
	@Autowired
	private ClientCommentService cCommentService;
	
	//POST METHOD -> localhost:8080/restapi/comment/addComment?pubdateComment=yoyo&pubdateID=2&dogID=1
	@PostMapping("/addComment")
	public Comment addComment(@RequestParam(required = true) String pubdateComment, String pubdateID, String dogID){
		
		if ((pubdateComment == null) || (pubdateID == null || (dogID == null)))  {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pubdateComment or pubdateID or dogID is empty");
		}
		

		Comment comment = cCommentService.createComment(pubdateComment, pubdateID, dogID);
		if (comment == null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "no such dog registered or pubdate found");
		}
		return comment;
	}
}
