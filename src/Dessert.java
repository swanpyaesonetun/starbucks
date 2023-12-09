/**
 * Dessert.java
 *
 * @author Swan Pyae Sone Tun
 * CS 151
 * Dec 6, 2023
 */

class Dessert extends MenuItem {
	// Constructors
	public Dessert() {
		super();
	}
	
    public Dessert(String name, double price) {
        super(name, price);
    }
    
    public Dessert(Dessert d, int quantity) {
        super(d);
        this.quantity = quantity;
    }
    
    public Dessert(Dessert d) {
        super(d);
    }
    
    // Methods
    
    @Override
	public String getDescription() {
		String output = super.getDescription() + "\n";
		
		return output;
	}
    
    @Override
	public String getOrderSummary() {
		String output = super.getOrderSummary() + "\n";
		
		return output;
	}
}