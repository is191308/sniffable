package at.fhstp.bis19.prog4.snowdogs.sniffable.repo;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;

@Repository
public interface DogRepo extends BaseCrudRepository<Dog> {
	public Optional<Dog> findByNameIgnoreCase(String name);
}
