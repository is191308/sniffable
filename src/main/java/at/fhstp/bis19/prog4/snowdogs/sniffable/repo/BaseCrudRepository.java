package at.fhstp.bis19.prog4.snowdogs.sniffable.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.BaseEntity;

@Repository
public interface BaseCrudRepository<T extends BaseEntity> extends CrudRepository<T, Integer> {

}
