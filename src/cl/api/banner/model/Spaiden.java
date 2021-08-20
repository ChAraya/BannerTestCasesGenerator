package cl.api.banner.model;

import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;

public class Spaiden {
	
	private List<Person> personList;
	
	public Spaiden(){
		this.personList = new ArrayList<Person>();
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	
	public void addPerson(Person p) {
		this.personList.add(p);
	}
	
	public void printList() {
		for (int i =0;i<personList.size();i++) {
			System.out.println("Rut: "+ personList.get(i).getSpriden_id());
			System.out.println("Name: " +personList.get(i).getName());
			System.out.println("Pidm: " +personList.get(i).getPidm());
			
			if (!isNull(personList.get(i).getAdmission())) {
				System.out.println("Program: " +personList.get(i).getAdmission().getProgram());	
				System.out.println("Camp Code: " +personList.get(i).getAdmission().getCamp_code());
				if (!isNull(personList.get(i).getSgastdn())) {
					System.out.println("Study Path: " +personList.get(i).getSgastdn().getKey_seqno());					
				}				
			}
			
			
			System.out.println("************************************************");
		}
	}
}
