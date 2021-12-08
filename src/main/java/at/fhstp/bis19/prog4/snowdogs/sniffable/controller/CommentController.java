package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;

@RestController("CommentController")
@RequestMapping("/restapi/comment")
public class CommentController extends GenericContoller<Comment> {
	
	
	

}
