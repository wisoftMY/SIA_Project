package io.sia.aoi.repository;

import io.sia.aoi.domain.Aoi;

import java.util.List;
import java.util.Optional;

public interface AoiNativeQueryRepository {

    List<Aoi> getIntersectsRegionByRegionId(final Long id);

    Aoi getInterestNearbyAreaByLatWithLong(final float latitude, final float longitude);
}
