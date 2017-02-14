package tr.com.ppm.desktop.model.common;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Orhun Dalabasmaz
 */
@MappedSuperclass
public abstract class AuditableEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String createdUsername;

	@Column
	private Date createdDate;

	@Column
	private String updatedUsername;

	@Column
	private Date updatedDate;

	@Column
	private boolean logicallyDeleted;
}
