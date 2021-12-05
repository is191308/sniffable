package at.fhstp.bis19.prog4.snowdogs.sniffable.repo;

import org.springframework.stereotype.Repository;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;

@Repository
public interface CommentRepo extends BaseCrudRepository<Comment> {

}
