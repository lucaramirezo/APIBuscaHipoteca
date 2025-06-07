package grupo_nueve.buscahipotecas.Creditos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import grupo_nueve.buscahipotecas.Usuarios.Usuario;

@Data // Anotation Lombok que añade getters y setters.
@Builder
@AllArgsConstructor // Anotation Lombok que añade constructor.
@NoArgsConstructor // No deja recibir parámetros.
@Entity
public class Credito {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_credito;

    // @Column(nullable = false, insertable=false, updatable=false)
    // int id_usuario;

    @Column(nullable = false)
    String cantidad;

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

    // Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnore // Esto evita que se serialice el usuario en los creditos.
    private Usuario usuario;
}
