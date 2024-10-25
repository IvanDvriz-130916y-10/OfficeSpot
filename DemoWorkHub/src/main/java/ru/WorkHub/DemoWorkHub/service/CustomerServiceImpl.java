package ru.WorkHub.DemoWorkHub.service; // Изменено название пакета на соответствующее проекту WorkHub

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.WorkHub.DemoWorkHub.dto.CustomerDto; // Изменено с UserDto на CustomerDto
import ru.WorkHub.DemoWorkHub.entity.Role;
import ru.WorkHub.DemoWorkHub.entity.Customer; // Изменено с User на Customer
import ru.WorkHub.DemoWorkHub.repository.RoleRepository;
import ru.WorkHub.DemoWorkHub.repository.CustomerRepository; // Изменено с UserRepository на CustomerRepository

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService { // Изменено с UserServiceImpl на CustomerServiceImpl

    private final CustomerRepository customerRepository; // Изменено с UserRepository на CustomerRepository
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, // Изменено с UserRepository на CustomerRepository
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveCustomer(CustomerDto customerDto) { // Изменено с saveUser на saveCustomer
        Customer customer = new Customer(); // Изменено с User на Customer
        customer.setName(customerDto.getFirstName() + " " + customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());

        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        customer.setRoles(Arrays.asList(role));
        customerRepository.save(customer); // Изменено с userRepository на customerRepository
    }

    @Override
    public Customer findCustomerByEmail(String email) { // Изменено с findUserByEmail на findCustomerByEmail
        return customerRepository.findByEmail(email); // Изменено с userRepository на customerRepository
    }

    @Override
    public List<CustomerDto> findAllCustomers() { // Изменено с findAllUsers на findAllCustomers
        List<Customer> customers = customerRepository.findAll(); // Изменено с users на customers
        return customers.stream().map(this::mapToCustomerDto).collect(Collectors.toList()); // Изменено на mapToCustomerDto
    }

    private CustomerDto mapToCustomerDto(Customer customer) { // Изменено с mapToUserDto на mapToCustomerDto
        CustomerDto customerDto = new CustomerDto(); // Изменено с UserDto на CustomerDto
        String[] str = customer.getName().split(" ");
        customerDto.setFirstName(str[0]);
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}