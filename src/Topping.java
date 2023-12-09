/**
 * Topping.java
 *
 * @author Swan Pyae Sone Tun
 * CS 151
 * Dec 6, 2023
 */

class Topping extends MenuItem {
	private String category; // e.g., coffee or drink
	
	// Constructors
	
	public Topping() {
		super();
		this.category = "";
	}
	
    public Topping(String name, double price, String category) {
        super(name, price);
        this.category = category;
    }
    
    public Topping(Topping t, int quantity) {
    	super(t);
    	this.category = t.category;
    	this.quantity = quantity;
    }
    
    public Topping(Topping t) {
        super(t);
        this.category = t.category;
    }
    
    // Methods
    
    @Override
	public String getDescription() {
		String output = super.getDescription() + String.format("%s", "(" + category + ")\n");
		
		return output;
	}
    
    @Override
	public String getOrderSummary() {
		String output = super.getOrderSummary() + "\n";
		
		return output;
	}
    
    // Getters and Setters
    
    public String getCategory() {
    	return this.category;
    }
    
	public void setCategory(String category) {
		this.category = category;
	}
}