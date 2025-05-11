package grupo_nueve.buscahipotecas.Hipotecas;

import java.util.List;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HipotecaRepository extends JpaRepository <Hipoteca, Integer> {

    @Query("SELECT h FROM Hipoteca h LEFT JOIN h.usuarios u WHERE u.id_usuario = :id_usuario")
    List<Hipoteca> findByIdUsuario(@Param("id_usuario") Integer id_usuario);
    
}
