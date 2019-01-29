package hello;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class GreeterTest {

    private Greeter greeter = new Greeter();

    @Test
    public void sayHello() {

        assertThat(greeter.sayHello(), containsString("Hello"));
    }
}