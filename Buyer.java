package com.surafel.realEstate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name ="Buyer")
public class Buyer {
	@Id
	int  buyer_id;
	String buyer_contact;
	String buyer_name;
	public int getBuyer_id() {

		return buyer_id;
	}
	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}
	public String getBuyer_contact() {
		return buyer_contact;
	}
	public void setBuyer_contact(String buyer_contact) {
		this.buyer_contact = buyer_contact;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	@Override
	public String toString() {
		return "Buyer [buyer_id=" + buyer_id + ", buyer_contact=" + buyer_contact + ", buyer_name=" + buyer_name + "]";
	}
	

}
