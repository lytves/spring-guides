package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Before
    public void cleanCollection() {
        repository.deleteAll();
    }

    @Test
    public void save() {

        Customer customer = new Customer("Korben", "Dallas");
        repository.save(customer);

        assertThat(repository.findAll(), hasItem(hasProperty("firstName", is("Korben"))));
    }

    @Test
    public void findOneByFirstName() {

        Customer customer = new Customer("Alis", "Willis");
        repository.save(customer);

        Customer retrievedCustomer = repository.findOneByFirstName("Alis");

        assertNotNull(retrievedCustomer);
        assertEquals(customer.getLastName(), retrievedCustomer.getLastName());
    }

    @Test
    public void findAllByLastName() {

        // given
        Customer customer1 = new Customer("Alis", "Willis");
        Customer customer2 = new Customer("Bob", "Willis");

        // when
        repository.save(customer1);
        repository.save(customer2);

        // then
        List<Customer> retrievedCustomers = repository.findAllByLastName("Willis");
        for (Customer customer : retrievedCustomers) {
            assertNotNull(customer);
            assertEquals(customer.getLastName(), "Willis");
        }
    }
}