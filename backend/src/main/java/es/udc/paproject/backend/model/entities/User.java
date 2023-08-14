package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
	
	public enum RoleType {ESPECTADOR, TAQUILLERO};

	private Long id;
	private Set<Compra> compras = new HashSet<>();
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private RoleType role;

	public User() {}

	public User(String userName, String password, String firstName, String lastName, String email, RoleType role) {

		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		
	}

	public User(String userName, String password, String firstName, String lastName, String email) {

		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "user")
	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

}
