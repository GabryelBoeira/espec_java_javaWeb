package br.edu.utfpr.cp.espjava.crudcidade.service;

import br.edu.utfpr.cp.espjava.crudcidade.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(final UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var usuario = usuarioRepository.findByNome(username);
        if (usuario.isPresent()) return usuario.get();

        throw new UsernameNotFoundException("Usuário não encontrado/inválido");
    }
}
