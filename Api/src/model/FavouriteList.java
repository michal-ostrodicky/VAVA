package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name = "favourite_lists")
public class FavouriteList implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;
		
	
	@ManyToMany(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private List<Movie> movies = new ArrayList<Movie>();

	
	
	public FavouriteList(){
		
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Movie> getMovies() {
		return movies;
	}


	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	
	
	
	
}
