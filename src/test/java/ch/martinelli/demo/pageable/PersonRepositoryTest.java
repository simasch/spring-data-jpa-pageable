
package ch.martinelli.demo.pageable;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;

@DataJpaTest
class PersonRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryTest.class);

    @Autowired
    private PersonRepository personRepository;

    @Test
    void page() {
        LOGGER.info("page test");
        Page<Person> page = personRepository.findAll(PageRequest.of(0, 50));

        Assertions.assertThat(page.getTotalElements()).isEqualTo(100);
    }

    @Test
    void slice() {
        LOGGER.info("slice test");

        Slice<Person> slice = personRepository.findAllBy(PageRequest.of(0, 50));

        Assertions.assertThat(slice.getContent().size()).isEqualTo(50);
    }

}
