package grupo_nueve.buscahipotecas.Creditos;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class CreditoService {

    // Inyeccion de dependencia.
    private final CreditoRepository creditoRepository;

    public List<Credito> getAll() {
        return creditoRepository.findAll();
    }

    public List<Credito> getAllByIdUsuario(int id_usuario) {
        return creditoRepository.findCreditosByUsuario(id_usuario);
    }

    public Credito update(int id_credito, Credito credito) {

        Credito creditoToUpdate = creditoRepository.findById(id_credito).orElseThrow();

        if (creditoToUpdate == null) {
            throw new IllegalArgumentException("Credito not found");
        }

        creditoToUpdate.setCantidad(credito.getCantidad());

        return creditoRepository.save(creditoToUpdate);
    }


    public void delete(int credito_id) {
        Credito credito = creditoRepository.findById(credito_id).orElseThrow();
        creditoRepository.delete(credito);
    }
}
