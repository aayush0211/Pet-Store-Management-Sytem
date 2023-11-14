package petStoreSystem.customer;

import java.io.ObjectInputFilter.Status;

import petStoreSystem.core.Pet;
import petStoreSystem.core.PetOrderMarkerInterface;

public class Order implements PetOrderMarkerInterface{
        private static int cnt=100;
        private String orderId;
        private Integer quatitiy;
        private petStoreSystem.customer.Status status;
        private String petName;
      
		public Order(String petName, Integer quatitiy) {
			super();
			
			this.orderId = String.valueOf(++cnt);
			this.quatitiy = quatitiy;
			this.status = petStoreSystem.customer.Status.PLACED;
			this.petName = petName;
		}
		public Order(String petName, Integer quantity, petStoreSystem.customer.Status status) {

			this.petName = petName;
			this.quatitiy = quantity;
			this.status = status;
		}
		
		public String getOrderId() {
			return orderId;
		}

		

		public Integer getQuatitiy() {
			return quatitiy;
		}

		public void setQuatitiy(Integer quatitiy) {
			this.quatitiy = quatitiy;
		}

		

		public petStoreSystem.customer.Status getStatus() {
			return status;
		}

		public void setStatus(petStoreSystem.customer.Status status) {
			this.status = status;
		}

		public String getPetName() {
			return petName;
		}

		

		@Override
		public String toString() {
			return "Order [orderId=" + orderId + ", quatitiy=" + quatitiy + ", status=" + status + ", petName=" + petName
					+ "]";
		}
        
        public String toPrint() {
        	return petName+","+quatitiy+","+status+",";
        	
        }
        
        
}
