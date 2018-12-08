package sales.taxes.item;

import sales.taxes.item.math.IRounder;

import java.util.Objects;

public abstract class AbstractItem implements IItem {
    private boolean isImported;
    private String name;
    private double basePrice;
    private IRounder rounder;

    AbstractItem(boolean isImported, String name, double basePrice, IRounder rounder) {
        this.isImported = isImported;
        this.name = name;
        this.basePrice = basePrice;
        this.rounder = rounder;
    }

    public boolean isImported() {
        return isImported;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return rounder.roundToCent(basePrice);
    }

    public double getTaxes() {
        return rounder.roundUpToFiveCents(getPrice() * getPercentageTaxes());
    }

    public double getTotalPrice() {
        return rounder.roundToCent(getPrice() + getTaxes());
    }

    protected abstract double getBasePercentage();

    double getPercentageTaxes() {
        return isImported() ? rounder.roundToCent(getBasePercentage() + getAdditionalImportPercentage()) : getBasePercentage();
    }

    private double getAdditionalImportPercentage() {
        return 0.05;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractItem that = (AbstractItem) o;
        return isImported == that.isImported &&
                Double.compare(that.basePrice, basePrice) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isImported, name, basePrice);
    }
}
