package model;

/**
 * A seminar object models a seminar that takes place in a conference. 
 * It keeps track of the seminar title, the current speaker and 
 * the content of their expected speech.  
 * 
 * @author Sylvia Wong
 * @version 30/09/05
 */
public class Seminar {

	private static final int DOG = 0;
	
	private static final String ENDNOTE = "\n Thank you!\n";
	
	private String title;		// the seminar title
	private String content;		// the expected content of the speech 
	private Speaker current;	// the speaker for this seminar
	
	/**
	 * Constructor for a model.Seminar object
	 * @param title		the seminar title
	 * @param content	the expected content of the speech
	 * @param speaker	the speaker object that is to give this seminar
	 * @param speakerType	whether the speaker is a dog or a philosopher
	 */
	public Seminar(String title, String content,
					String speaker, int speakerType){
		this.title = title;
		this.content = content;
		switch(speakerType){		
		case DOG : current = new Dog(speaker);
		           break;
		default : current = new Philosopher(speaker);
		          break;
		}
	}
	
	/*
	 * Begin the seminar with the speaker introducing themselves
	 * and the seminar title.
	 */
	private String begin(){
		String intro = current.introduceOneself();
		intro += "I am going to speak about " + title + ".\n";
		return intro;
	}
		
	/*
	 * End the speech by uttering the endnote.
	 */
	private String end(){
		return ENDNOTE;
	}
	
	/**
	 * The current speaker gives the complete seminar.
	 * @param speech
	 * @return what was said in the seminar
	 */
	public String proceed(){
		return begin() + current.speak(content) + end();
	}
	
	/**
	 * Change the current speaker.
	 * @param next	the new speaker
	 */
	public void changeSpeaker(Speaker next){
		current = next;
	}
}
