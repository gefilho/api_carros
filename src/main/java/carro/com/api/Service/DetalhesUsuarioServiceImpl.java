package carro.com.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import carro.com.api.Model.Admin;
import carro.com.api.Repositorio.AdminRepository;

@Service
public class DetalhesUsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository repositorio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin administrador = repositorio.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(administrador.getEmail())
                .password(administrador.getSenha())
                .roles("ADMIN") // personaliza
                .build();
    }
}
