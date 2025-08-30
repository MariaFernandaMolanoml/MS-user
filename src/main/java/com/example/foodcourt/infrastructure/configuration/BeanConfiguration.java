package com.example.foodcourt.infrastructure.configuration;

import com.example.foodcourt.domain.api.IAuthServicePort;
import com.example.foodcourt.domain.api.IUserServicePort;
import com.example.foodcourt.domain.spi.IEncryptPasswordPersistencePort;
import com.example.foodcourt.domain.spi.IRolePersistencePort;
import com.example.foodcourt.domain.spi.IUserPersistencePort;
import com.example.foodcourt.domain.usecase.AuthUseCase;
import com.example.foodcourt.domain.usecase.UserUseCase;
import com.example.foodcourt.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.example.foodcourt.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.example.foodcourt.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.example.foodcourt.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.example.foodcourt.infrastructure.output.jpa.repository.IRoleRepository;
import com.example.foodcourt.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final IEncryptPasswordPersistencePort encryptPasswordPersistencePort;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), encryptPasswordPersistencePort, rolePersistencePort());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IAuthServicePort authServicePort() {
        return new AuthUseCase(userPersistencePort(), passwordEncoder());
    }
}
