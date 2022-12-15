package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(book_title, book_author, book_publication_year) " +
                        "VALUES(?,?,?)",
                book.getBookTitle(), book.getBookAuthor(), book.getBookPublicationYear());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findAny().orElse(null);
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book Set book_title=?, book_author=?, book_publication_year=? WHERE book_id=?",
                updatedBook.getBookTitle(), updatedBook.getBookAuthor(),
                updatedBook.getBookPublicationYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public Person showHolder(int bookId) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.holder_id = Person.person_id " +
                        "WHERE Book.book_id = ?", new BeanPropertyRowMapper<>(Person.class),
                 bookId).stream().findAny().orElse(null);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET holder_id= null WHERE book_id=?", id);
    }

    public void assign(Person person, int bookId) {

        jdbcTemplate.update("UPDATE Book SET holder_id=? WHERE book_id=?", person.getPersonId(), bookId);
    }


}
