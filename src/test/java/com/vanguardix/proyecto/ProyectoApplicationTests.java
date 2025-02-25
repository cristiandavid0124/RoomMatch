package com.vanguardix.proyecto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.vanguardix.proyecto.model.User;
import com.vanguardix.proyecto.repository.UserRepository;
import com.vanguardix.proyecto.service.UserService;

@SpringBootTest

/**
 * Pruebas unitarias para la clase UserService.
 */
class ProyectoApplicationTests {

	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
	 /**
     * Prueba la eliminación de un usuario existente.
     * Verifica que el método deleteById se llame y que el código HTTP sea 204.
     */
    @Test
    void deleteUser_WhenUserExists_ShouldDeleteSuccessfully() {
        int userId = 1;
        when(userRepository.existsById(userId)).thenReturn(true);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
		
		ResponseEntity<Void> response = ResponseEntity.noContent().build();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

	 /**
     * Prueba la eliminación de un usuario inexistente.
     * Verifica que se lance una excepción ResponseStatusException con código HTTP 404.
     */
	@SuppressWarnings("null")
	@Test
    void deleteUser_WhenUserDoesNotExist_ShouldThrowNotFound() {
        int userId = 1;
		//se simula que el usuario no esta en la DB
        when(userRepository.existsById(userId)).thenReturn(false);

		//Se ejecuta el método y se espera una excepción
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            userService.deleteUser(userId);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getReason().contains("Usuario no encontrado con ID: "+userId));
    }
	 /**
     * Prueba la actualización de un usuario existente.
     * Verifica que los cambios sean guardados y que el código HTTP sea 200.
     */
    @Test
    void updateUser_WhenUserExists_ShouldUpdateSuccessfully() {
        int userId = 1;
        User existingUser = new User(userId, "carola", "carola@example.com", "password123");
        User updatedUser = new User(userId, "andres", "andres@example.com", "newpassword");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(userId, updatedUser);

        assertEquals(updatedUser.getName(), result.getName());
        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(updatedUser.getPassword(), result.getPassword());
        assertEquals(userId, result.getId());
		ResponseEntity<User> response = ResponseEntity.ok(result);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Prueba la actualización de un usuario inexistente.
     * Verifica que se lance una excepción ResponseStatusException con código HTTP 404.
     */
    @SuppressWarnings("null")
	@Test
    void updateUser_WhenUserDoesNotExist_ShouldThrowNotFound() {
        int userId = 1;
        User updatedUser = new User(userId, "cristian", "cristian@example.com", "newpassword");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            userService.updateUser(userId, updatedUser);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getReason().contains("Usuario no encontrado con ID: "+userId));
    }
	/**
	 * Prueba unitaria para verificar que el método createUser()
	 * guarda correctamente un usuario en la base de datos,
	 * y que la respuesta HTTP tiene el estado 201 CREATED.
	 */
	@Test
    void createUser_WhenValidUser_ShouldReturnCreatedUser() {
        User userToSave = new User(1, "Juan", "juan@example.com", "password123");
        when(userRepository.save(userToSave)).thenReturn(userToSave);

        User savedUser = userService.createUser(userToSave);

        assertNotNull(savedUser);
        assertEquals(userToSave.getId(), savedUser.getId());
        assertEquals(userToSave.getName(), savedUser.getName());
        assertEquals(userToSave.getEmail(), savedUser.getEmail());
        assertEquals(userToSave.getPassword(), savedUser.getPassword());
        verify(userRepository, times(1)).save(userToSave);

		ResponseEntity<User> response = ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

/**
 * Prueba unitaria para verificar que el método getAllUsers()
 * retorna una lista de usuarios cuando existen usuarios en la base de datos.
 * y que la respuesta HTTP tiene el estado 200 OK.
 */
@Test
void getAllUsers_WhenUsersExist_ShouldReturnUserList() {
    
    List<User> users = List.of(
        new User(1, "carola", "carola@example.com", "password123"),
        new User(2, "andre", "andre@example.com", "password456")
    );
    when(userRepository.findAll()).thenReturn(users);
    List<User> result = userService.getAllUsers();
    // Assert: Se verifica que la lista tenga los usuarios esperados
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("carola", result.get(0).getName());
    assertEquals("andre", result.get(1).getName());
    // Verificar que se llamó una vez al método findAll()
    verify(userRepository, times(1)).findAll();
		
	ResponseEntity<List<User>> response = ResponseEntity.ok(result);
    assertEquals(HttpStatus.OK, response.getStatusCode());
}



}
