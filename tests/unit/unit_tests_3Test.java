/*
 * Testes Unitários - Arquivo 3/3
 * Gerado automaticamente pela TestAI
 * Repositório: NobreJanio/biblioteca-api
 * Data: 2025-07-25T00:21:31.503Z
 * Linguagem: java
 * Framework: JUnit 5
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.biblioteca.controller.dto.LivroDto;

import java.time.LocalDate;

public class TestDtoClasses {
    @Test
    public void testLivroDto() {
        LocalDate publicacao = LocalDate.now();
        LivroDto dto = new LivroDto(1L, "Titulo", "Autor", "ISBN", publicacao, "Editora", 
                                     "Sinopse", 300, true, 1L);
        assertEquals(1L, dto.id());
        assertEquals("Titulo", dto.titulo());
        assertEquals("Autor", dto.autor());
        assertEquals("ISBN", dto.isbn());
        assertEquals(publicacao, dto.dataPublicacao());
        assertEquals("Editora", dto.editora());
        assertEquals("Sinopse", dto.sinopse());
        assertEquals(300, dto.numeroPaginas());
        assertEquals(true, dto.disponivel());
        assertEquals(1L, dto.categoriaId());
    }
   
}
