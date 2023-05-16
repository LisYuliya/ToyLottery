public class Toy {
        private static int nextId = 1;
        private String name;
        private int quantity;
        private double weight;
        private int id;
    
        public Toy(String name, int quantity, double weight) {
            this.name = name;
            this.quantity = quantity;
            this.weight = weight;
            this.id = nextId++;
        }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity) {
        if (quantity > this.quantity) {
            throw new IllegalArgumentException("Not enough quantity");
        }
        this.quantity -= quantity;
    }

    public boolean canBeSelected() {
        return quantity > 0 && weight > 0;
    }
}