package carro.com.api.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carro.com.api.Model.Carro;
import carro.com.api.Repositorio.CarroRepository;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repositorio;

    public List<Carro> findAll() {
        return repositorio.findAll();
    }

    public Carro criar(Carro carro) {
        return repositorio.save(carro);
    }

    public Carro atualizar(int id, Carro carroDetalhe) {
        Optional<Carro> carroOptional = repositorio.findById(id);
        if (carroOptional.isPresent()) {
            Carro carro = carroOptional.get();
            carro.setNome(carroDetalhe.getNome());
            carro.setPreco(carroDetalhe.getPreco());
            carro.setAno(carroDetalhe.getAno());
            carro.setMarca(carroDetalhe.getMarca());
            carro.setCidade(carroDetalhe.getCidade());
            carro.setPlaca(carroDetalhe.getPlaca());
            return repositorio.save(carro);
        } else {
            throw new RuntimeException("Carro não encontrado com id: " + id);
        }
    }

    public void deletar(int id) {
        Optional<Carro> carroOptional = repositorio.findById(id);
        if (carroOptional.isPresent()) {
            repositorio.delete(carroOptional.get());
        } else {
            throw new RuntimeException("Carro não encontrado com id: " + id);
        }
    }
}
