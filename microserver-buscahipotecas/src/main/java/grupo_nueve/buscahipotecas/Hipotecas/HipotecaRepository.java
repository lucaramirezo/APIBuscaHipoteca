package grupo_nueve.buscahipotecas.Hipotecas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HipotecaRepository extends JpaRepository <Hipoteca, Integer> {

    @Query("SELECT h FROM Hipoteca h LEFT JOIN h.usuarios u WHERE u.id_usuario = :id_usuario")
    List<Hipoteca> findByIdUsuario(@Param("id_usuario") Integer id_usuario);

    
    @Query("SELECT h FROM Hipoteca h WHERE h.banco.id_banco = :id_banco")
    List<Hipoteca> findByBancoId(@Param("id_banco") Integer id_banco);
    // List<Hipoteca> findByBancoId(Integer idBanco);  // Spring lo resuelve automáticamente
    
}
