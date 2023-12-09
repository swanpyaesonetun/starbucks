# Coffee Ordering App
This project is a Java-based application designed to simulate a café ordering system. It encompasses various classes that represent different aspects of a café menu, including drinks (like coffee and other beverages), desserts, toppings, and a system to handle customer orders. The application is structured in an object-oriented manner, with a clear division of responsibilities among different classes. This structure allows for easy management and modification of the menu items and orders.
# Features
+ Menu management (add, remove, display items)
+ Order processing
+ Support for various menu items (Coffee, Dessert, Drink, Topping)
+ Interactive user interface
# Instructions for the Program
1. Starting the Program:
   + Locate the 'UserInterface.java' file and run it.
2. Navigating the Main Menu:
   + Upon starting, select an option from the main menu to begin your order.
3. Back Navigation
   + Use the '0' key as a universal 'back' button. This will return you to the main menu at any stage, except during 'yes/no' (y/n) decision prompts.
4. Adding Toppings
   + When prompted for 'Add topping (y/n)', entering '0' will take you back to the Topping menu.
   + You can add up to 5 toppings to your drink or coffee. Each topping falls into one of three categories: specific to drinks, specific to coffees, or available for both. Note that drink-specific toppings cannot be added to coffee and vice versa.
5. Ordering Coffee and Drinks:
   + Choose between cold and hot for both coffee and drinks.
   + For cold drinks, you'll be prompted to select an ice level.
   + Coffees include an option for adding up to 6 coffee shots.
   + For drinks, you will choose a sweetness level.
6. Completing Your Order:
   + After selecting your preferences, you'll have the option to add the item to your cart.
7. Managing Your Cart:
   + If your cart isn't empty, you'll see 'Cart' and 'Checkout' options in the main menu.
   + In the 'Cart', you can review your order and remove items if needed.
   + 'Checkout' leads to the payment process.
