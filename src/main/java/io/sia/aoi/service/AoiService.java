package io.sia.aoi.service;

import org.locationtech.jts.io.ParseException;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.sia.aoi.service.dto.AoiDto.*;

@Component
public interface AoiService {

    Long register(final AoiRegisterRequest aoiDto);

    List<intersectsAoiResponse> getIntersectsAoiByRegionId(final Long id) throws ParseException;

    InterestAoisResponse getInterestNearbyAreaByLatWithLong(final float latitude, final float longitude);
}
