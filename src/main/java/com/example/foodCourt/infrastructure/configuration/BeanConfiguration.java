package com.example.foodCourt.infrastructure.configuration;

import com.example.foodCourt.domain.api.IUserServicePort;
import com.example.foodCourt.domain.spi.IEncryptPasswordPersistencePort;
import com.example.foodCourt.domain.spi.IRolePersistencePort;
import com.example.foodCourt.domain.spi.IUserPersistencePort;
import com.example.foodCourt.domain.usecase.UserUseCase;
import com.example.foodCourt.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.example.foodCourt.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.example.foodCourt.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.example.foodCourt.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.example.foodCourt.infrastructure.output.jpa.repository.IRoleRepository;
import com.example.foodCourt.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final IEncryptPasswordPersistencePort encryptPasswordPersistencePort; // inyectado desde @Component

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
        // ahora pasamos el puerto de encriptación al UseCase
        return new UserUseCase(userPersistencePort(), encryptPasswordPersistencePort, rolePersistencePort());
    }
}
