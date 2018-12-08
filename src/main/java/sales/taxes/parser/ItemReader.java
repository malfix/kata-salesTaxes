package sales.taxes.parser;

import sales.taxes.BasketItem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemReader implements IItemReader {
    Pattern pattern = Pattern.compile("([0-9]*(?:\\.[0-9]+)?) ([a-z ]+) at ([0-9]+(?:\\.[0-9]+)?)",
            Pattern.CASE_INSENSITIVE);
    private IItemMatcher itemMatcher;
    private String[] importedTokens;

    public ItemReader(IItemMatcher itemMatcher, String... importedTokens) {
        this.itemMatcher = itemMatcher;
        this.importedTokens = importedTokens;

    }

    public BasketItem getBasketItem(String textToParse) {
        Matcher matcher = pattern.matcher(textToParse);
        if (!matcher.matches())
            return null;
        double basePrice = Double.parseDouble(matcher.group(3));
        double quantity = Double.parseDouble(matcher.group(1));
        boolean isImported = false;
        String name = matcher.group(2).trim();
        for (String importedToken : importedTokens) {
            if (name.contains(importedToken)) {
                isImported = true;
                name = name.replaceAll("(^|\\s)" + importedToken + "(\\s|$)", " ").trim();
            }
        }
        return new BasketItem(quantity, itemMatcher.match(basePrice, isImported, name));

    }


}
