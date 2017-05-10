package lang;
import java.util.Locale;


public class Language {
	
	private  Locale language;
	
	private static Language  instance = null;
	
	
	private Language(){
		
	}
	
	public static Language getInstance(){
		if(instance  == null){
			instance = new Language();
		}
		return instance;
	}

	public Locale getLanguage() {
		return language;
	}

	public void setLanguage(Locale language) {
		this.language = language;
	}
	
	
	
	
	
	
	
}
