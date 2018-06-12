package film.parser;

public class FilmDataBase {
	
	private String movie_title;
	private String duration;
	private String budget;
	private String title_year;
	private String imdb_score;
	private String director_facebook_likes;
	private String movie_facebook_likes;
	private String cast_total_facebook_likes;

	

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getTitle_year() {
		return title_year;
	}

	public void setTitle_year(String d) {
		this.title_year = d;
	}

	public String getImdb_score() {
		return imdb_score;
	}

	public void setImdb_score(String imdb_score) {
		this.imdb_score = imdb_score;
	}

	public String getDirector_facebook_likes() {
		return director_facebook_likes;
	}

	public void setDirector_facebook_likes(String director_facebook_likes) {
		this.director_facebook_likes = director_facebook_likes;
	}

	public String getMovie_facebook_likes() {
		return movie_facebook_likes;
	}

	public void setMovie_facebook_likes(String movie_facebook_likes) {
		this.movie_facebook_likes = movie_facebook_likes;
	}

	public String getCast_total_facebook_likes() {
		return cast_total_facebook_likes;
	}

	public void setCast_total_facebook_likes(String cast_total_facebook_likes) {
		this.cast_total_facebook_likes = cast_total_facebook_likes;
	} 

}
