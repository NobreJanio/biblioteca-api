/*
 * Testes End-to-End - Arquivo 2/2
 * Gerado automaticamente pela TestAI
 * Repositório: NobreJanio/biblioteca-api
 * Data: 2025-07-25T00:23:25.833Z
 * Linguagem: java
 * Framework: Selenium
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

public class TestingControllers {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Configuração inicial. Aqui estamos usando o navegador Firefox.
        System.setProperty("webdriver.gecko.driver", "path-to-geckodriver");
        driver = new FirefoxDriver();
    }
    
    @Test
    public void testEmprestimoLivro() throws Exception {
        // Carrega a página de empréstimos
        driver.get("http://localhost:8080/emprestimos");

        // Seleciona um livro para empréstimo
        driver.findElement(By.id("livro-id")).click();
      
        // Verifica se o empréstimo foi realizado com sucesso
        assertTrue(driver.findElement(By.id("mensagem-sucesso")).isDisplayed());
    }
    
    @Test
    public void testDevolucaoLivro() throws Exception {
        // Carrega a página de empréstimos
        driver.get("http://localhost:8080/emprestimos");

        // Realiza a devolução de um livro
        driver.findElement(By.id("devolver-id")).click();
      
        // Verifica se a devolução foi realizada com sucesso
        assertTrue(driver.findElement(By.id("mensagem-sucesso")).isDisplayed());
    }
    
    @Test
    public void testCriarLivro() throws Exception {
        // Carrega a página de livros
        driver.get("http://localhost:8080/livros");

        // Preenche os dados do livro
        driver.findElement(By.id("titulo")).sendKeys("Teste de Livro");
        // demais campos ...
      
        // Submete o formulário
        driver.findElement(By.id("submit")).click();
      
        // Verifica se o livro foi criado com sucesso
        assertTrue(driver.findElement(By.id("mensagem-sucesso")).isDisplayed());
    }

    @After
    public void tearDown() throws Exception {
        // Fecha o navegador após a execução de cada teste.
        driver.quit();
    }
}
