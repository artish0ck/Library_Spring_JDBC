package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(person_name, year_of_birth) VALUES(?,?)",
                person.getPersonName(), person.getYearOfBirth());

    }

    public Optional<Person> showByName(String personName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_name=?", new BeanPropertyRowMapper<>(Person.class), personName)
                .stream().findAny();
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny().orElse(null);
    }

    public List<Book> getBooksById(int personId) {
        return jdbcTemplate.query("SELECT * FROM Book  WHERE holder_id=?", new BeanPropertyRowMapper<>(Book.class), personId);
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person Set person_name=?, year_of_birth=? WHERE person_id=?",
                updatedPerson.getPersonName(), updatedPerson.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

}
