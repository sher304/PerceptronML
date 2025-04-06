import java.util.List;

public class FlowerResult {
    public List<Flower> trainSet;
    public List<Flower> testSet;

    public FlowerResult(List<Flower> trainSet, List<Flower> testSet) {
        this.trainSet = trainSet;
        this.testSet = testSet;
    }
}
