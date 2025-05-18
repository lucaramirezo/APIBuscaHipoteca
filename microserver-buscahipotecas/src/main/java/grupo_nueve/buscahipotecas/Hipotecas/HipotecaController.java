package grupo_nueve.buscahipotecas.Hipotecas;

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
@RequestMapping("/buscahipotecas/v1/hipoteca")
@RequiredArgsConstructor // Inyeccion de dependencia.
public class HipotecaController {

    private final HipotecaService hipotecaService; // Inyeccion de dependencia.

    // @PostMapping("/create")
    // public ResponseEntity<String> createHipoteca(@RequestBody Hipoteca hipoteca)
    // {
    //     try {
    //         Integer id_banco = 1;
    //         // Banco banco = BancoRepository.findByBancoId(id_banco);
    //         //     .orElseThrow(() -> new ResourceNotFoundException("Banco no encontrado"));
    //         // if (hipoteca.id_banco() == null) {
    //         //     return ResponseEntity.badRequest().body("El id_banco es obligatorio");
    //         // }
            
    //         // // Validación adicional si es necesario
    //         // if (hipoteca.getId_banco() <= 0) {
    //         //     return ResponseEntity.badRequest().body("El id_banco debe ser un valor positivo");
    //         // }
    //         hipotecaService.create(hipoteca);

    //         return ResponseEntity.ok("Hipoteca creada con id: " + hipoteca.getId_hipoteca());
    //     } catch (Exception e) {
    //         throw new ResponseStatusException(
    //             HttpStatus.INTERNAL_SERVER_ERROR, 
    //             "Error al obtener los usuarios", 
    //             e
    //         );
    //     }
    // }

    @GetMapping("/all")
    public List<Hipoteca> all()
    {
        return hipotecaService.getAll();
    }

    @GetMapping("/all/{id_usuario}")
    public List<Hipoteca> allByIdUsuario(@PathVariable int id_usuario)
    {
        return hipotecaService.getAllByIdUsuario(id_usuario);
    }

    @PostMapping("/update/{id_hipoteca}")
    public ResponseEntity<Hipoteca> updateHipoteca(@PathVariable int id_hipoteca, @RequestBody Hipoteca hipoteca) {
        if (id_hipoteca <= 0) {
            throw new IllegalArgumentException("Invalid id_hipoteca");
        }
        Hipoteca updatedHipoteca = hipotecaService.update(id_hipoteca, hipoteca);
        return ResponseEntity.ok(updatedHipoteca);
    }

    @DeleteMapping("/delete/{id_hipoteca}")
    public ResponseEntity<String> deleteHipoteca(@PathVariable int id_hipoteca) {
        if (id_hipoteca <= 0) {
            throw new IllegalArgumentException("Invalid id_hipoteca");
        }
        hipotecaService.delete(id_hipoteca);
        return ResponseEntity.ok("Hipoteca deleted successfully");
    }
}
