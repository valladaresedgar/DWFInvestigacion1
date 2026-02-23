package Arquitecturaclean.infrastructure.adapter.output.persistence.repository;

import Arquitecturaclean.infrastructure.adapter.output.persistence.entity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderJpaEntity, String> {
}