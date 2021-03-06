package domain;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Person {

	private String username; // unique PK
	private String password;
	private String salt;
	private String firstName;
	private String lastName;
	private Role role;
	private String status;
	private List<Person> friends;

	public Person(String username, String password, String firstName,
				  String lastName, Role role) {
		setUsername(username);
		setHashedPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
		setStatus("Offline");
		friends = new ArrayList<>();
	}

	public Person(String username, String password, String salt,
				  String firstName, String lastName, Role role) {
		setUsername(username);
		setPassword(password);
		setSalt(salt);
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
	}

	public Person() {
	}

	public Role getRole() {
		return this.role;
	}

	private void setRole(Role role) {
		this.role=role;
	}
	

	private void setUsername(String username) {
		if (username.isEmpty()) {
			throw new IllegalArgumentException("No id given");
		}/*
		String USERID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(username);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}*/
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	private String getPassword() {
		return password;
	}

	public boolean isCorrectPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		return getPassword().equals(hashPassword(password, getSalt()));
	}

	private void setPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	private void setHashedPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		this.password = hashPassword(password);
	}

	private String hashPassword(String password) {
		SecureRandom random = new SecureRandom();
		byte[] seed = random.generateSeed(20);

		String salt = new BigInteger(1, seed).toString(16);
		this.setSalt(salt);

		return hashPassword(password, salt);
	}

	private String hashPassword(String password, String seed) {
		String hashedPassword;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(salt.getBytes(StandardCharsets.UTF_8));
			crypt.update(password.getBytes(StandardCharsets.UTF_8));
			hashedPassword = new BigInteger(1, crypt.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new DomainException(e.getMessage(), e);
		}
        return hashedPassword;
	}

	private void setSalt(String salt) {
		this.salt = salt;
	}

	private String getSalt() {
		return salt;
	}

	public String getFirstName() {
		return firstName;
	}

	private void setFirstName(String firstName) {
		if (firstName.isEmpty()) {
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;// firstName;

	}

	public String getLastName() {
		return lastName;
	}

	private void setLastName(String lastName) {
		if (lastName.isEmpty()) {
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		if (status == null || status.trim().isEmpty()) {
			throw new IllegalArgumentException("No status given");
		}
		this.status = status.trim();
	}


	public List<Person> getFriends() {
		return friends;
	}

	public void addFriend(Person friend) {
	    if(friend == null) throw new IllegalArgumentException("This is not an existing person.");
		if(friend == this) throw new IllegalArgumentException("You can't befriend yourself");
		if(friends.contains(friend))return;
		friends.add(friend);
        friend.addFriend(this);
    }



}
