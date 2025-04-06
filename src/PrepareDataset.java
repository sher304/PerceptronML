import java.util.Collections;
import java.util.List;

public class PrepareDataset {

   public static FlowerResult trainTestSplit(List<Flower> flowerList) {
       Collections.shuffle(flowerList);
       int trainSize = (int)(flowerList.size() * 0.7);
       List<Flower> trainSet = flowerList.subList(0, trainSize);
       List<Flower> testSet = flowerList.subList(trainSize, flowerList.size());
       return new FlowerResult(trainSet, testSet);
   }
}
