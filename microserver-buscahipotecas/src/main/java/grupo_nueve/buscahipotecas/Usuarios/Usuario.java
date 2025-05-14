package grupo_nueve.buscahipotecas.Usuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import grupo_nueve.buscahipotecas.Creditos.Credito;
import grupo_nueve.buscahipotecas.Hipotecas.Hipoteca;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
// @Table(name="usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})});
public class Usuario implements UserDetails {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)

    String password;

    Role role;

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

    @ManyToMany
    @JoinTable(
        name = "usuario_hipoteca", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "id_usuario"), // Columna para la clave foránea de Estudiante
        inverseJoinColumns = @JoinColumn(name = "ih_hipoteca") // Columna para la clave foránea de Curso
    )
    @Builder.Default
    private Set<Hipoteca> hipotecas = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }
    
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }

    // @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "usuario")
    private List<Credito> creditos = new ArrayList<>();


}
