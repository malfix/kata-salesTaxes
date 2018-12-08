package sales.taxes.item.math;

public interface IRounder {
    double roundToCent(double value);

    double roundUpToFiveCents(double value);
}
