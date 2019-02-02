package hello;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findOneByFirstName(String firstName);
    List<Customer> findAllByLastName(String lastName);
}