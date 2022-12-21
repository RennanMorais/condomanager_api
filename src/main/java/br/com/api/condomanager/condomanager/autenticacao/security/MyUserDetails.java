package br.com.api.condomanager.condomanager.autenticacao.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

  private final UsuarioRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final UserEntity user = userRepository.findByEmail(email);

    if (user == null) {
      throw new UsernameNotFoundException("User '" + email + "' not found");
    }

    return org.springframework.security.core.userdetails.User
        .withUsername(email)//
        .password(user.getPassword())
        .authorities(user.getNomeAccess())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
