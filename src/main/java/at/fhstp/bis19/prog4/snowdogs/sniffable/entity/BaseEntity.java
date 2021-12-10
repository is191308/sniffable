package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class for Entities
 * defines unique id
 * implements hashCode and equals
 */
@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue
	private int id;

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		return id == other.id;
	}
	
	
}
