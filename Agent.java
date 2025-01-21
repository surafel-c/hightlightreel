package com.surafel.realEstate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Agent")
public class Agent {
	@Id
	private int agent_id;
	private String agent_name;
	private String agent_contact;
	public int getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(int agent_id) {
		this.agent_id = agent_id;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getAgent_contact() {
		return agent_contact;
	}
	public void setAgent_contact(String agent_contact) {
		this.agent_contact = agent_contact;
	}
	

}
