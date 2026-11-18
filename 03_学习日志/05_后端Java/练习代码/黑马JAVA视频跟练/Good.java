public class Good {
    private int id;
    private String name;
    private double price;
    private int kucun;

    public Good(){

    }
    public Good(int id, String name, double price, int kucun){
        this.id = id;
        this.name = name;
        this.price = price;
        this.kucun = kucun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getKucun() {
        return kucun;
    }

    public void setKucun(int kucun) {
        this.kucun = kucun;
    }
}
