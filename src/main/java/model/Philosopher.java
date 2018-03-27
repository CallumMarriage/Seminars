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
		List<Integer> previousNumbers = new ArrayList<Integer>();

		Boolean alreadyDone = false;

		//randomizes the location of the ahems within the line
		for(int i = 0; i <= numberOfAhems; i++){
			int positionInString = random.nextInt(brokenSpeech.length -1) + 1;
			for(Integer prevNumber : previousNumbers){
				//if that position already has an ahem, break. This is in order to prevent multiple A hems in a row.
				if(prevNumber == positionInString){
					alreadyDone = true;
					break;
				}
			}
			//if its not already done add the a hem to the string at the position of the random number
			if(!alreadyDone) {
				brokenSpeech[positionInString] = brokenSpeech[positionInString] + " Ah-Hem!";
			}
			//add the latest random position to the list of used ones
			previousNumbers.add(positionInString);
			//reset the already done value
			alreadyDone = false;
		}
		//rebuild the string from the array. Have to re add the space as it was removed in order to split the array
		StringBuilder sb = new StringBuilder();
		for(String item : brokenSpeech){
			sb.append(item);
			sb.append(" ");
		}

		return sb.toString();
	}
}
