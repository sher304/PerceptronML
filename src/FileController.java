import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileController {
    public List<Flower> loadData(String path) {
        List<Flower> flowers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (!tokens[4].equals("setosa") && !tokens[4].equals("versicolor")) {
                    continue;
                }
                double[] features = new double[4];
                for (int i = 0; i < 4; i++) {
                    features[i] = Double.parseDouble(tokens[i]);
                }
                String label = tokens[4];
                switch (label) {
                    case "setosa" -> flowers.add(new Flower(features, label, 1));
                    case "versicolor" -> flowers.add(new Flower(features, label, 0));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flowers;
    }
}
