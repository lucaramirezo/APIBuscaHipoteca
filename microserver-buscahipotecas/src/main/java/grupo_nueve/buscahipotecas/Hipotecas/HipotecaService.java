package grupo_nueve.buscahipotecas.Hipotecas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.web.reactive.function.client.WebClient;

import grupo_nueve.buscahipotecas.Bancos.Banco;
import grupo_nueve.buscahipotecas.Bancos.BancoRepository;

import org.springframework.http.MediaType;

@Service
@RequiredArgsConstructor // Inyeccion de dependencia.
public class HipotecaService {

    private final HipotecaRepository hipotecaRepository; // Inyeccion de dependencia.
    private final WebClient.Builder webClientBuilder; // Inyeccion de dependencia para realizar peticiones HTTP.
    private final BancoRepository bancoRepository;

    public void create(Hipoteca hipoteca) {
        hipotecaRepository.save(hipoteca);
    }

    public List<Hipoteca> getAll() {
        return hipotecaRepository.findAll();
    }

    public List<Hipoteca> getAllByIdUsuario(int id_usuario) {
        return hipotecaRepository.findByIdUsuario(id_usuario);
    }

    public List<Hipoteca> getAllByIdBanco(int id_banco) {
        return hipotecaRepository.findByBancoId(id_banco);
    }

    public Hipoteca update(int id_hipoteca, Hipoteca hipoteca) {
        if (id_hipoteca <= 0 || hipoteca == null) {
            throw new IllegalArgumentException("Invalid id_hipoteca");
        }
        Hipoteca hipotecaToUpdate = hipotecaRepository.findById(id_hipoteca).orElse(null);
        if (hipotecaToUpdate == null) {
            throw new IllegalArgumentException("hipoteca not found");
        }
        hipotecaToUpdate.setNombre_hipoteca(hipoteca.getNombre_hipoteca());
        hipotecaToUpdate.setImporte_inicial(hipoteca.getImporte_inicial());
        hipotecaToUpdate.setMensualidad(hipoteca.getMensualidad());
        hipotecaToUpdate.setTipo_hipoteca(hipoteca.getTipo_hipoteca());
        hipotecaToUpdate.setFinalidad(hipoteca.getFinalidad());

        return hipotecaRepository.save(hipotecaToUpdate);
    }

    public void delete(int id_hipoteca) {
        if (id_hipoteca <= 0) {
            throw new IllegalArgumentException("Invalid id_hipoteca");
        }
        Hipoteca hipotecaToDelete = hipotecaRepository.findById(id_hipoteca).orElse(null);
        if (hipotecaToDelete == null) {
            throw new IllegalArgumentException("hipoteca not found");
        }
        hipotecaRepository.delete(hipotecaToDelete);
    }


    public void getInfoFromMagiLoop() {
        WebClient webClient = webClientBuilder.baseUrl("https://magicloops.dev").build();

        HipotecaResponse response = webClient.post()
                .uri("/api/loop/5aea7ecc-e8ef-45f3-861d-6877106adc6e/run")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("page", 1))
                .retrieve()
                .bodyToMono(HipotecaResponse.class)
                .block();

        if (response != null && response.getData() != null) {
            response.getData().forEach(dto -> {
                Hipoteca hipoteca = new Hipoteca();
                hipoteca.setNombre_hipoteca(dto.getNombre_hipoteca());
                hipoteca.setImporte_inicial(dto.getImporte_inicial());
                hipoteca.setMensualidad(dto.getMensualidad());
                hipoteca.setTipo_hipoteca(dto.getTipo_hipoteca());
                hipoteca.setFinalidad(dto.getFinalidad());
                hipoteca.setCreate_at(LocalDateTime.now());
                hipoteca.setUpdated_at(LocalDateTime.now());

                // List<Hipoteca> hipotecas = List.of(hipoteca);
                List<Hipoteca> hipotecas = new ArrayList<>();
                hipotecas.add(hipoteca);
                
                // ⚠️ Asignar banco si es requerido y hay lógica
                // hipoteca.setBanco(bancoRepository.findById(1).orElseThrow());

                //hipotecaRepository.save(hipoteca);
                int id_banco = 1;
                Banco banco = bancoRepository.findById(id_banco).orElse(null);
        
                if (banco == null) {
                    throw new IllegalArgumentException("Banco not found");
                }
                
                hipotecas.forEach(banco::addHipoteca);

                bancoRepository.save(banco);
            });
        }

    }

}
