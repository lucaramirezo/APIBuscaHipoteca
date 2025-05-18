package grupo_nueve.buscahipotecas.Creditos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditoRepository extends JpaRepository <Credito, Integer> {

    @Query("SELECT c FROM Credito c WHERE c.usuario.id_usuario = :idUsuario")
    List<Credito> findCreditosByUsuario(@Param("idUsuario") Integer idUsuario);
    
}
