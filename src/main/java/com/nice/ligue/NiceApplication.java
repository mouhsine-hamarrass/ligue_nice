package com.nice.ligue;

import com.nice.ligue.repository.EquipeRepository;
import com.nice.ligue.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Nice application.
 */
@SpringBootApplication
public class NiceApplication {
    /**
     * The Equipe repository.
     */
    @Autowired
    public EquipeRepository equipeRepository;

    /**
     * The Joueur repository.
     */
    @Autowired
    public JoueurRepository joueurRepository;


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(NiceApplication.class, args);
    }

}
