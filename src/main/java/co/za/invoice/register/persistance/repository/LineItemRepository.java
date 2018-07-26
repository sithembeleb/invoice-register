package co.za.invoice.register.persistance.repository;

import co.za.invoice.register.persistance.entity.LineItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends JpaRepository<LineItemEntity, Long> {
}
