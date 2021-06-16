package io.sia.aoi.repository;

import io.sia.aoi.domain.Aoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AoiRepository extends JpaRepository<Aoi, Long> {

}
