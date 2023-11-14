package petStoreSystem.utils;

import java.io.ObjectInputFilter.Status;
import java.util.InputMismatchException;
import java.util.Locale.Category;
import java.util.Map;
import java.util.Scanner;

import petStoreSystem.core.Pet;

//import javax.security.sasl.AuthenticationException;

import petStoreSystem.core.PetOrderMarkerInterface;
import petStoreSystem.customer.Order;
import petStoreSystem.exception.AuthenticationException;
import petStoreSystem.exception.AuthorizationException;
import petStoreSystem.exception.OutOfStockException;

public interface PetOrderValidationRules {
	static void adminUserLogin(Scanner sc) throws AuthorizationException, AuthenticationException {
		System.out.println("Login as Admin: \n" + "Enter Admin LoginId ");
		if (sc.next().equals("admin")) {
			System.out.println("Enter Admin password");
			if (sc.next().equals("admin")) {
				System.out.println("Successfully Logged in...");
			} else {
				throw new AuthenticationException("Wrong Password");
			}
		} else {
			throw new AuthorizationException("Restricted Access... Only Admin can Login");
		}

	}

	static void customerUserLogin(Scanner sc) throws AuthorizationException, AuthenticationException {
		System.out.println("Login as Customer \n" + "Enter LoginId");
		if (sc.next().equals("c1")) {
			System.out.println("Enter Password");
			if (sc.next().equals("c1")) {
				System.out.println("Successfully logged in as user");
			} else {
				throw new AuthenticationException("Wrong Password");
			}
		} else {
			throw new AuthorizationException("Wrong LoginId");
		}
	}

	static Map<String, PetOrderMarkerInterface> addPet(Map<String, PetOrderMarkerInterface> mapPets, Scanner sc)
			throws AuthorizationException, AuthenticationException, OutOfStockException, IllegalArgumentException,
			InputMismatchException {
		adminUserLogin(sc);
		System.out.println("Choose category : ");
		for (petStoreSystem.core.Category c : petStoreSystem.core.Category.values())
			System.out.println("category is " + c.name());
		petStoreSystem.core.Category validCategory = petStoreSystem.core.Category.valueOf(sc.next().toUpperCase());
		System.out.println("Enter pet name");
		String petName = sc.next();
		if (!mapPets.containsKey(petName)) {
			System.out.println("Enter pet quatity(stock),price");
			Pet newPet = new Pet(petName, sc.nextInt(), validCategory, sc.nextDouble());
			System.out.println("new pet Id is : " + newPet.getPetId());
			mapPets.put(petName, newPet);
			return mapPets;

		} else {
			throw new OutOfStockException("duplicate pet not allowed!!!");
		}

	}

	static void updatePetDetails(Map<String, PetOrderMarkerInterface> mapPets, Scanner sc)
			throws AuthorizationException, AuthenticationException, OutOfStockException {
		adminUserLogin(sc);
		System.out.println("Enter pet name");
		String petName = sc.next();
		if (mapPets.containsKey(petName)) {
			System.out.println("Enter quatity(stock) to added in stock ,new price");
			Pet p = ((Pet) mapPets.get(petName));
			p.setStock(p.getStock() + sc.nextInt());
			p.setPrice(sc.nextDouble());

		} else {
			throw new OutOfStockException("pet not found!!!");
		}

	}

	static void orderPet(Map<String, PetOrderMarkerInterface> mapPets, Scanner sc)
			throws OutOfStockException, IllegalArgumentException {
		System.out.println("Enter Pet name following : ");
		mapPets.values().stream().filter(p -> p instanceof Pet).map(p -> (Pet) p)
				.forEach(p -> System.out.println(" PetName = " + p.getPetName() + " ,Category: " + p.getCategory()
						+ " ,Stock =" + p.getStock()+" , Price: " + p.getPrice()));
		String petName = sc.next();
		if (mapPets.containsKey(petName)) {
			System.out.println("How many " + petName + " you want to buy?");
			Integer n = sc.nextInt();
			Pet p = (Pet) mapPets.get(petName);
			if (p.getStock() >= n) {
				Order o = new Order(petName, n);
				System.out.println("Order details " + o);
				p.setStock(p.getStock() - n);
				mapPets.put(o.getOrderId(), o);
			} else {
				throw new OutOfStockException("Pet Out of stock");

			}
		} else {
			throw new OutOfStockException("pet not available!!!");
		}
	}

	static void updateOrderStatus(Map<String, PetOrderMarkerInterface> mapPets, Scanner sc)
			throws AuthorizationException, AuthenticationException, IllegalArgumentException {
		adminUserLogin(sc);
		System.out.println("Enter Order ID");
		String i = sc.next();
		Order o = (Order) mapPets.get(i);
		System.out.println("current status of order is : " + o.getStatus());
		System.out.println("Enter Following updated Status");
		for (Status s : Status.values()) {
			System.out.println(s.name());
		}
		o.setStatus(petStoreSystem.customer.Status.valueOf(sc.next().toUpperCase()));
		System.out.println("new status is : " + o.getStatus());
		System.out.println("Successfully Updated");
	}
}
