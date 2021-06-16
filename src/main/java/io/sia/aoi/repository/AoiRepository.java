package io.sia.aoi.repository;

import io.sia.aoi.domain.Aoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AoiRepository extends JpaRepository<Aoi, Long> {

    Optional<Aoi> findByName(final String name);
}
