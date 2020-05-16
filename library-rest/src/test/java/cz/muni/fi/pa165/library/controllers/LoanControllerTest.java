package cz.muni.fi.pa165.library.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.library.dto.LoanDTO;
import cz.muni.fi.pa165.library.facade.LoanFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Petr Janik 485122
 * @since 16.05.2020
 */
@SpringBootTest
@AutoConfigureMockMvc
class LoanControllerTest {

    @MockBean
    private LoanFacade loanFacade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "USER")
    public void createLoanAsUser() throws Exception {
        LoanDTO loan = new LoanDTO();

        mockMvc.perform(post("/pa165/rest/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToString(loan)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void createLoanAsAdmin() throws Exception {
        LoanDTO loan = new LoanDTO();

        mockMvc.perform(post("/pa165/rest/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToString(loan)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private static String convertObjectToString(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
