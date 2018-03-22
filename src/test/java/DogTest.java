import model.Dog;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by callummarriage on 20/03/2018.
 */
public class DogTest {
    @Test
    public void speachTest(){
        Dog dog = new Dog("Steve");
        assertEquals("Hi, I am a Dog Woof!",  dog.speak("Hi, I am a Dog"));
        assertEquals("Hi, I am",  dog.speak("Hi, I am"));
        assertEquals("Hi, I am a Dog. Woof!\nHow are you this morning? Woof!", dog.speak("Hi, I am a Dog.\n How are you this morning?"));
        assertEquals("Hi, I am a Dog. Woof!\nHow are you?", dog.speak("Hi, I am a Dog.\n How are you?"));

    }
}
