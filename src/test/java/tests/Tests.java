package tests;

import delivery.DeliveryApp;
import org.junit.Before;
import org.junit.Test;

public class Tests {
    DeliveryApp app;

    @Before
    public void SetUp() {
        app = new DeliveryApp();
    }

    @Test
    public void simple01() {
        //app.start("src/main/resources/testdata/simple01.in");
    }

    @Test
    public void example() {
        //app.start("src/main/resources/testdata/example.in");
    }

}
