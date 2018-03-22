import model.Philosopher;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by callummarriage on 20/03/2018.
 */
public class PhilosopherTest {
    @Test
    public void speachTest(){
        assertTrue(new Philosopher("Steve").speak("In general, polymorphism describes multiple possible states for a single property (it is said to be polymorphic).").contains("Ah-Hem!"));
    }

}
