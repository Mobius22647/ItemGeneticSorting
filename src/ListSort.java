import java.util.ArrayList;
import java.util.List;

public class ListSort {
    private ArrayList<List<List<String>>> generation;
    private Double Value;
    private Double Weight;

    // Constructor
    public ListSort(ArrayList<List<List<String>>> generation, Double Value, Double Weight) {
        this.generation = generation;
        this.Value = Value;
        this.Weight = Weight;
    }

    // Getter and Setter for generation
    public ArrayList<List<List<String>>> getGeneration() {
        return generation;
    }

    public void setGeneration(ArrayList<List<List<String>>> generation) {
        this.generation = generation;
    }

    // Getter and Setter for Value
    public Double getValue() {
        return Value;
    }

    public void setValue(Double value) {
        this.Value = value;
    }

    // Getter and Setter for Weight
    public Double getWeight() {
        return Weight;
    }

    public void setWeight(Double weight) {
        this.Weight = weight;
    }

}