package javacode; 
public class Customer {
	private String name;
    private int id;

    public Customer(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString(){
        return id + " - " + name;
    }
}
