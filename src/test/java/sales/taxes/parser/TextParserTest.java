package sales.taxes.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TextParserTest {
    private double tollerance = 0.00;

    @Test
    public void getQuantity() {
        assertQuantity(1, "1 book at 12.49");
        assertQuantity(1.5, "1.5 book at 12.49");
        assertQuantity(1.599999999, "1.599999999 book at 12.49");
        assertQuantity(1.3, "01.3 book at 12.49");
    }

    @Test
    public void getName() {
        assertName("book", "1 book at 12.49");
        assertName("box of chocolates", "1 imported box of chocolates at 10.00");
        assertName("trim works", "1 imported        trim works         at 10.00");
        assertName("box of chocolates", "1 box of imported chocolates at 11.25");
        assertName("box of importedchocolates", "1 box of importedchocolates at 11.25");
        assertName("rhum", "1 rhum from cuba at 11.25");
    }

    @Test
    public void getBasePrice() {
        assertBasePrice(12.49, "1 book at 12.49");
        assertBasePrice(12, "1 book at 12");
        assertBasePrice(32.49, "1 book at 032.49");
    }

    @Test
    public void isImported() {
        assertIsImported(false, "1 book at 12.49");
        assertIsImported(true, "1 imported box of chocolates at 10.00");
        assertIsImported(true, "1 box of imported chocolates at 11.25");
    }

    @Test
    public void checkDefaultRegex() {
        assertEquals("([0-9]*(?:\\.[0-9]+)?) ([a-z ]+) at ([0-9]+(?:\\.[0-9]+)?)",
                new ItemReader(new ItemMatcher()).pattern.pattern());
    }

    private void assertBasePrice(double expected, String textToParse) {
        assertEquals(expected, buildTextParser().getBasketItem(textToParse).getItem().getPrice(), tollerance);
    }

    private void assertQuantity(double expected, String textToParse) {
        assertEquals(expected, buildTextParser().getBasketItem(textToParse).getQuantity(), tollerance);
    }

    private void assertName(String expected, String textToParse) {
        assertEquals(expected, buildTextParser().getBasketItem(textToParse).getItem().getName());
    }

    private void assertIsImported(boolean expected, String textToParse) {
        assertEquals(expected, buildTextParser().getBasketItem(textToParse).getItem().isImported());
    }

    private ItemReader buildTextParser() {
        return new ItemReader(
                new ItemMatcher(),
                "imported", "from cuba");
    }
}
