package fr.marc.safetynetalert.model;

import java.util.List;

public class ChildAlert {
	
	private List<Child> child;
	private List<Child> otherMember;
	

	public ChildAlert() {
	}
	
	
	public ChildAlert(List<Child> child, List<Child> otherMember) {
		this.child = child;
		this.otherMember = otherMember;
	}
	

	public List<Child> getChild() {
		return child;
	}


	public void setChild(List<Child> child) {
		this.child = child;
	}


	public List<Child> getOtherMember() {
		return otherMember;
	}


	public void setOtherMember(List<Child> otherMember) {
		this.otherMember = otherMember;
	}
	
}
