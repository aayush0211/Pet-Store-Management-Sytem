package petStoreSystem.tester;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import petStoreSystem.core.Pet;
import petStoreSystem.core.PetOrderMarkerInterface;
import petStoreSystem.customer.Order;
import petStoreSystem.utils.PetOrderValidationRules;
import petStoreSystem.utils.PopulatedPets;

public class PetStoreApplication implements PetOrderValidationRules{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Scanner sc = new Scanner(System.in)){
			Map<String,PetOrderMarkerInterface>petMaps =new HashMap<>();
			PopulatedPets.readToText(petMaps);
			//petMaps = PopulatedPets.populatePetMaps(petMaps);
			boolean exit = false; 
			while(!exit) {
				System.out.println("Enter your choice: \n"
						+ "1)Login \n2)Add new Pet \n3)Update pet Deatails\n 4)Display all Pets \n 5)Order a pet\n"
						+ "6)Check order status by OrderId\n 7)Update Order Status \n8)Display all Orders\n 0)Exit");
				try {
					switch(sc.nextInt()) {
					case 1: System.out.println("Enter Login as a)Admin b)Customer");
					
					switch(sc.next()) {
					case "a":
						PetOrderValidationRules.adminUserLogin(sc);
						break ;
					case "b": 
						PetOrderValidationRules.customerUserLogin(sc);
						break;
					default: System.out.println("Invalid input");
						break;
					}
					break;
					case 2: PetOrderValidationRules.addPet(petMaps, sc);
						break;
					case 3:PetOrderValidationRules.updatePetDetails(petMaps, sc);
						break;
					case 4:System.out.println("All pets Details are");
					       petMaps.values().stream().forEach(p->{
					    	   if(p instanceof Pet) {
					    		   System.out.println(p);
					    	   }
					       });
						break;
					case 5:PetOrderValidationRules.orderPet(petMaps, sc);
						break;
					case 6:System.out.println("enter order id : ");
					   String orderId = sc.next();
						Order o = (Order) petMaps.get(orderId);
						System.out.println("Current status is : "+o.getStatus());
						break;
					case 7:
						PetOrderValidationRules.updateOrderStatus(petMaps, sc);
					case 8: System.out.println("All Order Details: ");
					petMaps.values().stream().filter(p->p instanceof Order).forEach(System.out::println);
					case 0: System.out.println("ThankYou!!!!");
						exit = true;
						break;
					}
				
				}catch(Exception e) {
					e.printStackTrace();
					sc.nextLine();
				}
			}
			PopulatedPets.writeToText(petMaps);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
