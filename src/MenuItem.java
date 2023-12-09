/**
 * MenuItem.java
 *
 * @author Swan Pyae Sone Tun
 * CS 151
 * Dec 6, 2023
 */

abstract class MenuItem {
    protected String name;
    protected double price;
    protected int quantity;
    
    // Constructors
    
    public MenuItem() {
    	this.name = "";
        this.price = 0.0;
        this.quantity = 0;
    }
    
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }
    
    public MenuItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public MenuItem(MenuItem m) {
    	this.name = m.name;
    	this.price = m.price;
    	this.quantity = m.quantity;
    }
    
    // Method
    
	public String getDescription() {
		String output = String.format("%-35s %-5s $%-5.2f", name, "-", price);
		
		return output;
	}
	
	public String getOrderSummary() {
		String output = String.format("%-40s %-5s $%5.2f", name, "(" + quantity + ")", getTotalPrice());
		
		return output;
	}
	
	public String getSummary() {
		String output = String.format("%-5s - $%.2f %s", name + " (" + quantity + ")", getTotalPrice(), "\n");
		
		return output;
	}
	
	public double getTotalPrice() {
		return price * quantity;
	}
	
	// Getters and Setters
	
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
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}