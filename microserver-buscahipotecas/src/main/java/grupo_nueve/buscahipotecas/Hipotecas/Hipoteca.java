package grupo_nueve.buscahipotecas.Hipotecas;

import java.time.LocalDateTime;
import java.util.Set;

import grupo_nueve.buscahipotecas.Usuarios.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Anotation Lombok que añade getters y setters.
@Builder
@AllArgsConstructor // Anotation Lombok que añade constructor.
@NoArgsConstructor // No deja recibir parámetros.
@Entity
public class Hipoteca {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_hipoteca;

    @Column(nullable = false)
    String nombre_hipoteca;

    @Column(nullable = false)
    Double importe_inicial;

    @Column(nullable = false)
    Double mensualidad;

    @Column(nullable = true)
    String tipo_hipoteca;

    @Column(nullable = false)
    String finalidad;

    @Column(nullable = false)
    int id_entidad;

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
    
    
    // Realtions
    @ManyToMany(mappedBy = "hipotecas")
    private Set<Usuario> usuarios;


}

