package Arquitecturaclean.infrastructure.adapter.output.persistence;

import Arquitecturaclean.application.port.output.OrderRepository;
import Arquitecturaclean.domain.entity.Order;
import Arquitecturaclean.domain.vo.OrderId;
import Arquitecturaclean.infrastructure.adapter.output.persistence.entity.OrderJpaEntity;
import Arquitecturaclean.infrastructure.adapter.output.persistence.mapper.OrderPersistenceMapper;
import Arquitecturaclean.infrastructure.adapter.output.persistence.repository.JpaOrderRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class OrderPersistenceAdapter implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;
    private final OrderPersistenceMapper mapper;

    public OrderPersistenceAdapter(JpaOrderRepository jpaOrderRepository,
                                   OrderPersistenceMapper mapper) {
        this.jpaOrderRepository = jpaOrderRepository;
        this.mapper = mapper;
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity entity = mapper.toJpaEntity(order);
        if (entity.getItems() != null) {
            entity.getItems().forEach(item -> item.setOrder(entity));
        }
        OrderJpaEntity saved = jpaOrderRepository.save(entity);
        return mapper.toDomainEntity(saved);
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return jpaOrderRepository.findById(id.getValue())
                .map(mapper::toDomainEntity);
    }
}