package es.udc.paproject.e2etests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

class AppTest {

    String validViewer = "viewer";
    String validTicketseller = "ticketseller";
    String validPassword = "pa2223";
    String validCreditCard = "1234567891234567";

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    private void login(String username, String password) {
        // Abrir formulario de login
        driver.findElement(By.id("loginLink")).click();

        // Introducir usuario y contraseña
        WebElement usernameInput = driver.findElement(By.id("userName"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        // Enviar formulario
        driver.findElement(By.id("loginSubmit")).click();
    }

    @Test
    void testLogin() {
        // Loguearse
        login(validViewer, validPassword);

        // Encontrar el menú de opciones de usuario
        WebElement userOptions = driver.findElement(By.id("userOptions"));

        // Comprobar que el usuario está logueado y aparece el menú de opciones
        assertTrue(userOptions.isDisplayed());
        // Comprobar que el nombre del usuario logueado coincide con el introducido
        assertTrue(userOptions.getText().contains(validViewer));
    }

    //TT-1

    @Test
    void TT_1_ver_session(){
        login(validViewer, validPassword);
        Select select = new Select(driver.findElement(By.id("billboardDate")));
        select.selectByIndex(1);
        List<WebElement> peliculas = driver.findElements(By.className("cartelera-item-title"));
        String titulo_pelicula = (peliculas.get(0).getText());
        System.out.println(titulo_pelicula);

        List<WebElement> links = driver.findElements(By.tagName("a"));
        WebElement link_sesion = null;
        String hora_sesion = null;
        for(int i=0; i < links.size(); i++) { //cojo el link de la sesion, de todos los de la pagina
            if(links.get(i).getText().equals("12:00"))
                link_sesion = links.get(i);
        }
        hora_sesion = link_sesion.getText(); //guardamos la hora de la sesion
        link_sesion.click();


        WebElement precios = driver.findElement(By.id("Precios"));
        assertTrue(precios.isDisplayed());
        WebElement duracion = driver.findElement(By.id("duracion"));
        assertTrue(duracion.isDisplayed());
        WebElement hora_y_fecha = driver.findElement(By.id("nombre_sala_y_fecha"));
        assertTrue(hora_y_fecha.isDisplayed());
        WebElement fecha_y_hora = driver.findElement(By.id("fecha_y_hora"));
        assertTrue(fecha_y_hora.isDisplayed());

        List<WebElement> links_2 = driver.findElements(By.tagName("a"));
        WebElement link_pelicula = null;
        for(int i=0; i < links_2.size(); i++) { //cojo el link de la pelicula, de todos los de la pagina
            if(links_2.get(i).getText().equals(titulo_pelicula)) //tecnicamente con esto se comprueba si el nombre de la pelicula es correcto
                link_pelicula = links_2.get(i);
        } // pero lo vuelvo a hacer por si acaso
        assertEquals(link_pelicula.getText(), titulo_pelicula);
        String[] horas = fecha_y_hora.getText().split(" ");
        assertEquals(horas[2], hora_sesion); // horas[2] es la hora de la sesion, por el split


        WebElement formCompra = driver.findElement(By.id("formCompra"));
        assertTrue(formCompra.isDisplayed());

    }

    @Test
    void TT_2_comprar(){
        // Loguearse
        login(validViewer, validPassword);

        // Abrimos la página de la sesión
        Select select = new Select(driver.findElement(By.id("billboardDate")));
        select.selectByIndex(1);

        List<WebElement> links = driver.findElements(By.tagName("a"));
        WebElement link_sesion = null;
        for (WebElement link : links) { //cojo el link de la sesion, de todos los de la pagina
            if (link.getText().equals("12:00"))
                link_sesion = link;
        }
        link_sesion.click();


        // Localizar el título de la película
        WebElement pelicula = driver.findElement(By.className("tituloPeli"));
        String[] tituloPeliculaTodo = pelicula.getText().split(" ");

        String tituloPelicula = tituloPeliculaTodo[tituloPeliculaTodo.length - 1];


        // Introducir cantidad y número de tarjeta
        WebElement quantityInput = driver.findElement(By.id("quantity"));
        WebElement numTarjetaInput = driver.findElement(By.id("numTarjeta"));

        quantityInput.sendKeys("2");
        numTarjetaInput.sendKeys("1234567891234567");

        // Confirmamos la compra
        driver.findElement(By.id("submitCompra")).click();

        // Localizamos el elemento que contiene el identificador de compra
        WebElement resultadoCompra = driver.findElement(By.id("resultadoCompra"));
        String[] idCompraTodo = resultadoCompra.getText().split(" ");
        String idCompra = idCompraTodo[idCompraTodo.length - 1];

        // Vamos a la parte de la página en la que se muestran las compras realizadas hasta el momento
        driver.findElement(By.id("verComprasLink")).click();
        List<WebElement> idsCompras = driver.findElements(By.className("compras-item-title"));
        String[] idPrimeraCompraTodo = idsCompras.get(0).getText().split(" ");
        String idPrimeraCompra = idPrimeraCompraTodo[idPrimeraCompraTodo.length - 1];
        List<WebElement> titulosPelisCompras = driver.findElements(By.id("compraTituloPeli"));
        String[] tituloPeliTodo = titulosPelisCompras.get(0).getText().split(" ");
        String tituloPeli = tituloPeliTodo[tituloPeliTodo.length - 1];

        // Realizamos las comprobaciones oportunas
        assertEquals(idCompra, idPrimeraCompra);
        assertEquals(tituloPeli, tituloPelicula);

    }

    @Test
    public void testDeliverTickets() {
        // Loguearse como ticketseller
        login(validTicketseller, validPassword);

        // Acceder al formulario para entregar entradas
        driver.findElement(By.id("deliverTicketsLink")).click();

        // Introducir identificador de compra y tarjeta de crédito válidos
        WebElement purchaseId = driver.findElement(By.id("purchaseId"));
        WebElement creditCard = driver.findElement(By.id("numTarjeta"));

        purchaseId.sendKeys("3");
        creditCard.sendKeys(validCreditCard);

        // Enviar formulario
        WebElement sumbitButton = driver.findElement(By.id("deliverTicketsSubmit"));
        sumbitButton.click();

        // Comprobar mensaje de éxito
        WebElement successDisplay = driver.findElement(By.id("deliverTicketsSuccess"));
        assertTrue(successDisplay.isDisplayed());
        assertTrue(successDisplay.getText().contains(
                "Entradas entregadas correctamente. Identificador de la compra 3"
        ));

        // Enviar de nuevo el formulario
        purchaseId.clear();
        creditCard.clear();
        purchaseId.sendKeys("3");
        creditCard.sendKeys(validCreditCard);
        sumbitButton.click();

        // Comprobar mensaje de error
        WebElement errorDisplay = driver.findElement(By.id("errorDisplay"));
        assertTrue(errorDisplay.isDisplayed());
        assertTrue(errorDisplay.getText().contains(
                "Los tickets asociados a esta compra ya fueron entregados"
        ));
    }
}