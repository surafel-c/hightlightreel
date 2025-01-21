package property;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Admin")
class Admin {
	
	@Id
	int admin_id;
	String admin_name;
	String admin_pssword;
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_pssword() {
		return admin_pssword;
	}
	public void setAdmin_pssword(String admin_pssword) {
		this.admin_pssword = admin_pssword;
	}
	
	
    
	
	
	
	
}
