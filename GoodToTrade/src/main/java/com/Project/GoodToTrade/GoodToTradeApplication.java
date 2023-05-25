package com.Project.GoodToTrade;

import com.Project.GoodToTrade.Models.Role;
import com.Project.GoodToTrade.Models.Users;
import com.Project.GoodToTrade.Services.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class GoodToTradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodToTradeApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UsersService usersService) {
		return args -> {
			usersService.saveRole(new Role(null, "ROLE_USER"));
			usersService.saveRole(new Role(null, "ROLE_ADMIN"));

			usersService.saveUser(new Users("Steinburg", "Javier Esparza", "cof.core@gmail.com", "123456789", "1234", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
			usersService.saveUser(new Users("James", "Chicorita", "james@smith.com", "123456789", "1234", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
			usersService.saveUser(new Users("Jane", "Pikachu", "jane@carry.com", "123456789", "1234", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
			usersService.saveUser(new Users("Chris", "Corleone", "chris@anderson.com", "123456789", "1234", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

			usersService.addRoleToUser("Steinburg", "ROLE_ADMIN");
			usersService.addRoleToUser("james", "ROLE_ADMIN");
			usersService.addRoleToUser("jane", "ROLE_USER");
			usersService.addRoleToUser("chris", "ROLE_ADMIN");
			usersService.addRoleToUser("chris", "ROLE_USER");
		};
	}

}
