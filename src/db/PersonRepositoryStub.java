package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Person;
import domain.Role;

public class PersonRepositoryStub implements PersonRepository {
	private final Map<String, Person> persons = new HashMap<>();
	
	public PersonRepositoryStub () {
		Person administrator = new Person("admin", "t", "Ad", "Min", Role.ADMIN);
		add(administrator);
		Person jan = new Person("jan", "t", "Jan", "Janssens", Role.USER);
        add(jan);
		Person an = new Person("an", "t", "An", "Cornelissen", Role.USER);
		add(an);
		Person bert = new Person("bert", "t", "Bert", "Von Sesame", Role.USER);
		add(bert);
		Person ernie = new Person("ernie", "t", "Bert", "Von Sesame", Role.USER);
		add(ernie);

		jan.addFriend(an);
		jan.addFriend(administrator);
	}
	
	public Person get(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		return persons.get(personId);
	}
	
	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());	
	}

	public void add(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		if (persons.containsKey(person.getUsername())) {
			throw new IllegalArgumentException("User already exists");
		}
		persons.put(person.getUsername(), person);
	}
	
	public void update(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		persons.put(person.getUsername(), person);
	}
	
	public void delete(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		persons.remove(personId);
	}
	
	public Person getAuthenticatedUser(String email, String password) {
		Person person = get(email);
		
		if (person != null && person.isCorrectPassword(password)) {
			return person;
		}
		else {
			return null;
		}
	}
}
