package sales.taxes.item.math;

public class Rounder implements IRounder {

    public double roundToCent(double value) {
        return ((double) Math.round((value * 100))) / 100;
    }

    public double roundUpToFiveCents(double value) {
        return Math.ceil((value * 20)) / 20;
    }
}
