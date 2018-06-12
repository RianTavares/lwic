package film.parser;

public class DOC {
	private int docno;
	private String title;
	private int words; 
	private int uniq_words;
	public int getDocno() {
		return docno;
	}
	public void setDocno(int docno) {
		this.docno = docno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getWords() {
		return words;
	}
	public void setWords(int words) {
		this.words = words;
	}
	public int getUniq_words() {
		return uniq_words;
	}
	public void setUniq_words(int uniq_words) {
		this.uniq_words = uniq_words;
	}
	
}
