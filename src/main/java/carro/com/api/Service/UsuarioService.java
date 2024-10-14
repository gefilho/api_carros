package carro.com.api.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import carro.com.api.Model.Usuario;
import carro.com.api.Repositorio.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repositorio;
    private PasswordEncoder passwordEnconder;
    
    public UsuarioService(UsuarioRepository repository) {
    	this.passwordEnconder = new BCryptPasswordEncoder();
    }

    public List<Usuario> findAll() {
        return repositorio.findAll();
    }

    public Optional<Usuario> findById(int id) {
        return repositorio.findById(id);
    }

    public Usuario criar(Usuario Usuario) {
    	String encoder = this.passwordEnconder.encode(Usuario.getSenha());
    	Usuario.setSenha(encoder);
        return repositorio.save(Usuario);
    }

    public Usuario atualizar(Integer id, Usuario UsuarioDetalhe) {
    	String encoder = this.passwordEnconder.encode(UsuarioDetalhe.getSenha());
        Optional<Usuario> optionalUsuario = repositorio.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario Usuario = optionalUsuario.get();
            Usuario.setNome(UsuarioDetalhe.getNome());
            Usuario.setEmail(UsuarioDetalhe.getEmail());
        	Usuario.setSenha(encoder);
            return repositorio.save(Usuario);
        } else {
            throw new RuntimeException("Usuario não encontrado com o ID: " + id);
        }
    }

    public Usuario editar(Integer id, Usuario UsuarioDetalhe) {
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

	public Boolean validarSenha(Usuario usuario) {
	    Optional<Usuario> optionalUsuario = repositorio.findById(usuario.getId());
	    if (optionalUsuario.isPresent()) {
	        String senha = optionalUsuario.get().getSenha();
	        Boolean valid = passwordEnconder.matches(usuario.getSenha(), senha);
	        return valid;
	    } else {
	        return false;
	    }
	}

}