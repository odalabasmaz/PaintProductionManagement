package tr.com.ppm.desktop.model.user;

import tr.com.ppm.desktop.model.common.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Orhun Dalabasmaz
 */
@Entity
@Table(name = "USER")
public class User extends AuditableEntity {

	@Column(name = "NAME")
	private String name;

	@Column(name = "SURNAME")
	private String surname;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "LASTPWCHANGEDDATE")
	private Date lastPasswordChangedDate;

//	private Set<Role> roles;


	public User(String name, String surname, String username, String password, Date lastPasswordChangedDate) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.lastPasswordChangedDate = lastPasswordChangedDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastPasswordChangedDate() {
		return lastPasswordChangedDate;
	}

	public void setLastPasswordChangedDate(Date lastPasswordChangedDate) {
		this.lastPasswordChangedDate = lastPasswordChangedDate;
	}
}
