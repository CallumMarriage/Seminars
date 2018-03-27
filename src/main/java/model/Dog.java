package model;

import static Constants.SpeakerConstants.*;

/**
 * A model.Dog object models limited behaviours of a dog.
 * A model.Dog can speak, so it can introduce itself and even make a speech.
 * 
 * @author Sylvia Wong
 * @version 25/09/10
 */
/* !!!! Modify the class declaration to define the
 * 		realisation relationship between classes model.Dog and model.Speaker.
 */
public class Dog implements Speaker {

	// the name of this dog object
	private String name;
	
	/**
	 * Constructor for creating a model.Dog object
	 * @param name	the name of the dog
	 */
	public Dog(String name){
		this.name = name;
	}

	/**
	 * A dog introduces itself
	 * @return the self introduction statement 
	 */
	public String introduceOneself() {
		return speak(NAME_INTRO + name + ". " + THANKS );
	}

	/**
	 * Speak out an utterance with "Woof!"s in between.
	 * @param speech	the utterance for speaking out
	 * @return the uttered speech with a dog's speaking manner 
	 */
	public String speak(String speech) {
		// split the text string into word tokens
		String[] text = speech.split(" ");

		//start at 4 because the 5th word is at position 4 in the array
		for(int i = 4; i <= text.length-1; i+=5){
			String word = text[i];
			//make sure the woof is always added directly after the 5th word.
			if(!word.contains("\n")){
				text[i] = word.trim() + " Woof!";
			} else{
				text[i] = word.trim() + " Woof!\n";
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < text.length; i++){
			//make sure it looks like a sentance
			sb.append(text[i]);
			if(!text[i].contains("\n") && i != text.length -1){
				sb.append(" ");
			}
		}
		return sb.toString();
	}
}
