package io.sia.region.service;

import io.sia.aoi.domain.Aoi;
import io.sia.common.Area;
import io.sia.region.domain.Region;
import io.sia.aoi.repository.AoiNativeQueryRepository;
import io.sia.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.sia.region.service.dto.RegionDto.RegionRegisterRequest;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final AoiNativeQueryRepository aoiNativeQueryRepository;

    @Transactional
    public Long register(final RegionRegisterRequest regionDto) {
        Polygon polygon = Area.createPolygon(regionDto.getArea());
        Region region = Region.createRegion(regionDto, polygon);
        regionRepository.save(region);

        return region.getId();
    }
}
