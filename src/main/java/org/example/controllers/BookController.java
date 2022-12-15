package org.example.controllers;

import org.example.dao.BookDao;
import org.example.models.Book;
import org.example.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("books")
public class BookController {
    public final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDao.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDao.show(id));
        model.addAttribute("people", bookDao.indexPeople());
        model.addAttribute("holder", bookDao.showHolder(id));
        return "book/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("book", new Book());
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/new";
        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.show(id));
        return "/book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "book/edit";
        bookDao.update(id, book);
        return "redirect:/books";
    }

    @PostMapping("/{id}")
    public String release(@PathVariable("id") int bookId) {
        bookDao.release(bookId);
        return "redirect:/books/"+bookId;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String assign(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookDao.addHolder(person.getPersonId(), id);
        return "redirect:/books/" + id;
    }

}
