import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static List<List<String>> Combo(List<List<String>> records){ //Only combos of 10 items with Weights below 120
        
        List<List<String>> Best = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            System.out.println(records.get(i));
        }

        return records;
    }
    public static void main(String[] args) {
        String csvFile = "random_items.csv";
        String line;
        String csvSplitBy = ",";
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Combo(records);
    }
}