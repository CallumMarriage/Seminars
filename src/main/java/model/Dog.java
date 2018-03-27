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

		int numberOfWords = text.length;
		StringBuilder sb = new StringBuilder();

		for(int i = 1; i <= numberOfWords; i++){
			if(i != 0 && i % 5 == 0){
				String word = text[i-1];
				sb.append(word.trim());
				sb.append(" Woof!");

				if(word.contains("\n")){
					sb.append("\n");
				}

			} else{
				sb.append(text[i-1]);
			}
			if(!text[i-1].contains("\n")  && i != numberOfWords){
				sb.append(" ");
			}
		}

		return sb.toString();
	}
}
