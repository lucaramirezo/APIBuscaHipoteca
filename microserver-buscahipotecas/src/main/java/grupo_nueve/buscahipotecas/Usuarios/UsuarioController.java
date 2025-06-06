package grupo_nueve.buscahipotecas.Usuarios;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import grupo_nueve.buscahipotecas.Creditos.Credito;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/buscahipotecas/v1/usuario")
@RequiredArgsConstructor // Inyeccion de dependencia.
public class UsuarioController {

    private final UsuarioService usuarioService; // Inyeccion de dependencia.

    // Create se realiza en el auth.

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> all()
    {
        // return usuarioService.getAll();
        try {
            List<Usuario> usuarios = usuarioService.getAll();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Error al obtener los usuarios", 
                e
            );
        }
    }

    @GetMapping("/get/{id_usuario}")
    public ResponseEntity<Usuario> getUserById(@PathVariable int id_usuario) {
        if (id_usuario <= 0) {
            throw new IllegalArgumentException("Invalid id_usuario");
        }
        // Usuario usuario = usuarioService.getById(id_usuario);
        // return ResponseEntity.ok(usuario);
        try {
            Usuario usuario = usuarioService.getById(id_usuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Error al obtener los usuarios", 
                e
            );
        }
    }

    @PostMapping("/update/{id_usuario}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable int id_usuario, @RequestBody Usuario usuario) {
        if (id_usuario <= 0) {
            throw new IllegalArgumentException("Invalid id_usuario");
        }
        Usuario updatedUsuario = usuarioService.update(id_usuario, usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/delete/{id_usuario}")
    public ResponseEntity<String> deleteUsuario(@PathVariable int id_usuario) {
        if (id_usuario <= 0) {
            throw new IllegalArgumentException("Invalid id_usuario");
        }
        usuarioService.delete(id_usuario);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PostMapping("/setHipotecas/{id_usuario}")
    public ResponseEntity<Usuario> setHipotecas(
        @PathVariable int id_usuario,
        @RequestBody List<Integer> ids_hipotecas
    ) {
        if (id_usuario <= 0) {
            throw new IllegalArgumentException("Invalid id_usuario");
        }

        if (ids_hipotecas == null || ids_hipotecas.isEmpty()) {
            throw new IllegalArgumentException("Invalid ids_hipotecas");
        }
        // ToDo comprobar que ya exista la relaicón entre id_hipoteca e id_usuario.

        Usuario usuario = usuarioService.setHipotecas(id_usuario, ids_hipotecas);
        
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/create_creditos/{id_usuario}")
    public ResponseEntity<Usuario> setCreditos(
        @PathVariable int id_usuario,
        @RequestBody List<Credito> creditos
    ) {
        if (id_usuario <= 0) {
            throw new IllegalArgumentException("Invalid id_usuario");
        }

        if (creditos == null || creditos.isEmpty()) {
            throw new IllegalArgumentException("Invalid ids_creditos");
        }

        Usuario usuario = usuarioService.saveCreditos(id_usuario, creditos);
        
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }
}
