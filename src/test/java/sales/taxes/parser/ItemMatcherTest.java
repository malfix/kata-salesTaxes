package sales.taxes.parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemMatcherTest {
    @Test
    public void isAnExemptItem() {
        ItemMatcher itemMatcher = new ItemMatcher();
        assertTrue(itemMatcher.isAnExemptItem("book"));
        assertTrue(itemMatcher.isAnExemptItem("chocolate"));
        assertTrue(itemMatcher.isAnExemptItem("chocolates"));
        assertTrue(itemMatcher.isAnExemptItem("headache pills"));
        assertFalse(itemMatcher.isAnExemptItem("music"));
    }
}