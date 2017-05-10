package remotes;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.ejb.Remote;
import javax.imageio.ImageIO;

import exceptions.NotReadableImageException;
import model.Movie;

@Remote
public interface MovieManagerRemote {
	
	void saveMovie(Movie movie);
	
	Movie getMovie(int id);
	
	
	Movie getMovie(byte[] imageQR) throws NotReadableImageException;
	
	List<Movie> getNewMovies();
	

}
