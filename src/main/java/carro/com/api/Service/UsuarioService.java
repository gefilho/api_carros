package carro.com.api.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import carro.com.api.DTO.RegisterDTO;
import carro.com.api.Model.Usuario;
import carro.com.api.Repositorio.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositorio;
	@Autowired
	private PasswordEncoder passwordEnconder;
    @Autowired
    private MockEmailService email;

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

	public ResponseEntity<String> registrarUsuario(RegisterDTO data) {
		if (this.repositorio.findByEmail(data.email()) != null)
			return ResponseEntity.badRequest().build();

		String senha = data.senha();
		if (senha.length() < 12 || !senha.matches(".*[A-Z].*") || !senha.matches(".*[a-z].*")
				|| !senha.matches(".*\\d.*") || !senha.matches(".*[!@#$%^&*()-+=<>?{}].*")) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					"A senha deve ter no mínimo 12 caracteres, incluindo letras maiúsculas, minúsculas, números e caracteres especiais.");
		}

		String encryptedPassword = new BCryptPasswordEncoder().encode(senha);
		Usuario novoUsuario = new Usuario(data.nome(), data.email(), encryptedPassword, data.permissao());
		this.repositorio.save(novoUsuario);
		
        email.enviarEmail(
                data.email(),
                "Cadastro Realizado",
                "Olá " + data.nome() + ", seu cadastro foi realizado com sucesso!"
        );

		return ResponseEntity.ok().build();
	}

	public ResponseEntity<?> atualizar(Integer id, Usuario UsuarioDetalhe) {

		String senha = UsuarioDetalhe.getSenha();
		if (senha.length() < 12 || !senha.matches(".*[A-Z].*") || !senha.matches(".*[a-z].*")
				|| !senha.matches(".*\\d.*") || !senha.matches(".*[!@#$%^&*()-+=<>?{}].*")) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					"A senha deve ter no mínimo 12 caracteres, incluindo letras maiúsculas, minúsculas, números e caracteres especiais.");
		}

		String encoder = this.passwordEnconder.encode(senha);

		Optional<Usuario> optionalUsuario = repositorio.findById(id);
		if (optionalUsuario.isPresent()) {
			Usuario Usuario = optionalUsuario.get();
			Usuario.setNome(UsuarioDetalhe.getNome());
			Usuario.setEmail(UsuarioDetalhe.getEmail());
			Usuario.setSenha(encoder);
			return ResponseEntity.ok(repositorio.save(Usuario));
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

}