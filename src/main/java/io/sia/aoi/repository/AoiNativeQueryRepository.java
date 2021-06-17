package io.sia.aoi.repository;

import io.sia.aoi.domain.Aoi;

import java.util.List;

public interface AoiNativeQueryRepository {

    List<Aoi> getIntersectsRegionByRegionId(final Long id);

    Aoi getInterestNearbyAreaByLatWithLong(final float latitude, final float longitude);
}
