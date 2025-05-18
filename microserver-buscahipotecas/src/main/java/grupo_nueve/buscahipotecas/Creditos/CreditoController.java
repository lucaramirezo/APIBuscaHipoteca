package grupo_nueve.buscahipotecas.Creditos;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/buscahipotecas/v1/credito")
@RequiredArgsConstructor // Inyeccion de dependencia.
public class CreditoController {
    
    private final CreditoService CreditoService; // Inyeccion de dependencia.

    // @PostMapping("/create/{id_usuario}")
    // public void create(@PathVariable int id_usuario, @RequestBody Credito credito)
    // {
    //     CreditoService.create(id_usuario, credito);
    // }

    @GetMapping("/all")
    public List<Credito> all()
    {
        return CreditoService.getAll();
    }

    @GetMapping("/all/{id_usuario}")
    public List<Credito> allByUsuario(@PathVariable int id_usuario)
    {
        return CreditoService.getAllByIdUsuario(id_usuario);
    }

    @PostMapping("/update/{id_credito}")
    public ResponseEntity<Credito> updateHipoteca(@PathVariable int id_credito, @RequestBody Credito Credito) {
        if (id_credito <= 0) {
            throw new IllegalArgumentException("Invalid id_hipoteca");
        }
        Credito updatedCredito = CreditoService.update(id_credito, Credito);
        return ResponseEntity.ok(updatedCredito);
    }

    @DeleteMapping("/delete/{id_Credito}")
    public ResponseEntity<String> deleteCredito(@PathVariable int id_credito) {
        if (id_credito <= 0) {
            throw new IllegalArgumentException("Invalid id_Credito");
        }
        CreditoService.delete(id_credito);
        return ResponseEntity.ok("Credito deleted successfully");
    }
}
