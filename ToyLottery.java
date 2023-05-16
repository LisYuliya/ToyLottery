import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyLottery {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    public ToyLottery() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public void addPrizeToy(Toy toy) {
        prizeToys.add(toy);
    }

    public Toy selectPrizeToy() {
        if (prizeToys.isEmpty()) {
            throw new IllegalStateException("Нет доступных призовых игрушек.");
        }
    
        double totalWeight = 0;
        for (Toy toy : prizeToys) {
            totalWeight += toy.getWeight();
        }
    
        Random random = new Random();
        double randomNumber = random.nextDouble() * totalWeight;
    
        double weightSum = 0;
        int index = 0;
        for (Toy toy : prizeToys) {
            weightSum += toy.getWeight();
            if (randomNumber <= weightSum) {
                break;
            }
            index++;
        }
    
        Toy selectedToy = prizeToys.get(index);
        selectedToy.removeQuantity(1);
    
        if (selectedToy.getQuantity() == 0) {
            prizeToys.remove(index);
        }
    
        toys.add(selectedToy);
        updateAndSaveToys(prizeToys, toys);
        return selectedToy;
    }

    private void updateAndSaveToys(List<Toy> prizeToys, List<Toy> selectedToys) {
        try {
            FileWriter prizeWriter = new FileWriter("prize_toys.txt");
            FileWriter toysWriter = new FileWriter("won_toys.txt");

            for (Toy toy : prizeToys) {
                if (toy.getQuantity() > 0) {
                    prizeWriter.write(
                            toy.getId() + "," + toy.getName() + "," + toy.getQuantity() + "," + toy.getWeight() + "\n");
                }
            }
            prizeWriter.close();

            for (Toy toy : selectedToys) {
                toysWriter.write(
                        toy.getName() + "\n");
            }
            toysWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Toy> getToys() {
        return toys;
    }

    public List<Toy> getPrizeToys() {
        return prizeToys;
    }

    public void updatePrizeToyWeight(int toyId, double newWeight) {
        for (Toy toy : prizeToys) {
            if (toy.getId() == toyId) {
                toy.setWeight(newWeight);
                return;
            }
        }
        throw new IllegalArgumentException("Toy with id " + toyId + " not found");
    }
}
