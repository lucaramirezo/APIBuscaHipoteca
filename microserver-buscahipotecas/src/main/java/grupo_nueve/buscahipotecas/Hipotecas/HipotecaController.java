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

    @GetMapping("/get_info_from_magi_loop")
    public ResponseEntity<String> get_info_from_magi_loop()
    {
        hipotecaService.getInfoFromMagiLoop();
        return ResponseEntity.ok("Th database has filled with data from the Magi loop.");
    }

    
}
