package tech.ada.tenthirty.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.tenthirty.ecommerce.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
