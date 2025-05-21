import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class App extends ListSort{
    
    public App(List<List<String>> generation, Double Value, Double Weight) {
        super(generation, Value, Weight);
    }
    
    public static ArrayList<ListSort> Combo(List<List<String>> records){ //Only combos of 10 items with Weights below 100
        
        ArrayList<ListSort> Simulation = new ArrayList<>();
        ArrayList<List<List<String>>> Generation = new ArrayList<>(); //Will create a sets of 10 objects {Parent(),Parent()...}

        Double Value = 0.0;
        Double Weight = 0.0;
        
        
        for (int j = 0; j < 20; j++ ) {
            ArrayList<List<String>> Parent = new ArrayList<>(); //Creates an Array that will hold 10 random Parent Objects
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
            List<List<String>> generationCopy = new ArrayList<>(Parent);

            ListSort GenerationObject = new ListSort(new ArrayList<>(generationCopy), Value, Weight);
            Simulation.add(GenerationObject);
            //System.out.println(Simulation.get(j).getGeneration().get(j));

            Parent.clear();
            //System.out.println(j + "\n " + GenerationObject.getGeneration().get(0));
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
        
         
        ArrayList<List<String>> Parent = new ArrayList<>(); //Creates an Array that will hold 10 random Parent Objects
        ArrayList<List<List<String>>> Generation = new ArrayList<>(); //Will create a sets of 10 objects {Parent(),Parent()...}
        Double[] Values = new Double[20];
        Double[] Weights = new Double[20];
        
        int Chromosome = 0;
        Double Weight = 0.0;
        Double Value = 0.0;
    
        for(int g = 0; g < 10; g++){
            for (int i = 0; i < Simulation.size(); i++){
                //System.out.println(i + "\n " + Simulation.get(i).getGeneration());

                for (int j = i+1; j < Simulation.size(); j++){ //Try switching to a bubble sort method soon for performance
                    
                    ListSort tmp = new ListSort(null, null, null);
                    
                    if(Simulation.get(j).getValue() < Simulation.get(i).getValue()){ //Brute Force sorting
                        //Swapping
                        tmp = Simulation.get(i);
                        Simulation.set(i, Simulation.get(j));
                        Simulation.set(j, tmp);
                    }
                }
            }
            for (int i = 0; i < Simulation.size(); i++){
                System.out.println(Simulation.get(i).getValue());
                //System.out.println(Simulation.get(i).getGeneration());
            }
            for(int j = 19; j > 9; j-=2){ //Loops through 2 at a time for 2 Generation Genes, for the Top 10 of each Generation
                for(int k = 0; k < 4; k++){ //loops through 4 times to make 4 children
                    for(int i = 0; i < 10; i++){ //Each pairs of Parents will Create 4 Children
                        Random rand = new Random();
                        Chromosome = rand.nextInt(2);
                        if(Chromosome == 0){
                            //System.out.println(i + "\n " + Simulation.get(j).getGeneration().get(j));
                            
                            Parent.add(Simulation.get(j).getGeneration().get(i));
                            Value += Double.parseDouble(Simulation.get(j).getGeneration().get(i).get(1));
                            Weight+= Double.parseDouble(Simulation.get(j).getGeneration().get(i).get(2));
                            

                        } else{
                            
                            Parent.add(Simulation.get(j-1).getGeneration().get(i));
                            Value += Double.parseDouble(Simulation.get(j-1).getGeneration().get(i).get(1));
                            Weight+= Double.parseDouble(Simulation.get(j-1).getGeneration().get(i).get(2));
                            
                        }
                    
                    }
                    
                    if(Weight > 100){
                        Value = 0.0;
                    }
                    //System.out.println(Value);
                    // System.out.println(Value);
                    Values[((19 - j) / 2) * 4 + k] = Value; //Values Array set to current Value
                    Weights[((19 - j) / 2) * 4 + k] = Weight; //Value Weights set to current weight
                    
                    Value = 0.0;
                    Weight = 0.0;
                    Generation.add(new ArrayList<>(Parent));
                    Parent.clear();
                }
            }
            for(int i = 0; i < Generation.size(); i++){
                for (int j = 0; j < 10; j++){
                    //System.out.println(Simulation.get(i).getGeneration().get(0).get(j));
                    Simulation.get(i).getGeneration().set(j , Generation.get(i).get(j));
                    
                }
                Simulation.get(i).setValue(Values[i]);
                //System.out.println(i + ": " + Values[i]);
            }
            Generation.clear();
        }
    }
}