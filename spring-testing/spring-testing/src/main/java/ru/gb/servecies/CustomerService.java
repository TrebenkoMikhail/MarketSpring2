package ru.gb.servecies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.model.Customer;
import ru.gb.repository.CustomerRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public Customer create(String name) {
        var customer = new Customer();
        customer.setName(name);
        return customerRepository.save(customer);
    }
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
