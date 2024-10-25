package ru.WorkHub.DemoWorkHub.service; // Изменено название пакета на соответствующее проекту WorkHub

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.WorkHub.DemoWorkHub.entity.Customer; // Изменено с User на Customer
import ru.WorkHub.DemoWorkHub.repository.CustomerRepository; // Изменено с UserRepository на CustomerRepository

import java.util.stream.Collectors;

@Service
public class CustomCustomerDetailsService implements UserDetailsService { // Изменено с CustomUserDetailsService на CustomCustomerDetailsService
    private CustomerRepository customerRepository; // Изменено с UserRepository на CustomerRepository

    public CustomCustomerDetailsService(CustomerRepository customerRepository) { // Изменено с UserRepository на CustomerRepository
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(usernameOrEmail); // Изменено с User на Customer
        if (customer != null) {
            return new org.springframework.security.core.userdetails.User(
                    customer.getEmail(),
                    customer.getPassword(),
                    customer.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
            );
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}