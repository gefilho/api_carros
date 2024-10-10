package carro.com.api.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import carro.com.api.Model.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {

    
}