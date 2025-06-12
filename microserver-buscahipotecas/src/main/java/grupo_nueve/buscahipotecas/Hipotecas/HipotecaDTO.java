package grupo_nueve.buscahipotecas.Hipotecas;

import lombok.Data;

@Data
public class HipotecaDTO {
    private String nombre_hipoteca;
    private Double importe_inicial;
    private Double mensualidad;
    private String tipo_hipoteca;
    private String finalidad;
}

