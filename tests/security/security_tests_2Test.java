/*
 * Testes de Segurança - Arquivo 2/2
 * Gerado automaticamente pela TestAI
 * Repositório: NobreJanio/biblioteca-api
 * Data: 2025-07-25T00:24:53.777Z
 * Linguagem: java
 * Framework: OWASP ZAP
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AuthorizationTest {
    @Autowired
    private MockMvc mockMvc;
    
    // Mocks para serviços utilizados pelos controladores
    @MockBean
    private EmprestimoService emprestimoService;

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    public void givenUserRole_whenGetEmprestimos_thenOk()
        throws Exception{
        
        mockMvc.perform(get("/emprestimos"))
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    public void givenUserRole_whenPostEmprestimo_thenForbidden() 
        throws Exception {
        
        mockMvc.perform(post("/emprestimos"))
        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void givenAdminRole_whenPostEmprestimo_thenCreated()
        throws Exception {
        
        mockMvc.perform(post("/emprestimos"))
        .andExpect(status().isCreated());
    }

    // ... Faça testes análogos para os outros métodos dos controladores ...
}
