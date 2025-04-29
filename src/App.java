import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class App extends ListSort{
    
    public App(ArrayList<List<List<String>>> generation, Double Value, Double Weight) {
        super(generation, Value, Weight);
    }
    
    public static ArrayList<ListSort> Combo(List<List<String>> records){ //Only combos of 10 items with Weights below 100
        
        
        ArrayList<List<String>> Parent = new ArrayList<>(); //Creates an Array that will hold 10 random Parent Objects
        ArrayList<List<List<String>>> Generation = new ArrayList<>(); //Will create a sets of 10 objects {Parent(),Parent()...}
        ArrayList<ListSort> Simulation = new ArrayList<>();

        Double Value = 0.0;
        Double Weight = 0.0;
        
        
        for (int j = 0; j < 20; j++ ) {
            Collections.shuffle(records); //creates 20 Different Parent Generations of random Items to Start
            Weight = 0.0;
            Value = 0.0;
            
            for (int i = 0; i < 10; i++) {
                
                Value += Double.parseDouble(records.get(i).get(1));
                Weight += Double.parseDouble(records.get(i).get(2));
                //System.out.println(records.get(i));
                Parent.add(records.get(i));
            
            if (Weight > 100){
                Value = 0.0;
                }
            }
            Generation.add(new ArrayList<>(Parent));
            ListSort GenerationObject = new ListSort(new ArrayList<>(Generation), Value, Weight);
            Simulation.add(GenerationObject);

            Parent.clear();
            Generation.clear();
            //System.out.println(GenerationObject.getValue());
        }  
        return Simulation;
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

        ArrayList<ListSort> Simulation = Combo(records);
        GeneticSim(Simulation, records);
    }

     public static void GeneticSim(ArrayList<ListSort> Simulation, List<List<String>> records){
        
        for (int i = 0; i < Simulation.size(); i++){
            for (int j = i+1; j < Simulation.size(); j++){
                
                ListSort tmp = new ListSort(null, null, null);
                
                if(Simulation.get(j).getValue() < Simulation.get(i).getValue()){ //Brute Force sorting
                    //Swapping
                    tmp = Simulation.get(i);
                    Simulation.set(i, Simulation.get(j));
                    Simulation.set(j, tmp);
                }
            }
            System.out.println(Simulation.get(i).getValue());
        }
    }
}