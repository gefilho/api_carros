package carro.com.api.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import carro.com.api.Model.Admin;
import carro.com.api.Repositorio.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository repositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin criar(Admin admin) {
        // Criptografa a senha antes de salvar
        admin.setSenha(passwordEncoder.encode(admin.getSenha()));
        return repositorio.save(admin);
    }

    public List<Admin> findAll() {
        return repositorio.findAll();
    }

    public Optional<Admin> findById(int id) {
        return repositorio.findById(id);
    }

    public Admin atualizar(Integer id, Admin adminDetalhe) {
        Optional<Admin> optionalAdmin = repositorio.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setNome(adminDetalhe.getNome());
            admin.setEmail(adminDetalhe.getEmail());
            admin.setSenha(adminDetalhe.getSenha());
            return repositorio.save(admin);
        } else {
            throw new RuntimeException("Admin não encontrado com o ID: " + id);
        }
    }

    public Admin editar(Long id, Admin adminDetalhe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }

    public void deletar(Integer id) {
        Optional<Admin> optionalAdmin = repositorio.findById(id);
        if (optionalAdmin.isPresent()) {
            repositorio.deleteById(id);
        } else {
            throw new RuntimeException("Admin não encontrado com o ID: " + id);
        }
    }


    /*public Admin editar(String nome, Admin adminDetalhe) {
        Optional<Admin> optionalAdmin = repositorio.findByNome(nome);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setNome(adminDetalhe.getNome());
            admin.setEmail(adminDetalhe.getEmail());
            admin.setSenha(adminDetalhe.getSenha());
            return repositorio.save(admin);
        } else {
            throw new RuntimeException("Admin nÃ£o encontrado com o nome: " + nome);
        }
    }*/

 /*public void deletar(String nome) {
        Optional<Admin> admin = repositorio.findByNome(nome);
        if (admin.isPresent()) {
            repositorio.deleteByNome(nome);
            System.out.println("Admin " + nome + " deletado.");
        } else {
            throw new RuntimeException("Admin nÃ£o encontrado com o nome: " + nome);
        }
    }*/

 /*public Admin criar(Admin admin) {
        return repositorio.save(admin);
    }*/
}
