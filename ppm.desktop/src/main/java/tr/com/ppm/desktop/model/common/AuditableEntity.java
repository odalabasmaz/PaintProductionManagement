package tr.com.ppm.desktop.model.common;

import java.util.Date;

/**
 * @author Orhun Dalabasmaz
 */
public abstract class AuditableEntity {
	private String id;
	private String createdUsername;
	private Date createdDate;
	private String updatedUsername;
	private Date updatedDate;
	private boolean logicallyDeleted;
}
