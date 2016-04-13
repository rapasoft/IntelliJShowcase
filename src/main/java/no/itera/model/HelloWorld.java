package no.itera.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Pavol Rajzak, Itera.
 */
@Entity
public class HelloWorld {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@Column(name = "MESSAGE")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getId() {
		return id;
	}
}
