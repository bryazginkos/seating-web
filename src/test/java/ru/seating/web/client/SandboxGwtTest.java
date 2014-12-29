package ru.seating.web.client;

import com.google.gwt.junit.client.GWTTestCase;
import org.junit.Test;

public class SandboxGwtTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "ru.seating.web.webseating";
    }

    @Test
    public void testSandbox() {
        assertTrue(true);
    }
}
