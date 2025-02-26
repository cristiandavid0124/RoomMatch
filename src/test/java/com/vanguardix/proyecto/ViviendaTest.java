package com.vanguardix.proyecto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.vanguardix.proyecto.model.User;
import com.vanguardix.proyecto.model.Vivienda;

public class ViviendaTest {

    @Test
    public void testConstructorAndGetters() {
        User testUser = new User(1, "TestUser", "test@example.com", "password");
        Vivienda vivienda = new Vivienda(1, "Apartamento Test", "Calle Falsa 123", testUser, true);
        assertEquals(1, vivienda.getId());
        assertEquals("Apartamento Test", vivienda.getName());
        assertEquals("Calle Falsa 123", vivienda.getDireccion());
        assertEquals(testUser, vivienda.getUser());
        assertTrue(vivienda.isDisponible());
    }
    
    @Test
    public void testSetters() {
        User initialUser = new User(1, "UsuarioInicial", "inicial@example.com", "password");
        Vivienda vivienda = new Vivienda(1, "NombreInicial", "DireccionInicial", initialUser, true);
        vivienda.setId(2);
        vivienda.setName("NombreModificado");
        vivienda.setDireccion("DireccionModificada");
        User newUser = new User(2, "NuevoUsuario", "nuevo@example.com", "newpassword");
        vivienda.setUser(newUser);
        vivienda.setDisponible(false);
        assertEquals(2, vivienda.getId());
        assertEquals("NombreModificado", vivienda.getName());
        assertEquals("DireccionModificada", vivienda.getDireccion());
        assertEquals(newUser, vivienda.getUser());
        assertFalse(vivienda.isDisponible());
    }
    
    @Test
    public void testToString() {
        User testUser = new User(1, "TestUser", "test@example.com", "password");
        Vivienda vivienda = new Vivienda(3, "CasaTest", "Avenida Siempre Viva 742", testUser, false);
        String resultadoToString = vivienda.toString();
        assertTrue(resultadoToString.contains("id=3"));
        assertTrue(resultadoToString.contains("name='CasaTest'"));
        assertTrue(resultadoToString.contains("direccion='Avenida Siempre Viva 742'"));
        assertTrue(resultadoToString.contains("disponible=false"));
        assertTrue(resultadoToString.contains(testUser.toString()));
    }
}
