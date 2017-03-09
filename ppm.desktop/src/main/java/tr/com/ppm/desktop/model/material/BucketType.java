package tr.com.ppm.desktop.model.material;

import tr.com.ppm.desktop.model.common.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author UTKU ADAK :)
 */
@Entity
@Table(name = "BUCKET_TYPE")
public class BucketType extends AuditableEntity {
	@Column(name = "BUCKET_SIZE")
	private String bucket_size;

	@Column(name = "BUCKET_UNIT")
	private String bucket_unit;

	/* for hibernate */
	public BucketType(String bucket_size, String bucket_unit) {
	}

	public String getBucket_unit() {
		return bucket_unit;
	}

	public void setBucket_unit(String bucket_unit) {
		this.bucket_unit = bucket_unit;
	}

	public String getBucket_size() {
		return bucket_size;
	}

	public void setBucket_size(String bucket_size) {
		this.bucket_size = bucket_size;
	}

	@Override
	public String toString() {
		return getBucket_size() + getBucket_unit();
	}
}
