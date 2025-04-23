import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class App {

    public static List<List<String>> Combo(List<List<String>> records){ //Only combos of 10 items with Weights below 100
        
        
        ArrayList<List<String>> Best = new ArrayList<>();
        Double Value = 0.0;
        Double Weight = 0.0;
        Collections.shuffle(records);
        
        for (int j = 0; j < 20; j++ ) {
            for (int i = 0; i < 10; i++) {
                
                Value += Double.parseDouble(records.get(i).get(1));
                Weight += Double.parseDouble(records.get(i).get(2));
                System.out.println(records.get(i));
                Best.add(records.get(i));
            }
            if (Weight > 100){
                Value = 0.0;
            }

            
            System.out.println(Weight);
            System.out.println(Value);
           
        }
        return records;
    }
    public static void main(String[] args) {
        String csvFile = "src\\random_items.csv";
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