package grupo_nueve.buscahipotecas.Creditos;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import grupo_nueve.buscahipotecas.Usuarios.Usuario;
import grupo_nueve.buscahipotecas.Usuarios.UsuarioRepository;

@Service
@RequiredArgsConstructor 
public class CreditoService {
    // Inyeccion de dependencia.
    private CreditoRepository creditoRepository;
    private UsuarioRepository usuarioRepository;

    public Credito create(int id_usuario, Credito credito) {
        Usuario usuario = usuarioRepository.findById(id_usuario).orElseThrow();
        credito.setUsuario(usuario);
        return creditoRepository.save(credito);
    }

    public List<Credito> getAll() {
        return creditoRepository.findAll();
    }

    public List<Credito> getAllByIdUsuario(int id_usuario) {
        return creditoRepository.findCreditosByUsuario(id_usuario);
    }

    public Credito update(int credito_id, Credito credito) {
        Credito existingCredito = creditoRepository.findById(credito_id).orElseThrow();
        return creditoRepository.save(existingCredito);
    }


    public void delete(int credito_id) {
        Credito credito = creditoRepository.findById(credito_id).orElseThrow();
        creditoRepository.delete(credito);
    }
}
