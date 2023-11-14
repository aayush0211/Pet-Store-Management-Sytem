package petStoreSystem.core;

import java.util.Objects;
import java.util.Locale.Category;

public class Pet implements PetOrderMarkerInterface{
	private static int cnt=0;
	private Integer petId;
	private String petName;
	private Integer stock;
	private petStoreSystem.core.Category category;
	private Double price;
	
	public Pet( String petName, Integer stock, petStoreSystem.core.Category validCategory, Double price) {
		super();
		this.petId = ++cnt;
		this.petName = petName;
		this.stock = stock;
		this.category = validCategory;
		this.price = price;
	}




	public Pet(String petName,petStoreSystem.core.Category category, Integer stock , Double price) {
		super();
		
		this.petName = petName;
	
		this.category = category;
		this.stock = stock;
		this.price = price;
	}




	public Integer getPetId() {
		return petId;
	}


	public void setPetId(Integer petId) {
		this.petId = petId;
	}


	public String getPetName() {
		return petName;
	}


	public void setPetName(String petName) {
		this.petName = petName;
	}
	public Pet(String name) {
		this.petName=name;
		
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}



	public petStoreSystem.core.Category getCategory() {
		return category;
	}




	public void setCategory(petStoreSystem.core.Category category) {
		this.category = category;
	}




	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		return Objects.hash(petName);
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Pet) {
			Pet p = (Pet)o;
			return petName.equals(p.petName);
		}
		return false;
		}
	



	@Override
	public String toString() {
		return "Pet [petId=" + petId + ", petName=" + petName + ", stock=" + stock + ", category=" + category
				+ ", price=" + price + "]";
	}
	
	public String toPrint() {
		return petName+","+category+","+stock+","+price+",";
	}
	 
}
