package com.olteanuflorin86.sdjpaintro.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.olteanuflorin86.sdjpaintro.repositories.ElectricGuitarRepository;
import com.olteanuflorin86.sdjpaintro.domain.joined.ElectricGuitar;

@Component
public class Bootstrap implements CommandLineRunner {
	
    @Autowired
    ElectricGuitarRepository electricGuitarRepository;

	@Override
	public void run(String... args) throws Exception {

        ElectricGuitar eg = new ElectricGuitar();
        eg.setNumberOfStrings(6);
        eg.setNumberOfPickups(2);
        electricGuitarRepository.save(eg);
		
	}

}
