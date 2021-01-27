package com.epam.esm.service.user;

import com.epam.esm.entity.User;
import com.epam.esm.repository.specification.UsersByEmailCriteriaSpecification;
import com.epam.esm.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository repository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = findUserByEmail(username).
        orElseThrow(() -> new UsernameNotFoundException("Invalid email or password."));
    return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getPassword(),
        getAuthority(user)
    );
  }

  public Optional<User> findUserByEmail(String email) {
    return repository.findByEmail(new UsersByEmailCriteriaSpecification(email));
  }

  private List<SimpleGrantedAuthority> getAuthority(User user) {
    return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getValue()));
  }
}
