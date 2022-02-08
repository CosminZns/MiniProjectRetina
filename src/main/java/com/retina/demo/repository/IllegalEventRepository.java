package com.retina.demo.repository;

import com.retina.demo.model.IllegalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IllegalEventRepository extends JpaRepository<IllegalEvent, Long> {

    Optional<IllegalEvent> findByEventId(Long id);
}
