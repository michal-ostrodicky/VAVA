package bussines;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Movie;
import model.User;
import remotes.UserManagerRemote;

/**
 * Session Bean implementation class UserManager
 */
@Stateless
public class UserManager implements UserManagerRemote{
	
	@PersistenceContext(unitName = "DBConnection")
	private EntityManager entityManager;
	
	
	@EJB
	private MovieManager movieManager;

    /**
     * Default constructor. 
     */
    public UserManager() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public int login(String login, String password) {
		
		String hql = "SELECT U.id FROM User U " + 
		"WHERE U.login = :login AND U.password = :password";
		
		Query query = entityManager.createQuery(hql);
		query.setParameter("login", login);
		query.setParameter("password", password);
		
		int userId = (int) query.getSingleResult();
		
		return userId;
		
	}

	@Override
	public User getUser(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public void createUser(String login, String password) {	
		User user = new User(login, password);
		entityManager.persist(user);
	}

	@Override
	public void addFavouriteMovieToList(int movieId, int userId) {
		User user = getUser(userId);
		Movie movie = movieManager.getMovie(movieId);
		user.getFavouriteMovies().getMovies().add(movie);
	}

	
	
    
    

}
