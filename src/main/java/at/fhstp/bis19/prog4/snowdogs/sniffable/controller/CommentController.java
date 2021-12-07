package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.ClientCommentService;

@RestController("CommentController")
@RequestMapping("/restapi/comment")
public class CommentController extends GenericContoller<Comment> {
	
	@Autowired
	private ClientCommentService cCommentService;
	
	//POST METHOD -> localhost:8080/restapi/pubdate/addPubdate/.........
	@PostMapping("/addComment")
	public Comment addComment(@RequestParam(required = true) String comment, String pubdateID){
		
		//toDo
		
		return (new Comment());
	}
}
