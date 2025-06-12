package grupo_nueve.buscahipotecas.Hipotecas;

import lombok.Data;
import java.util.List;

@Data
public class HipotecaResponse {
    private List<HipotecaDTO> data;
}

