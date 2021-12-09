package at.fhstp.bis19.prog4.snowdogs.sniffable.controller.generic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.BaseEntity;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.BaseCrudRepository;

public class GenericContoller<T extends BaseEntity> {
	private final String masterkeyHeaderAttribute = "masterkey";
	private final String masterkeyConfigProperty = "sniffers.restapi.masterKey";
	
	@Autowired
	private Environment env;

	@Autowired
	private BaseCrudRepository<T> repo;

	@GetMapping
	public List<T> read(@RequestHeader(masterkeyHeaderAttribute) String headerKey) {
		String mk = env.getProperty(masterkeyConfigProperty);
		if (mk != null && mk.equals(headerKey)) {
			return (List<T>) repo.findAll();
		}
		return null;
	}

	@GetMapping(value = "{id}")
	public T readByID(@PathVariable(value = "id") int id, @RequestHeader(masterkeyHeaderAttribute) String headerKey) {
		String mk = env.getProperty(masterkeyConfigProperty);
		if (mk != null && mk.equals(headerKey)) {
			return repo.findById(id).orElse(null);
		}
		return null;
	}

	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable(value = "id") int id, @RequestHeader(masterkeyHeaderAttribute) String headerKey) {
		String mk = env.getProperty(masterkeyConfigProperty);
		if (mk != null && mk.equals(headerKey)) {
			repo.deleteById(id);
		}
	}
	
	/*@PutMapping(value = "{id}")
	public T update(@PathVariable(value = "id") int id, @RequestBody T entity, @RequestHeader(masterkeyHeaderAttribute) String headerKey) {
		String mk = env.getProperty(masterkeyConfigProperty);
		if (mk != null && mk.equals(headerKey)) {
			return repo.save(entity);
		}
		return null;
	}
	
	@PostMapping
	public T create(@RequestBody T entity, @RequestHeader("masterkey") String headerKey) {
		String mk = env.getProperty(masterkeyConfigProperty);
		if (mk != null && mk.equals(headerKey)) {
			return repo.save(entity);
		}
		return null;
	}*/

}
