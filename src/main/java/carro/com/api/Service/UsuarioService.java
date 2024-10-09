package carro.com.api.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carro.com.api.Model.Usuario;
import carro.com.api.Repositorio.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repositorio;

    public List<Usuario> findAll() {
        return repositorio.findAll();
    }

    public Optional<Usuario> findById(int id) {
        return repositorio.findById(id);
    }

    public Usuario criar(Usuario Usuario) {
        return repositorio.save(Usuario);
    }

    public Usuario atualizar(Integer id, Usuario UsuarioDetalhe) {
        Optional<Usuario> optionalUsuario = repositorio.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario Usuario = optionalUsuario.get();
            Usuario.setNome(UsuarioDetalhe.getNome());
            Usuario.setEmail(UsuarioDetalhe.getEmail());
            Usuario.setSenha(UsuarioDetalhe.getSenha());
            return repositorio.save(Usuario);
        } else {
            throw new RuntimeException("Usuario não encontrado com o ID: " + id);
        }
    }

    public Usuario editar(Long id, Usuario UsuarioDetalhe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }

    public void deletar(Integer id) {
        Optional<Usuario> optionalUsuario = repositorio.findById(id);
        if (optionalUsuario.isPresent()) {
            repositorio.deleteById(id);
        } else {
            throw new RuntimeException("Usuario não encontrado com o ID: " + id);
        }
    }

    }