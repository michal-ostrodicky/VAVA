package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;



@Entity
@Table(name = "Users")
public class User implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length = 100)
	private String login;
	
	@Column(length = 20)
	private String password;
	
	public User() {
	}
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "movie_list_id")
	private FavouriteList favouriteMovies = new FavouriteList();
	
		
	public User(String login, String password){
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public FavouriteList getFavouriteMovies() {
		return favouriteMovies;
	}

	public void setFavouriteMovies(FavouriteList favouriteMovies) {
		this.favouriteMovies = favouriteMovies;
	}
	
	
	
	
	
	
	
}
