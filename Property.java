package property;

 





import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity

@Table(name="property")
 public class Property {
	
	@Id
	int property_id;
	String property_type;
	String property_location;
	double property_price;
	String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getProperty_id() {
		return property_id;
	}
	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	}
	public String getProperty_type() {
		return property_type;
	}
	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}
	public String getProperty_location() {
		return property_location;
	}
	public void setProperty_location(String property_location) {
		this.property_location = property_location;
	}
	

		@Lob
		@Column(name = "property_image") 
		private byte[] imageData; 

		// getters and setters 
	

	// ... 

	public byte[] getImageData() {
			return imageData;
		}
	
	
		
	
		
		
		


	
	public double getProperty_price() {
		return property_price;
	}
	public void setProperty_price(double property_price) {
		this.property_price = property_price;
	}
	@Override
	public String toString() {
		return "Property [property_id=" + property_id + ", property_type=" + property_type + ", property_location="
				+ property_location + ", property_price=" + property_price + ", description=" + description + "]";
	}
	
	

}
