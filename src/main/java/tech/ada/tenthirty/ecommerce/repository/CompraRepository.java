package tech.ada.tenthirty.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.tenthirty.ecommerce.model.Compra;

import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {
     Optional<Compra> findByIdentificador(String identificador);
}
