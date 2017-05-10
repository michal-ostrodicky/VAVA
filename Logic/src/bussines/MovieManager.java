package bussines;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import exceptions.NotReadableImageException;
import remotes.MovieManagerRemote;
import model.Movie;

/**
 * Session Bean implementation class MovieManager
 */
@Stateless
@LocalBean
public class MovieManager implements MovieManagerRemote {

	@PersistenceContext(unitName = "DBConnection")
	private EntityManager entityManager;
	
	@EJB
	private QRCodeWorker  qrCodeWorker;
	 
	

	@Override
	public void saveMovie(Movie movie) {
		entityManager.persist(movie);
	}

	@Override
	public Movie getMovie(int id) {
		return entityManager.find(Movie.class, id);
	}

	@Override
	public Movie getMovie(byte[] imageQR) throws NotReadableImageException {
		
		int id = qrCodeWorker.getIdFromQRCode(imageQR);
		return this.getMovie(id);
	}

	@Override
	public List<Movie> getNewMovies() {
		
		
		Query newMovies = entityManager.createNamedQuery("findNewMovies", Movie.class).setMaxResults(100);
		List<Movie> movies = newMovies.getResultList();
		
		return movies;
	}
	
	
	
	
	
    
    
    

}
