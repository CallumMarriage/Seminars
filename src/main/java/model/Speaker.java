package model;

/**
 * The interface model.Speaker is implemented by classes that model objects who
 * can speak. Every object that is meant to speak must implement 
 * the abstract methods specified in this interface.
 * 
 * @author Sylvia Wong
 * @version	30/09/05
 */
public interface Speaker {

	
	/**
	 * A speaker introduces themselves 
	 * @return the self introduction message
	 */
	String introduceOneself();
	
	/**
	 * A speaker gives a speech by speaking it out. As they speak, their
	 * own speaking manner (e.g. throat-clearing sound, stutter, 
	 * unconventional pauses, etc.) is also introduced into 
	 * the speech content.
	 *  
	 * @param speech the content of the speech that is to be made
	 * @return the speech itself as delivered by the speaker 
	 * (i.e. speech content + speaking manner)
	 */
	String speak(String speech);

}
