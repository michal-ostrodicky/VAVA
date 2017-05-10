package remotes;

import java.util.List;

import javax.ejb.Remote;

import model.Movie;
import model.User;


@Remote
public interface UserManagerRemote {
	
	int login(String login, String password);
	
	User getUser(int id);
	
	void createUser(String login, String password);
	
	void addFavouriteMovieToList(int movieId, int userId);
	
	
	
	
	
}
