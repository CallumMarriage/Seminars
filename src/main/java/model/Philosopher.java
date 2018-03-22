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
		int numberOfRandom = random.nextInt(brokenSpeech.length/2) +1;
		List<Integer> previousNumbers = new ArrayList<Integer>();

		Boolean alreadyDone = false;

		for(int i = 0; i < numberOfRandom; i++){

			int n = random.nextInt(brokenSpeech.length -1) + 1;
			for(Integer prevNumber : previousNumbers){
				if(prevNumber == n){
					alreadyDone = true;
					break;
				}
			}
			if(!alreadyDone) {
				brokenSpeech[n] = brokenSpeech[n] + " Ah-Hem!";
			}
			previousNumbers.add(n);
			alreadyDone = false;
		}

		StringBuilder sb = new StringBuilder();
		for(String item : brokenSpeech){
			sb.append(item);
			sb.append(" ");
		}

		return sb.toString();
	}
}
