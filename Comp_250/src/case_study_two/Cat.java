package case_study_two;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cat implements Comparable{
	private String name;
	private int age;
	
	public Cat() {
		this.name = "Grumpy";
		this.age = 7;
	}
	
	public Cat(String n) {
		this.name = n;
		this.age = 0;
	}
	
	public Cat(String n, int a) {
		this.name = n;
		this.age = a;
	}
	
	/*
	 * ADD CODE HERE
	 */

	public static void sortCats(List<Cat> cats) {
		
		for (int i = 0; i < cats.size()-1; i++) {
			
			for (int z = 0; z < cats.size()-1-i; z++) {
				
				if (cats.get(z).compareTo(cats.get(z+1)) > 0) {
					
					Cat temp = cats.get(z);
					cats.remove(z);
					cats.add(z+1, temp);
				}
				
			}
			
		}
		
	}
	
	//Runs in O(n), n being the largest array size and considering that cats1.size == cats2.size
	public static ArrayList<Cat> getCommonCats(List<Cat> cats1, List<Cat> cats2){
		
		ArrayList<Cat> result = new ArrayList<>();
		
		for (int i = 0; i < cats1.size(); i++) {
			
			if (cats2.contains(cats1.get(i))) {
				result.add(cats1.get(i));
			}
			
		}
		
		return result;
		
	}
	
	public String toString() {
		return "(" + this.name + ", " + this.age + ")";
	}
	
	@Override
	public int compareTo(Object o) {
		
		if (o instanceof Cat) {
			
			Cat temp = (Cat) o;
			
			if (this.age > temp.age) {
				return 1;
			} else if (this.age < temp.age){
				return -1;
			} else {
				return this.name.compareTo(temp.name);
			}
			
		}
		
		throw new Error("CompareTo has been given an non-Cat object");
		
	}
	
	public boolean equals(Object o) {
		if (o instanceof Cat) {
			return ((Cat) o).compareTo(this)==0;
		}
		return false;
	}
	
	public static void main(String[] args) {
		ArrayList<Cat> myCats = new ArrayList<Cat>();
		myCats.add(new Cat());
		myCats.add(new Cat("Tiger"));
		myCats.add(new Cat("Spritz", 12));
		myCats.add(new Cat("Kitty", 2));
		myCats.add(new Cat("Ginger", 2));
		sortCats(myCats);
		System.out.println(myCats);
		
		
		ArrayList<Cat> otherCats = new ArrayList<Cat>();
		otherCats.add(new Cat());
		otherCats.add(new Cat("Tiger", 8));
		otherCats.add(new Cat("Spritz", 12));
		otherCats.add(new Cat("Kitty", 1));
		otherCats.add(new Cat("Grey", 2));
		otherCats.add(new Cat("Fluffy", 11));
		sortCats(otherCats);
		System.out.println(otherCats);
		
		ArrayList<Cat> common = getCommonCats(myCats, otherCats);
		System.out.println(common);
		
	}


}
