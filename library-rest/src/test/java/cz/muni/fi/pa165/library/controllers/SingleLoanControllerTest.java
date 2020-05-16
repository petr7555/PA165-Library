package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.facade.SingleLoanFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Petr Janik 485122
 * @since 16.05.2020
 */
@SpringBootTest
@AutoConfigureMockMvc
class SingleLoanControllerTest {

    @MockBean
    private SingleLoanFacade singleLoanFacade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "USER")
    public void findSingleLoansForUserAsUser() throws Exception {
        mockMvc.perform(get("/pa165/rest/singleLoans")
                .queryParam("userId", "1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void findSingleLoansForBookAsUser() throws Exception {
        mockMvc.perform(get("/pa165/rest/singleLoans")
                .queryParam("bookId", "1"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }
}
