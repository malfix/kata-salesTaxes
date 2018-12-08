package sales.taxes;

import org.junit.Before;
import org.junit.Test;
import sales.taxes.formatter.ReceiptFormatter;
import sales.taxes.parser.ItemMatcher;
import sales.taxes.parser.ItemReader;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class BasketTest {
    private Basket basket;

    @Before
    public void initializeCart() {
        basket = new Basket(
                new ItemReader(new ItemMatcher(), "imported"),
                new ReceiptFormatter());
    }

    @Test
    public void firstUseCase() {
        basket.addItems(
                "1 book at 12.49",
                "1 music CD at 14.99",
                "1 chocolate bar at 0.85");

        List<String> expectedOutput = Arrays.asList(
                "1 book: 12.49",
                "1 music CD: 16.49",
                "1 chocolate bar: 0.85",
                "Sales Taxes: 1.50",
                "Total: 29.83");

        assertEquals(expectedOutput, basket.getReceipt());
    }

    @Test
    public void secondUseCase() {
        basket.addItems(
                "1 imported box of chocolates at 10.00",
                "1 imported bottle of perfume at 47.50");

        List<String> expectedOutput = Arrays.asList(
                "1 imported box of chocolates: 10.50",
                "1 imported bottle of perfume: 54.65",
                "Sales Taxes: 7.65",
                "Total: 65.15");

        assertEquals(expectedOutput, basket.getReceipt());
    }

    @Test
    public void thirdUseCase() {
        basket.addItems(
                "1 imported bottle of perfume at 27.99",
                "1 bottle of perfume at 18.99",
                "1 packet of headache pills at 9.75",
                "1 box of imported chocolates at 11.25");

        List<String> expectedOutput = Arrays.asList(
                "1 imported bottle of perfume: 32.19",
                "1 bottle of perfume: 20.89",
                "1 packet of headache pills: 9.75",
                "1 imported box of chocolates: 11.85",
                "Sales Taxes: 6.70",
                "Total: 74.68");

        assertEquals(expectedOutput, basket.getReceipt());
    }

    @Test
    public void extraUseCasesMultipleSameItems() {
        basket.addItems(
                "1 music CD at 14.99",
                "1 music CD at 14.99",
                "1 music CD at 14.99"
        );

        List<String> expectedOutput = Arrays.asList(
                "3 music CD: 49.47",
                "Sales Taxes: 4.50",
                "Total: 49.47");
        assertEquals(expectedOutput, basket.getReceipt());
    }


    @Test
    public void extraUseCasesDoNotAddInvalidItems() {
        basket.addItems(
                "1 music CD at 14.99",
                "INVALID ITEMS!!!!"
        );

        List<String> expectedOutput = Arrays.asList(
                "1 music CD: 16.49",
                "Sales Taxes: 1.50",
                "Total: 16.49");
        assertEquals(expectedOutput, basket.getReceipt());
    }
}