package grupo_nueve.buscahipotecas.Bancos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import grupo_nueve.buscahipotecas.Hipotecas.Hipoteca;

@Data // Anotation Lombok que añade getters y setters.
@Builder
@AllArgsConstructor // Anotation Lombok que añade constructor.
@NoArgsConstructor // No deja recibir parámetros.
@Entity
public class Banco {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_banco;

    @Column(nullable = false)
    String nombre_banco;

    // Time status.
    @Column(nullable = false)
    LocalDateTime create_at;

    @Column(nullable = false)
    LocalDateTime updated_at;

    @Column(nullable = true)
    LocalDateTime deleted_at;

    @PrePersist
    public void prePersist() {
        this.create_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    } // Se ejecuta antes de persistir el objeto en la base de datos. Se le asigna la fecha y hora actual a create_at.


    // Relaciones:
    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hipoteca> hipotecas = Collections.synchronizedList(new ArrayList<>());

    // Helper
    // Método helper para agregar hipoteca
    public void addHipoteca(Hipoteca hipoteca) {
        hipotecas.add(hipoteca);
        hipoteca.setBanco(this);
    }
}

