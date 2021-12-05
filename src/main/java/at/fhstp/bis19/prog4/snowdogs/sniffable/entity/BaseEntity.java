package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class for Entities
 */
@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue
	private int id;

	public int getId() {
		return id;
	}
}
