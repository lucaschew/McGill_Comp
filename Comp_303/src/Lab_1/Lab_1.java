package Lab_1;

import java.util.ArrayList;

public class Lab_1 {
	
	public static ArrayList<Food> foodCart = new ArrayList<>();
	
	public static void main (String[] args) {
		
		System.out.println(foodCart);
		
		Food apple = new Food("Apple", "173691749", 31.25, FoodLabel.Vegan);
		
		storeFood(apple);
		storeFood(apple);
		
		System.out.println(foodCart);
		
	}
	
	public static void storeFood(Food food) {
		
		foodCart.add(food);
		
	}

}
