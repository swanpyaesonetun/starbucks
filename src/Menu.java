/**
 * Menu.java
 *
 * @author Swan Pyae Sone Tun
 * CS 151
 * Dec 6, 2023
 */

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private List<Coffee> coffees;
	private List<Drink> drinks;
	private List<Topping> toppings;
	private List<Dessert> desserts;

    public Menu() {
        this.coffees = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.toppings = new ArrayList<>();
        this.desserts = new ArrayList<>();
    }
    
    // Get menu size methods
    
    public int getCoffeeListSize() {
    	return coffees.size();
    }
    
    public int getDrinkListSize() {
    	return drinks.size();
    }
    
    public int getToppingListSize() {
    	return toppings.size();
    }
    
    public int getDessertListSize() {
    	return desserts.size();
    }
    
    // Get item at index methods
    
    public Coffee getCoffee(int index) {
    	return coffees.get(index);
    }
    
    public Drink getDrink(int index) {
    	return drinks.get(index);
    }
    
    public Topping getTopping(int index) {
    	return toppings.get(index);
    }
    
    public Dessert getDessert(int index) {
    	return desserts.get(index);
    }

    // Add menu methods
    
	public void addMenu(Coffee coffee) {
		coffees.add(coffee);
	}

	public void addMenu(Drink drink) {
		drinks.add(drink);
	}

	public void addMenu(Topping topping) {
		toppings.add(topping);
	}

	public void addMenu(Dessert dessert) {
		desserts.add(dessert);
	}

	// Remove menu methods
	
    public boolean removeMenu(Coffee coffee) {
        return coffees.remove(coffee);
    }

    public boolean removeMenu(Drink drink) {
        return drinks.remove(drink);
    }

    public boolean removeMenu(Topping topping) {
        return toppings.remove(topping);
    }

    public boolean removeMenu(Dessert dessert) {
        return desserts.remove(dessert);
    }
    
    // Display menu methods
    
    public void displayCoffee() {
    	int i = 1;
    	
    	System.out.println("\nCoffees");
    	for (Coffee coffee : coffees) {
    		if (i < 10) {
    			System.out.print(i + ".  " + coffee.getDescription());
    		} else {
    			System.out.print(i + ". " + coffee.getDescription());
    		}
    		i++;
    	}
    	
    	System.out.println("\n0.  Back");
    }
    
    public void displayDrink() {
    	int i = 1;
    	
    	System.out.println("\nDrinks");
    	for (Drink drink : drinks) {
    		if (i < 10) {
    			System.out.print(i + ".  " + drink.getDescription());
    		} else {
    			System.out.print(i + ". " + drink.getDescription());
    		}
    		i++;
    	}

    	System.out.println("\n0.  Back");
    }
    
    public void displayDessert() {
    	int i = 1;
    	
    	System.out.println("\nDesserts");
    	for (Dessert dessert : desserts) {
    		if (i < 10) {
    			System.out.print(i + ".  " + dessert.getDescription());
    		} else {
    			System.out.print(i + ". " + dessert.getDescription());
    		}
    		i++;
    	}

    	System.out.println("\n0.  Back");
    }
    
    public void displayToppings() {
    	int i = 1;
    	
    	System.out.println("\nToppings");
		for (Topping topping : toppings) {
			if (i < 10) {
				System.out.print(i + ".  " + topping.getDescription());
			} else {
				System.out.print(i + ". " + topping.getDescription());
			}
			i++;
		}
		
    	System.out.println("\n0.  Back");
    }
}