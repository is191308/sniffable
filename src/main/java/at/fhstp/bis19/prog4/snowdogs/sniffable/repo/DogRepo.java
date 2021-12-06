package at.fhstp.bis19.prog4.snowdogs.sniffable.repo;

import java.util.List;

import org.springframework.stereotype.Repository;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;

@Repository
public interface DogRepo extends BaseCrudRepository<Dog> {
	public List<Dog> findByNameIgnoreCase(String name);
}
