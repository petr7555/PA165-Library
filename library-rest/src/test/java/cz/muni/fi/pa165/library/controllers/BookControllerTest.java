package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.facade.BookFacade;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 */
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Mock
    private BookFacade bookFacade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "USER")
    public void debugTest() throws Exception {
        when(bookFacade.findAllBooks()).thenReturn(Collections.unmodifiableList(createBooks()));
        
        mockMvc.perform(get("/pa165/rest/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print());
    }

    private List<BookDTO> createBooks() {
        BookDTO bookDTO = new BookDTO();
        return List.of(new BookDTO());
    }


    @Test
    void createBook() {

    }

    @Test
    void deleteBook() {
    }

    @Test
    void findAllBooks() {
    }

    @Test
    void findByTitle() {
    }

    @Test
    void findByAuthor() {
    }
}
