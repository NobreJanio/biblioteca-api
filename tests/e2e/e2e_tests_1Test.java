/*
 * Testes End-to-End - Arquivo 1/2
 * Gerado automaticamente pela TestAI
 * Repositório: NobreJanio/biblioteca-api
 * Data: 2025-07-25T00:22:59.264Z
 * Linguagem: java
 * Framework: Selenium
 */

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BibliotecaEndToEndTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testListarTodosOsLivros() {
        driver.get("http://localhost:8080/livros");

        WebElement body = driver.findElement(By.tagName("body"));
        assert body.getText().contains("Harry Potter"); // Supõe-se que "Harry Potter" é o livro no banco de dados
    }

    @Test
    public void testRealizarEmprestimo() {
        driver.get("http://localhost:8080/emprestimos/realizar?usuarioId=1&livroId=1&diasParaDevolucao=7");

        WebElement body = driver.findElement(By.tagName("body"));
        assert body.getText().contains("sucesso"); // Supõe-se que a palavra "sucesso" esteja na resposta do empréstimo
    }

    @Test
    public void testListarLivrosEmprestadosParaUsuario() {
        driver.get("http://localhost:8080/emprestimos/usuario/1");

        WebElement body = driver.findElement(By.tagName("body"));
        assert body.getText().contains("Harry Potter"); // Supõe-se que o usuário 1 possui o livro "Harry Potter" emprestado
    }

    @Test
    public void testRealizarDevolucao() {
        driver.get("http://localhost:8080/emprestimos/devolver/1");

        WebElement body = driver.findElement(By.tagName("body"));
        assert body.getText().contains("sucesso"); // Supõe-se que a palavra "sucesso" esteja na resposta da devolução
    }
}
