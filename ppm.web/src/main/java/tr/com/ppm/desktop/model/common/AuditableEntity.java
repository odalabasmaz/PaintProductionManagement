package tr.com.ppm.desktop.model.common;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Orhun Dalabasmaz
 */
@MappedSuperclass
public abstract class AuditableEntity {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "CREATED_USERNAME")
	private String createdUsername;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDATED_USERNAME")
	private String updatedUsername;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "DELETED")
	private boolean deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedUsername() {
		return createdUsername;
	}

	public void setCreatedUsername(String createdUsername) {
		this.createdUsername = createdUsername;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedUsername() {
		return updatedUsername;
	}

	public void setUpdatedUsername(String updatedUsername) {
		this.updatedUsername = updatedUsername;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
