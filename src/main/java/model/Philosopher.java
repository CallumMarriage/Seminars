package model;

import java.util.*;

import static Constants.SpeakerConstants.*;

/**
 * A philosopher object models limited behaviours of a philosopher.
 * A philosopher can speak. However, when making a speech, 
 * random "Ah-Hem!" introduced into their speech.
 *  
 * @author Sylvia Wong
 * @version 25/09/10
 */
/* !!!! Modify the class declaration to define the
 * 		realisation relationship between classes model.Philosopher and model.Speaker.
 */
public class Philosopher implements Speaker{
	// the name of the philosopher
	private String name;
	
	/**
	 * Constructor
	 * @param name	the name of the philosopher
	 */
	public Philosopher(String name){
		this.name = name;
	}
	
	/**
	 * Introduce myself. A model.Philosopher doesn't go "Ah-Hem!"
	 * while introducing himself/herself.
	 */
	public String introduceOneself() {
		return NAME_INTRO + name + ". " + THANKS;
	}

	public String speak(String speech) {

		String[] brokenSpeech = speech.split(" ");

		Random random = new Random();
		//reasonable amount of a-hems
		int numberOfAhems = random.nextInt(brokenSpeech.length/2) +1;

		//randomizes the location of the ahems within the line
		for(int i = 0; i <= numberOfAhems; i++){
			int positionInString = random.nextInt(brokenSpeech.length -1) + 1;
			//if its not already done add the a hem to the string at the position of the random number
			if(!brokenSpeech[positionInString].contains(" Ah-Hem!")) {
				brokenSpeech[positionInString] += " Ah-Hem!";
			}
		}
		//rebuild the string from the array.

		return String.join(" ", brokenSpeech);
	}
}
