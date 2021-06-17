package io.sia.region.service;

import io.sia.common.Area;
import io.sia.region.domain.Region;
import io.sia.region.exception.RegionDuplicatedException;
import io.sia.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.sia.region.service.dto.RegionDto.RegionRegisterRequest;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Transactional
    public Long register(final RegionRegisterRequest regionDto) {
        checkDuplicateName(regionDto.getName());
        Polygon polygon = Area.createPolygon(regionDto.getArea());
        Region region = Region.createRegion(regionDto.getName(), polygon);

        final Region findRegion = regionRepository.save(region);

        return findRegion.getId();
    }

    private void checkDuplicateName(final String name) {
        regionRepository.findByName(name).ifPresent(region -> {
            log.error("Region Duplicated Name Exception: {}", name);

            throw new RegionDuplicatedException(name);
        });
    }
}
