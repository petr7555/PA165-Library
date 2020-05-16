package cz.muni.fi.pa165.library.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.facade.BookFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 */
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @MockBean
    private BookFacade bookFacade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void unauthorizedUserIsRedirectedToLogin() throws Exception {
        mockMvc.perform(get("/pa165/rest/books"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void findAllBooksAsUser() throws Exception {
        when(bookFacade.findAllBooks()).thenReturn(Collections.unmodifiableList(createBooks()));

        mockMvc.perform(get("/pa165/rest/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].title").value("Animal Farm"))
                .andExpect(jsonPath("$.[?(@.id==1)].author").value("George Orwell"))
                .andExpect(jsonPath("$.[?(@.id==2)].title").value("The Jungle Book"))
                .andExpect(jsonPath("$.[?(@.id==2)].author").value("Rudyard Kipling"))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void findAllBooksAsAdmin() throws Exception {
        when(bookFacade.findAllBooks()).thenReturn(Collections.unmodifiableList(createBooks()));

        mockMvc.perform(get("/pa165/rest/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void createBookAsUser() throws Exception {
        mockMvc.perform(post("/pa165/rest/books"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void createBookAsAdmin() throws Exception {
        BookDTO book = createAnimalFarm();
        book.setId(0);

        when(bookFacade.createBook(any())).thenReturn(4L);

        mockMvc.perform(post("/pa165/rest/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToString(book)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("4"))
                .andDo(print());
    }

    private static String convertObjectToString(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void deleteBookAsUser() throws Exception {
        mockMvc.perform(delete("/pa165/rest/books"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void deleteBookAsAdmin() throws Exception {
        BookDTO book = createAnimalFarm();

        when(bookFacade.deleteBook(4)).thenReturn(4L);

        mockMvc.perform(delete("/pa165/rest/books")
                .param("id", "4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("4"))
                .andDo(print());
    }

    private List<BookDTO> createBooks() {
        BookDTO book1 = createAnimalFarm();
        BookDTO book2 = createTheJungleBook();
        return List.of(book1, book2);
    }

    private BookDTO createAnimalFarm() {
        BookDTO book1 = new BookDTO();
        book1.setId(1);
        book1.setTitle("Animal Farm");
        book1.setAuthor("George Orwell");
        return book1;
    }

    private BookDTO createTheJungleBook() {
        BookDTO book2 = new BookDTO();
        book2.setId(2);
        book2.setTitle("The Jungle Book");
        book2.setAuthor("Rudyard Kipling");
        return book2;
    }
}
