package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.example.demo.doa.TeamDao;
import com.example.demo.domain.Address;
import com.example.demo.domain.Player;
import com.example.demo.domain.Team;

@SpringBootApplication
public class MicroserviceBootApplication extends SpringBootServletInitializer {

	@Autowired
	TeamDao teamDao;
	
	/**
	 * when I am running as a jar this method will be used
	 * @param args
	 */
	public static void main(String[] args) {
		//starts here
		SpringApplication.run(MicroserviceBootApplication.class, args);
	}
	
	
	/**
	 * when I am running as a war this method will be used
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
			
		return builder.sources(MicroserviceBootApplication.class);
	}
	
	@PostConstruct
	public void init() {
		
		Set<Player> players = new HashSet();
		
		Player player1 = new Player("Charlie", "Brown", "pitcher");
		Address address1 = new Address();
		address1.setAddressLine1("5 Kentucky DR");
		address1.setAddressLine2("Brampton, ON");
		player1.setAddress(address1);
		
		Player player2 = new Player("Michael", "Corbat", "shortstop");
		Address address2 = new Address();
		address2.setAddressLine1("10 Kentucky DR");
		address2.setAddressLine2("Piscataway, NJ");
		player2.setAddress(address2);
		
		players.add(player1);
		players.add(player2);
		
		
		Team team = new Team("California", "Peanuts", players);
		teamDao.save(team);
		System.out.println("======== >>> stored data successfully");
	}
	
	
}
