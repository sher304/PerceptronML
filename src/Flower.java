import java.util.Arrays;

public class Flower {
    private double[] features;
    private String title;
    private int label;
    public Flower(double[] features, String title, int label) {
        this.features = features;
        this.title = title;
        this.label = label;
    }

    @Override
    public String toString() {
        return title + " " + Arrays.toString(features) + " Label: " + label;
    }

    public double[] getFeatures() {
        return features;
    }

    public String getTitle() {
        return title;
    }

    public int getLabel() {
        return label;
    }
}

