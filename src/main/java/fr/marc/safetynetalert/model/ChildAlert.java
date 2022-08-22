package fr.marc.safetynetalert.model;

import java.util.List;

public class ChildAlert {
	
	private List<Child> child;
	private List<Person> otherMember;
	
	
	public ChildAlert(List<Child> child, List<Person> otherMember) {
		super();
		this.child = child;
		this.otherMember = otherMember;
	}


	public List<Child> getChild() {
		return child;
	}


	public void setChild(List<Child> child) {
		this.child = child;
	}


	public List<Person> getOtherMember() {
		return otherMember;
	}


	public void setOtherMember(List<Person> otherMember) {
		this.otherMember = otherMember;
	}
	
}
