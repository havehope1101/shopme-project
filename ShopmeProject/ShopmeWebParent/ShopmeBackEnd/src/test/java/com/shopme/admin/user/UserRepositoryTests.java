package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userLongLe = new User("haha@gmail.com", "long2020", "Long", "Le");
		userLongLe.addRole(null);
		
		User saveUser = repo.save(userLongLe);
		assertThat(saveUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userTank = new User("tank@gmail.com", "tank222", "Tank", "Fight");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userTank.addRole(roleEditor);
		userTank.addRole(roleAssistant);
		
		User saveUser = repo.save(userTank);
		
		assertThat(saveUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		User userTank = repo.findById(1).get();
		System.out.println(userTank);
		assertThat(userTank).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userTank = repo.findById(1).get();
		userTank.setEnabled(true);
		userTank.setEmail("hihihoho@gmail.com");
		
		repo.save(userTank);
	}
	
	@Test 
	public void testUpdateUserRoles() {
		User userTank = repo.findById(2).get();
		Role roleEditor = new Role(4);
		Role roleSalesperson = new Role(3);
		
		userTank.getRoles().remove(roleEditor);
		userTank.addRole(roleSalesperson);
		
		repo.save(userTank);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "hihihoho@gmail.com";
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}
}
