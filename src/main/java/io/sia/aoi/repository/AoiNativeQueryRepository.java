package io.sia.aoi.repository;

import io.sia.aoi.domain.Aoi;

import java.util.List;

public interface AoiNativeQueryRepository {

    List<Aoi> getIntersectsRegionByRegionId(Long id);
}
