/**
 * Order.java
 *
 * @author Swan Pyae Sone Tun
 * CS 151
 * Dec 6, 2023
 */

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<MenuItem> orderItems;

    // Constructor
    
    public Order() {
        this.orderItems = new ArrayList<>();
    }
    
    // Methods
    
    public void clear() {
    	orderItems.clear();
    }
    
    public boolean isEmpty() {
    	return orderItems.size() == 0;
    }
    
    public int getSize() {
    	return orderItems.size();
    }

	public void addItem(MenuItem item) {
		orderItems.add(item);
	}

	public MenuItem getItem(int itemNum) {
		return orderItems.get(itemNum);
	}

	public void removeItem(int itemNum) {
		orderItems.remove(itemNum);
	}

    public double calculateTotalCost() {
        double total = 0;
        for (MenuItem item : orderItems) {
            total += item.getTotalPrice();
        }
        return total;
    }
    
    public void getOrderCart() {
    	int i = 1;
    	
    	System.out.println("\nCart:");
    	for (MenuItem item : orderItems) {
    		if (i < 10) {
    			System.out.println(i + ".  " + item.getSummary());
    		} else {
    			System.out.println(i + ". " + item.getSummary());
    		}
    		
    		i++;
    	}
    }
    
    public void getOrderSummary() {
    	int i = 1;
    	
    	System.out.println("\nOrder Summary:");
    	System.out.printf("%-3s %-40s %-5s %s", "No.", "Name", "Qty", "Price");
    	System.out.println("\n");
    	for (MenuItem item : orderItems) {
    		if (i < 10) {
    			System.out.println(i + ".  " + item.getOrderSummary());
    		} else {
    			System.out.println(i + ". " + item.getOrderSummary());
    		}
    		
    		i++;
    	}
    	
    	double total = calculateTotalCost();
    	System.out.println("----------------------------------------------------------");
    	System.out.printf("%-50s $%5.2f %s", "Total Price", total, "\n");
    	System.out.println("----------------------------------------------------------");
    }
}