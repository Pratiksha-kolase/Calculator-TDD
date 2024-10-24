import org.example.CalculatorApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    public CalculatorApp calculatorApp;

    @BeforeEach
    void setUp() {
        calculatorApp = new CalculatorApp();
    }

    @Test
    public void testApp() {
        assertEquals(3, calculatorApp.add("1,2"));
    }

    @Test
    public void testWithNegative() {
        assertEquals(4, calculatorApp.add("1,2,-1"));
    }

    @Test
    public void allNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculatorApp.add("-1,-2,-3,-4");
        });
        assertEquals("Negative numbers not allowed: -1, -2, -3, -4", exception.getMessage());
    }

    @Test
    public void testWithChars() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculatorApp.add("1,1,s,2");
        });
        assertEquals("Invalid Number Format: s", exception.getMessage());
    }

    @Test
    public void testWithNewLine() {
        assertEquals(6, calculatorApp.add("1,\n,2,3"));
    }

    @Test
    public void testWithDifferentDelimeters() {
        assertEquals(6, calculatorApp.add("1,2,3"));
        assertEquals(6, calculatorApp.add("1,2\t3"));
    }
}
