import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        FileController fileController = new FileController();
        List<Flower> flowerList = fileController.loadData("src/iris.csv");

        PrepareDataset splitter = new PrepareDataset();
        FlowerResult split = splitter.trainTestSplit(flowerList);
        List<Flower> trainSet = split.trainSet;
        List<Flower> testSet = split.testSet;

        double[][] trainInputs = new double[trainSet.size()][];
        int[] trainLabels = new int[trainSet.size()];
        for (int i = 0; i < trainSet.size(); i++) {
            trainInputs[i] = trainSet.get(i).getFeatures();
            trainLabels[i] = trainSet.get(i).getLabel();
        }

        int dimension = trainInputs[0].length;
        double learningRate = 0.05;
        double threshold = 0.0;
        Perceptron perceptron = new Perceptron(dimension, threshold, learningRate);
        perceptron.train(trainInputs, trainLabels);

        List<Integer> realLabels = new ArrayList<>();
        List<Integer> predictedLabels = new ArrayList<>();
        for (Flower flower : testSet) {
            double[] features = flower.getFeatures();
            int real = flower.getLabel();
            int predicted = perceptron.predict(features);
            realLabels.add(real);
            predictedLabels.add(predicted);
        }

        System.out.println(Arrays.toString(perceptron.getWeights()));

        System.out.println(perceptron.getThreshold());
        EvaluationMetrics evaluationMetrics = new EvaluationMetrics();
        System.out.println("Accuracy: " + evaluationMetrics.measureAccuracy(realLabels, predictedLabels));
//        plotDecisionRough(perceptron);
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Predict your own flower!");
//        System.out.print("Enter sepal length: ");
//        double sepalLength = scanner.nextDouble();
//        System.out.print("Enter sepal width: ");
//        double sepalWidth = scanner.nextDouble();
//        System.out.print("Enter petal length: ");
//        double petalLength = scanner.nextDouble();
//        System.out.print("Enter petal width: ");
//        double petalWidth = scanner.nextDouble();
//        double[] input = {sepalLength, sepalWidth, petalLength, petalWidth};
//        int prediction = perceptron.predict(input);
//        System.out.println("Predicted class: " + (prediction == 1 ? "Setosa" : "Not Setosa"));
    }

    public static void plotDecisionRough(Perceptron p) {
        System.out.println("Plot the resulting decision:");
        for (int i = 10; i >= -10; i--) {
            for (int j = -10; j <= 10; j++) {
                double a = j * 0.5;
                double b = i * 0.5;
                double[] point = { a, b };

                int result = p.predict(point);

                if (result == 1) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }


}