/**
 * Drink.java
 *
 * @author Swan Pyae Sone Tun
 * CS 151
 * Dec 6, 2023
 */

import java.util.ArrayList;
import java.util.List;

class Drink extends MenuItem {
    private String type; // e.g., hot or cold
    private List<Topping> toppings;
    private String sweetLevel;
    private String iceLevel;
    private String size;

    // Constructors
    public Drink() {
    	super();
    	this.type = "";
    	this.toppings = new ArrayList<>();
        this.sweetLevel = "";
        this.iceLevel = "";
    	this.size = "";
    }
    
    public Drink(String name, double price, String type) {
        super(name, price);
        this.quantity = 0;
        this.type = type;
        this.toppings = new ArrayList<>();
        this.sweetLevel = "";
        this.iceLevel = "";
    	this.size = "";
    }
    
    public Drink(Drink d) {
        super(d);
        this.type = d.type;
        
        this.toppings = new ArrayList<>();
        for (Topping t : d.toppings) {
            this.toppings.add(new Topping(t));
        }
        
        this.sweetLevel = d.sweetLevel;
        this.iceLevel = d.iceLevel;
        this.size = d.size;
    }
    
    // Methods

    public void addTopping(Topping t) {
    	toppings.add(t);
    }
    
    public void removeTopping(Topping t) {
    	toppings.remove(t);
    }
    
    public int getTopQuantity() {
    	int size = 0;
    	
    	for (Topping t : toppings) {
        	size += t.getQuantity();
        }
    	
    	return size;
    }
    
    @Override
    public String getDescription() {
    	String output = super.getDescription() + String.format("%s", "(" + type + ")\n");
        
    	return output;
    }
    
    @Override
	public String getOrderSummary() {
		String output = super.getOrderSummary() + "\n";
		output += String.format("%-3s Size: %s\n", "", this.size);
		output += String.format("%-3s Sweetness: %s\n", "", this.sweetLevel);

		if (this.type.equalsIgnoreCase("Cold")) {
			output += String.format("%-3s Ice Level: %s\n", "", this.iceLevel);
		}
		
    	if (!this.toppings.isEmpty()) {
    		output += String.format("%-3s %s", "", "Add-ons:");
    		
    		int count = 0;
            for (Topping t : toppings) {
            	if ((count%2) == 0) {
            		output += String.format("%s %-3s", "\n", "");
            	}
                output += t.name + " x" + t.quantity + ", ";
                count++;
            }
            
            output = output.substring(0, output.length() - 2);
            output += "\n";
    	}
		
		return output;
	}
    
    @Override
    public double getTotalPrice() {
    	double total = 0;
    	double size = 0;
    	
    	total = super.getTotalPrice();
    	
    	if (!this.toppings.isEmpty()) {
            for (Topping t : toppings) {
                total += t.getTotalPrice() * this.quantity;
            }
    	}
    	
		if (this.size.equalsIgnoreCase("xl")) {
			size = 1.5;
		} else if (this.size.equalsIgnoreCase("l")) {
			size = 1;
		} else if (this.size.equalsIgnoreCase("m")) {
			size = 0.5;
		} else if (this.size.equalsIgnoreCase("s")) {
			size = 0.25;
		}
		
		total += size * this.quantity;
    	
    	return total;
    }
    
    // Getters and Setters

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public List<Topping> getToppings() {
		return toppings;
	}

	public void setToppings(List<Topping> toppings) {
    	for (Topping t : toppings) {
            this.toppings.add(new Topping(t));
        }
	}
	
	public String getSweetLevel() {
		return sweetLevel;
	}

	public void setSweetLevel(int sweetLevel) {
		switch (sweetLevel) {
			case 1:
				this.sweetLevel = "No Sugar";
				break;
			case 2:
				this.sweetLevel = "Light Sugar";
				break;
			case 3:
				this.sweetLevel = "Half Sugar";
				break;
			case 4:
				this.sweetLevel = "Less Sugar";
				break;
			case 5:
				this.sweetLevel = "Normal Sugar";
				break;
		}
	}

	public String getIceLevel() {
		return iceLevel;
	}

	public void setIceLevel(int iceLevel) {
		switch (iceLevel) {
			case 1:
				this.iceLevel = "No Ice";
				break;
			case 2:
				this.iceLevel = "Less Ice";
				break;
			case 3:
				this.iceLevel = "Regular Ice";
				break;
			case 4:
				this.iceLevel = "More Ice";
				break;
			case 5:
				this.iceLevel = "Extra Ice";
				break;
		}
	}

	public String getSize() {
		return size;
	}
	
	public void setSize(int size) {
		switch (size) {
			case 1:
				this.size = "S";
				break;
			case 2:
				this.size = "M";
				break;
			case 3:
				this.size = "L";
				break;
			case 4:
				this.size = "XL";
				break;
			}
	}
}