package org.elsys.ip.servlet.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.elsys.ip.servlet.model.PasswordFactory;
import org.elsys.ip.servlet.model.User;

public class UserService {
	private static HashSet<User> users = new HashSet<>();

	public UserService() throws NoSuchAlgorithmException {
		users.add(new User(1, "admin",
				PasswordFactory.cryptPassword("hehehe"), "admin@admin.bg"));
		users.add(new User(2, "user",
				PasswordFactory.cryptPassword("Tozmnogorazbirae"), "user@user.bg"));
	}

	public List<User> getUsers() {
		return new ArrayList<>(users);
	}

	public User getByName(String name) {
		if (name != null) {
			return users.stream().filter(u -> name.equals(u.getName())).findFirst().orElse(null);
		} else {
			return null;
		}
	}

	public void updateUser(String name, String newName, String password, String email) {
		User user = getByName(name);
		users.remove(user);
		user.setName(newName);
		user.setPassword(PasswordFactory.cryptPassword(password));
		user.setEmail(email);
		users.add(user);
	}

	public void deleteUser(String name) {
		User user = getByName(name);
		users.remove(user);
	}

	public void addUser(String name, String password, String email) {
		int id = users.size()+ 1;
		users.add(new User(id, name, password, email));
	}
}
