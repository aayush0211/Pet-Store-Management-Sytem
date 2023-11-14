package petStoreSystem.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Map;
import java.util.Scanner;

import petStoreSystem.core.Category;
import petStoreSystem.core.Pet;
import petStoreSystem.core.PetOrderMarkerInterface;
import petStoreSystem.customer.Order;
import petStoreSystem.customer.Status;

public class PopulatedPets {
	public static Map<String, PetOrderMarkerInterface> populatePetMaps(Map<String, PetOrderMarkerInterface> petMaps){
		
		petMaps.put("Kali", new Pet("Kali", 20, Category.CAT, 100.00));
		petMaps.put("BullDog", new Pet("BullDog", 10, Category.DOG, 2200.00));
		petMaps.put("Slow", new Pet("Slow", 5, Category.TURTLE, 50.00));
		petMaps.put("Fast", new Pet("Fast", 9, Category.RABBIT, 1500.50));
		petMaps.put("Jerry", new Pet("Jerry",15, Category.RAT, 750.00));
		petMaps.put("Rohu", new Pet("Rohu", 25, Category.FISH, 900.00));
		return petMaps;
	}
	
	public static void writeToText(Map<String , PetOrderMarkerInterface> petMaps) throws IOException {
		try(PrintWriter pw = new PrintWriter(new FileWriter("PetDetails.txt"));
				PrintWriter ow = new PrintWriter(new FileWriter("OrderDetails.txt"))){

			petMaps.values().stream().filter(p->p instanceof Pet).forEach(p->pw.println(((Pet)p).toPrint()));
			petMaps.values().stream().filter(p->p instanceof Order).forEach(p->ow.println(((Order)p).toPrint()));
			System.out.println("SUCCESFULLY Write");
		
		}
		
	}
	
	public static void readToText(Map<String , PetOrderMarkerInterface> petMaps) throws FileNotFoundException, IOException {
		try(BufferedReader pr = new BufferedReader(new FileReader("PetDetails.txt"));
				BufferedReader or = new BufferedReader(new FileReader("OrderDetails.txt"))){
		pr.lines().map(p->readData(p)).forEach(p->petMaps.put(((Pet)p).getPetName(), p));
		or.lines().map(p->readOrderData(p)).forEach(p->petMaps.put(((Order)p).getOrderId(), p));
			
		}
	}
	public static PetOrderMarkerInterface readData(String lines) {
		try(Scanner sc = new Scanner(lines)){
			sc.useDelimiter(",");
			Pet p = new Pet(sc.next(),Category.valueOf(sc.next().toUpperCase()),sc.nextInt(),sc.nextDouble());
			return p;
		}
	}
	
	public static PetOrderMarkerInterface readOrderData(String lines) {
		try(Scanner sc = new Scanner(lines)){
			sc.useDelimiter(",");
			Order o = new Order(sc.next(),sc.nextInt(),Status.valueOf(sc.next()));
			
			return o;
		}
	}
}
