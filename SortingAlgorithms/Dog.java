public class Dog implements Comparable<Dog> {

    private String name;
    private double weight;

    public Dog(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setWeight(double w) {
        this.weight = w;
    }

    public String getName() {
        return this.name;
    }

    public double getWeight() {

        return this.weight;
    }

    public int compareTo(Dog otherDog){

        if (this.weight - otherDog.getWeight() < 0.0)
            return -1;
        else if (this.weight - otherDog.getWeight() > 0.0)
            return 1;
        return 0;
    }

    public String toString() {
        return name + ", weight: " + weight;
    }

}
