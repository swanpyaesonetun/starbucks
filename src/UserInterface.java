/**
 * UserInterface.java
 *
 * @author Swan Pyae Sone Tun
 * CS 151
 * Dec 6, 2023
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
	public static Menu menu;
	public static Order order;
	public static Coffee currCoffee;
	public static Dessert currDessert;
	public static Drink currDrink;
	public static final int MAX_TOP_SIZE = 5;
	public static final int MAX_COFFEE_SHOT = 6;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		try {
			populateMenu();
			System.out.print("Welcome to Starbucks!\n");

			boolean exit = false;
			while (!exit) {
				exit = menuPrompt(input);
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}

		return;
	}

	public static void populateMenu() {
		menu = new Menu();

		// Make coffees
		Coffee americano = new Coffee("Caffe Americano", 7, "Hot");
		Coffee cappuccino = new Coffee("Cappuccino", 6.5, "Hot");
		Coffee espresso = new Coffee("Espresso", 5.5, "Hot");
		Coffee latte = new Coffee("Latte", 6, "Hot");
		Coffee flatWhite = new Coffee("Flat White", 6, "Hot");
		Coffee mocha = new Coffee("Peppermint Mocha", 8, "Hot");
		Coffee icedAmericano = new Coffee("Iced Caffe Americano", 7, "Cold");
		Coffee icedCoffee = new Coffee("Iced Coffee", 5, "Cold");
		Coffee coldBrew = new Coffee("Starbucks Cold Brew Coffee", 8, "Cold");
		// Add coffees
		menu.addMenu(americano);
		menu.addMenu(cappuccino);
		menu.addMenu(espresso);
		menu.addMenu(latte);
		menu.addMenu(flatWhite);
		menu.addMenu(mocha);
		menu.addMenu(icedAmericano);
		menu.addMenu(icedCoffee);
		menu.addMenu(coldBrew);

		// Make drinks
		Drink strawberry = new Drink("Strawberry Lemonade", 7, "Cold");
		Drink pineapple = new Drink("Pineapple Passionfruit Lemonade", 7, "Cold");
		Drink mango = new Drink("Mango Dragonfruit Lemonade", 6.5, "Cold");
		Drink hotChocolate = new Drink("Peppermint Hot Chocolate", 8.5, "Hot");
		Drink whiteHotChocolate = new Drink("Peppermint White Hot Chocolate", 8, "Hot");
		Drink steamedApple = new Drink("Steamed Apple Juice", 7.5, "Hot");
		// Add drinks
		menu.addMenu(strawberry);
		menu.addMenu(pineapple);
		menu.addMenu(mango);
		menu.addMenu(hotChocolate);
		menu.addMenu(whiteHotChocolate);
		menu.addMenu(steamedApple);

		// Make desserts
		Dessert tiramisu = new Dessert("Tiramisu", 8);
		Dessert cheeseCake = new Dessert("Cheesecake", 7);
		Dessert cinnamonRoll = new Dessert("Cinnamon Roll", 2.5);
		Dessert donut = new Dessert("Donut", 5);
		Dessert chocolateCake = new Dessert("Chocolate Cake", 6.5);
		Dessert strawberryCake = new Dessert("Strawberry Cake", 6);
		// Add desserts
		menu.addMenu(tiramisu);
		menu.addMenu(cheeseCake);
		menu.addMenu(cinnamonRoll);
		menu.addMenu(donut);
		menu.addMenu(chocolateCake);
		menu.addMenu(strawberryCake);

		// Make toppings
		Topping whippedCream = new Topping("Whipped Cream", 0.5, "Both");
		Topping milk = new Topping("Milk", 0.5, "Coffee");
		Topping sweetCream = new Topping("Sweet Cream Foam", 0.5, "Coffee");
		Topping boba = new Topping("Honey Boba", 0.5, "Drink");
		Topping strawberryBoba = new Topping("Strawberry Boba", 0.5, "Drink");
		// Add toppings
		menu.addMenu(whippedCream);
		menu.addMenu(milk);
		menu.addMenu(sweetCream);
		menu.addMenu(boba);
		menu.addMenu(strawberryBoba);
	}

	public static boolean menuPrompt(Scanner input) {
		int userInput = -1, menuSize = 0;
		order = new Order();

		do {
			if (order.isEmpty()) {
				System.out.println("\n1.  Coffee\n2.  Drinks\n3.  Desserts\n0.  Exit");
			} else {
				System.out.println("\n1.  Coffee\n2.  Drinks\n3.  Desserts\n4.  Cart\n5.  Checkout\n0.  Exit");
			}

			System.out.print("\nInput: ");

			try {
				userInput = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid input. Please enter a number.");
				input.next();
				continue;
			}

			switch (userInput) {
			case 0:
				System.out.println("\nThank you for visiting!");
				return true;
			case 1:
				menu.displayCoffee();
				menuSize = menu.getCoffeeListSize();
				break;
			case 2:
				menu.displayDrink();
				menuSize = menu.getDrinkListSize();
				break;
			case 3:
				menu.displayDessert();
				menuSize = menu.getDessertListSize();
				break;
			case 4:
				if (!order.isEmpty()) {
					order.getOrderCart();
					
					int itemNum = removerOrder(input);
					if (itemNum != 0) {
						String name = order.getItem(itemNum - 1).getName();
						order.removeItem(itemNum - 1);
						System.out.println(name + " was removed.");
					}
					continue;
				} else {
					System.out.println("\nInvalid input. Please try again.");
					continue;
				}
			case 5:
				if (!order.isEmpty()) {
					order.getOrderSummary();

					double total = order.calculateTotalCost();
					double change = processPayment(input, total);

					if (change == 0) {
						continue;
					}

					order.clear();
					return true;
				} else {
					System.out.println("\nInvalid input. Please try again.");
					continue;
				}
			default:
				System.out.println("\nInvalid input. Please try again.");
				continue;
			}

			itemPrompt(input, userInput, menuSize);
		} while (true);
	}
		
	public static void itemPrompt(Scanner input, int userChoice, int menuSize) {
		int itemChoice = -1, qty, size, ice, sweet, shot;
		boolean exit = false;
		String orderType;

		do {
			System.out.print("\nItem Number: ");
			try {
				itemChoice = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid input. Please enter a number.");
				input.next();
				continue;
			}

			if (itemChoice > menuSize) {
				System.out.println("\nInvalid input. Please enter a number on the menu.");
			} else {
				if (itemChoice == 0) {
					return;
				} else {
					if (userChoice == 1) {
						orderType = "Coffee";
						currCoffee = new Coffee(menu.getCoffee(itemChoice - 1));

						List<Topping> tops = toppingPrompt(input, orderType, currCoffee.getTopQuantity());
						currCoffee.setToppings(tops);

						if (currCoffee.getType().equals("Cold")) {
							ice = icePrompt(input, orderType);
							if (ice == 0) {
								break;
							} else {
								currCoffee.setIceLevel(ice);
							}
						}

						shot = coffeeShotPrompt(input, orderType);
						if (shot == 0) {
							break;
						} else {
							currCoffee.setCoffeeShot(shot);
						}

						qty = quantityPrompt(input, orderType);
						if (qty == 0) {
							break;
						} else {
							currCoffee.setQuantity(qty);
						}

						size = sizePrompt(input, orderType);
						if (size == 0) {
							break;
						} else {
							currCoffee.setSize(size);
						}

						System.out.println("\nOrder:\n" + currCoffee.getSummary());
					} else if (userChoice == 2) {
						orderType = "Drink";
						currDrink = new Drink(menu.getDrink(itemChoice - 1));

						List<Topping> tops = toppingPrompt(input, orderType, currDrink.getTopQuantity());
						currDrink.setToppings(tops);

						if (currDrink.getType().equals("Cold")) {
							ice = icePrompt(input, orderType);
							if (ice == 0) {
								break;
							} else {
								currDrink.setIceLevel(ice);
							}
						}

						sweet = sweetPrompt(input, orderType);
						if (sweet == 0) {
							break;
						} else {
							currDrink.setSweetLevel(sweet);
						}

						qty = quantityPrompt(input, orderType);
						if (qty == 0) {
							break;
						} else {
							currDrink.setQuantity(qty);
						}

						size = sizePrompt(input, orderType);
						if (size == 0) {
							break;
						} else {
							currDrink.setSize(size);
						}

						System.out.println("\nOrder:\n" + currDrink.getSummary());
					} else {
						orderType = "Dessert";

						currDessert = new Dessert(menu.getDessert(itemChoice - 1));
						qty = quantityPrompt(input, orderType);
						if (qty == 0) {
							break;
						}

						currDessert.setQuantity(qty);

						System.out.println("\nOrder:\n" + currDessert.getSummary());
					}

					String userInput;
					do {
						System.out.print("Would you like to add this order to cart? (y/n): ");
						try {
							userInput = input.next();
							if (userInput.equalsIgnoreCase("n")) {
								return;
							} else if (userInput.equalsIgnoreCase("y")) {
								switch (userChoice) {
								case 1:
									order.addItem(currCoffee);
									System.out.println("\n(" + qty + ") " + menu.getCoffee(itemChoice - 1).getName()
											+ " has been added to cart!");

									return;
								case 2:
									order.addItem(currDrink);
									System.out.println("\n(" + qty + ") " + menu.getDrink(itemChoice - 1).getName()
											+ " has been added to cart!");

									return;
								case 3:
									order.addItem(currDessert);
									System.out.println("\n(" + qty + ") " + menu.getDessert(itemChoice - 1).getName()
											+ " has been added to cart!");

									return;
								}
							} else {
								System.out.println("\nInvalid input. Please enter y or n.");
							}
						} catch (InputMismatchException e) {
							System.out.println("\nInvalid input. Please enter y or n.");
							input.next();
							continue;
						}
					} while (true);
				}
			}
		} while (itemChoice > menuSize || !exit);

		return;
	}

	public static List<Topping> toppingPrompt(Scanner input, String orderType, int topQty) {
		String userInput;
		int itemChoice, qty;

		List<Topping> orderTops = new ArrayList<>();

		do {
			if (topQty >= MAX_TOP_SIZE) {
				System.out.println("You have " + (MAX_TOP_SIZE - topQty) + " toppings left for this order.");
				return orderTops;
			}

			System.out.print("\nAdd toppings (y/n): ");

			try {
				userInput = input.next();
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid input. Please enter y or n.");
				input.next();
				continue;
			}

			if (userInput.equalsIgnoreCase("y")) {

				do {
					menu.displayToppings();
					System.out.print("\nItem Number: ");

					try {
						itemChoice = input.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nInvalid input. Please enter a number.");
						input.next();
						continue;
					}

					if (itemChoice == 0) {
						break;
					} else if (itemChoice > menu.getToppingListSize()) {
						System.out.println("\nInvalid input. Please enter a number on the menu.");
						continue;
					} else if (menu.getTopping(itemChoice - 1).getCategory() != orderType
							&& menu.getTopping(itemChoice - 1).getCategory() != "Both") {

						System.out.println("\n" + menu.getTopping(itemChoice - 1).getName() + " is not suitable for "
								+ orderType + ".");
						System.out.println("Please choose a different topping.");
						continue;
					} else {
						qty = quantityPrompt(input, "Topping", topQty);
						if (qty == 0) {
							break;
						} else {
							topQty += qty;
						}

						boolean found = false;
						for (int i = 0; i < orderTops.size(); i++) {
							if (orderTops.get(i).getName() == menu.getTopping(itemChoice - 1).getName()) {
								qty += orderTops.get(i).getQuantity();
								orderTops.get(i).setQuantity(qty);
								found = true;
							}
						}

						if (!found) {
							Topping currTop = new Topping(menu.getTopping(itemChoice - 1), qty);
							orderTops.add(currTop);
						}

						System.out.println("\n" + menu.getTopping(itemChoice - 1).getName() + " has been added!");

						if (topQty < MAX_TOP_SIZE) {
							System.out.print("You have " + (MAX_TOP_SIZE - topQty)
									+ " toppings left for this order.\n\nWould you like to add more?(y/n): ");

							do {
								try {
									userInput = input.next();
									if (userInput.equalsIgnoreCase("n")) {
										return orderTops;
									} else if (userInput.equalsIgnoreCase("y")) {
										break;
									} else {
										System.out.print(
												"\nInvalid input. Please enter y or n.\nWould you like to add more?(y/n): ");
									}
								} catch (InputMismatchException e) {
									System.out.print(
											"\nInvalid input. Please enter y or n.\nWould you like to add more?(y/n): ");
									input.next();
								}
							} while (true);
						}
					}
				} while (topQty < MAX_TOP_SIZE);

			} else if (userInput.equalsIgnoreCase("n")) {
				return orderTops;
			} else {
				System.out.println("\nInvalid input. Please try again.");
			}
		} while (true);
	}

	public static int quantityPrompt(Scanner input, String orderType, int topQty) {
		int quantity = 0;
		do {
			System.out.print("\n" + orderType + " Quantity(0. Back): ");
			try {
				quantity = input.nextInt();
				if (quantity > (MAX_TOP_SIZE - topQty)) {
					System.out.println("\nYou have " + (MAX_TOP_SIZE - topQty) + " toppings left for this order.");
				} else {
					return quantity;
				}

			} catch (InputMismatchException e) {
				System.out.println("\nInvalid input. Please enter a number.");
				input.next();
			}
		} while (true);
	}

	public static int quantityPrompt(Scanner input, String orderType) {
		int quantity = 0;
		do {
			System.out.print("\n" + orderType + " Quantity(0. Back): ");
			try {
				quantity = input.nextInt();
				if (quantity < 0 || quantity > 50) {
					System.out.println("\nInvalid input. Please enter a number between 1-50.");
				} else {
					return quantity;
				}

			} catch (InputMismatchException e) {
				System.out.println("\nInvalid input. Please enter a number.");
				input.next();
			}
		} while (true);
	}

	public static int sizePrompt(Scanner input, String orderType) {
		int size;

		do {
			System.out.printf("\n" + orderType + " Sizes:\n");
			System.out.printf("%-15s - $%.2f\n", "1. Small", 0.25);
			System.out.printf("%-15s - $%.2f\n", "2. Medium", 0.5);
			System.out.printf("%-15s - $%.2f\n", "3. Large", 1.0);
			System.out.printf("%-15s - $%.2f\n", "4. Extra Large", 1.25);
			
			System.out.printf("\n0. Back\n");
			
			System.out.print("\nSize: ");
			try {
				size = input.nextInt();
				if (size < 0 || size > 4) {
					System.out.println("\nInvalid input. Please enter a number between 0-4.");
				} else {
					return size;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid input. Please enter a number.");
				input.next();
			}
		} while (true);
	}

	public static int icePrompt(Scanner input, String orderType) {
		int iceLevel = 0;
		do {
			System.out.println("\n" + orderType
					+ " Ice Level:\n1. No Ice\n2. Less Ice\n3. Regular Ice\n4. More Ice\n5. Extra Ice\n\n0. Back");
			System.out.print("\nChoice: ");
			try {
				iceLevel = input.nextInt();
				if (iceLevel < 0 || iceLevel > 5) {
					System.out.println("\nInvalid input. Please enter a number between 0-5.");
				} else {
					return iceLevel;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid input. Please enter a number.");
				input.next();
			}
		} while (true);
	}

	public static int sweetPrompt(Scanner input, String orderType) {
		int sweetness = 0;
		do {
			System.out.println("\n" + orderType
					+ " Sweetness Level:\n1. No Sugar\n2. Light Sugar\n3. Half Sugar\n4. Less Sugar\n5. Normal Sugar\n\n0. Back");
			System.out.print("\nChoice: ");

			try {
				sweetness = input.nextInt();
				if (sweetness < 0 || sweetness > 5) {
					System.out.println("\nInvalid input. Please enter a number between 0-5.");
				} else {
					return sweetness;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid input. Please enter a number.");
				input.next();
			}
		} while (true);
	}

	public static int coffeeShotPrompt(Scanner input, String orderType) {
		int shot = 0;
		do {
			System.out.print("\n" + orderType + " Shots(0. Back): ");

			try {
				shot = input.nextInt();
				if (shot < 0) {
					System.out.println("\nInvalid input. Please enter a number between 0-3.");
				} else if (shot > MAX_COFFEE_SHOT) {
					System.out.println("\nMore than " + MAX_COFFEE_SHOT + " shots is not healthy. Please choose a smaller amount.");
				} else {
					return shot;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid input. Please enter a number.");
				input.next();
			}
		} while (true);
	}

	public static double processPayment(Scanner input, double totalCost) {
		double amountEntered;
		double change = 0;

		try {
			while (true) {
				System.out.printf("%-50s $ ", "Enter your payment");
				amountEntered = input.nextDouble();

				if (amountEntered == 0) {
					return amountEntered;
				} else if (amountEntered < totalCost) {
					System.out.println("Insufficient amount.\n");
				} else {
					change = amountEntered - totalCost;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Invalid input. Please enter a valid amount.\n");
			input.next();
			return processPayment(input, totalCost);
		}

		System.out.printf("\n%-50s $%5.2f\n\nThank you for visiting!", "Payment accepted. Your change is", change);
		return amountEntered;
	}

	public static int removerOrder(Scanner input) {
		int itemNum = 0;
		do {
			System.out.print("Remove Item(0. Back): ");
			try {
				itemNum = input.nextInt();
				if (itemNum >= 0 && itemNum <= order.getSize()) {
					return itemNum;
				} else {
					System.out.println("\nInvalid input. Please enter a number between 0-" + order.getSize() + ".");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid input. Please enter a number.");
				input.next();
			}
		} while (true);
	}
}