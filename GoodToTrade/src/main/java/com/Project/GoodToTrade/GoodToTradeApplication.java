package com.Project.GoodToTrade;

import com.Project.GoodToTrade.Enums.Category;
import com.Project.GoodToTrade.Enums.SubCategory;
import com.Project.GoodToTrade.Models.Products;
import com.Project.GoodToTrade.Models.Role;
import com.Project.GoodToTrade.Models.TheLikes;
import com.Project.GoodToTrade.Models.Users;
import com.Project.GoodToTrade.Services.ProductsService;
import com.Project.GoodToTrade.Services.TheLikesService;
import com.Project.GoodToTrade.Services.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

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
	CommandLineRunner run(UsersService usersService, ProductsService productService, TheLikesService theLikesService) {
		return args -> {
			usersService.saveRole(new Role(null, "ROLE_USER"));
			usersService.saveRole(new Role(null, "ROLE_ADMIN"));

			Users trainer1 = usersService.saveUser(new Users("Trainer1", "1", "trainer1@pokemon.com", "123456789", "1234", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
			Users trainer2 = usersService.saveUser(new Users("Trainer2", "2", "trainer2@pokemon.com", "123456789", "1234", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
			Users trainer3 = usersService.saveUser(new Users("Trainer3", "3", "trainer3@pokemon.com", "123456789", "1234", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
			Users trainer4 = usersService.saveUser(new Users("Trainer4", "4", "trainer4@pokemon.com", "123456789", "1234", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
			Users trainer5 = usersService.saveUser(new Users("Trainer5", "5", "trainer5@pokemon.com", "123456789", "1234", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

			Products squirtle = productService.saveProduct(new Products("Squirtle", 300.0, "Water Pokemon.", "https://c4.wallpaperflare.com/wallpaper/633/184/843/pokemon-macbook-hd-wallpaper-preview.jpg", Category.POKEMONS, SubCategory.WATER, Category.POKEMONS, SubCategory.WATER, trainer1, new ArrayList<>()));
			Products charmander = productService.saveProduct(new Products("Charmander", 300.0, "Fire Pokemon.", "https://p4.wallpaperbetter.com/wallpaper/171/549/94/pokemon-pokemon-red-and-blue-wallpaper-preview.jpg", Category.POKEMONS, SubCategory.FIRE, Category.POKEMONS, SubCategory.FIRE, trainer2, new ArrayList<>()));
			Products eevee = productService.saveProduct(new Products("Eevee", 300.0, "Normal Pokemon.", "https://img1.picmix.com/output/pic/normal/5/0/5/6/9926505_5ee3e.jpg", Category.POKEMONS, SubCategory.NORMAL, Category.POKEMONS, SubCategory.NORMAL, trainer3, new ArrayList<>()));
			Products caterpie = productService.saveProduct(new Products("Caterpie", 300.0, "Bug Pokemon.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSgp0Eppyv1_426luRydeFsM47CDjzGbuOElA&usqp=CAU", Category.POKEMONS, SubCategory.BUG, Category.POKEMONS, SubCategory.BUG, trainer4, new ArrayList<>()));
			Products pikachu = productService.saveProduct(new Products("Pikachu", 300.0, "Electric Pokemon.", "https://www.latercera.com/resizer/j-ZtEKzoY5fUQtLLGOVNBOpG7Aw=/arc-anglerfish-arc2-prod-copesa/public/6O3E6FX56FCYFGW5NZFOKYGNY4.jpg", Category.POKEMONS, SubCategory.ELECTRIC, Category.POKEMONS, SubCategory.ELECTRIC, trainer5, new ArrayList<>()));

			usersService.addRoleToUser("Trainer1", "ROLE_ADMIN");
			usersService.addRoleToUser("Trainer2", "ROLE_ADMIN");
			usersService.addRoleToUser("Trainer3", "ROLE_USER");
			usersService.addRoleToUser("Trainer4", "ROLE_ADMIN");
			usersService.addRoleToUser("Trainer5", "ROLE_USER");

			List<Users> allUsers = List.of(trainer1, trainer2, trainer3, trainer4, trainer5);

			for (Users user : allUsers) {
				if (user.getUsername().equals("Trainer2") || user.getUsername().equals("Trainer3") || user.getUsername().equals("Trainer4") || user.getUsername().equals("Trainer5")) {
					TheLikes like = new TheLikes(user, squirtle);
					theLikesService.saveLike(like);
				}
			}
		};
	}
}
