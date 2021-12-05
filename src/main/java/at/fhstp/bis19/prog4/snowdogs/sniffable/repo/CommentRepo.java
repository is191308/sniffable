package at.fhstp.bis19.prog4.snowdogs.sniffable.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Integer> {

}
