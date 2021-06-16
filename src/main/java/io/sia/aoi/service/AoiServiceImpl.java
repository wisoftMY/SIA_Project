package io.sia.aoi.service;

import io.sia.aoi.domain.Aoi;
import io.sia.aoi.exception.AoiDuplicatedException;
import io.sia.aoi.exception.AoiNotFountException;
import io.sia.aoi.repository.AoiNativeQueryRepository;
import io.sia.aoi.repository.AoiRepository;
import io.sia.common.Area;
import io.sia.region.exception.RegionDuplicatedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static io.sia.aoi.service.dto.AoiDto.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AoiServiceImpl implements AoiService {

    private final AoiRepository aoiRepository;
    private final AoiNativeQueryRepository aoiNativeQueryRepository;

    @Transactional
    public Long register(final AoiRegisterRequest aoiDto) {
        checkDuplicateName(aoiDto.getName());
        Polygon polygon = Area.createPolygon(aoiDto.getArea());
        Aoi aoi = Aoi.createAoi(aoiDto, polygon);
        aoiRepository.save(aoi);

        return aoi.getId();
    }

    @Transactional(readOnly = true)
    public List<intersectsAoiResponse> getIntersectsAoiByRegionId(final Long id) throws ParseException {
        final List<Aoi> aois = aoiNativeQueryRepository.getIntersectsRegionByRegionId(id);
        if (aois == null || aois.isEmpty()) {
            log.error("AoiService-getIntersectsAoiByRegionId: Not Found Exception: {}", id);
            throw new AoiNotFountException(String.valueOf(id));
        }
        final List<List<Area>> areasList = Area.createAreaFromPolygon(aois.stream().map(Aoi::getArea).collect(Collectors.toList()));
        final List<intersectsAoiResponse> intersectsAoiResponseList = Aoi.mergeAoisWithAreas(aois, areasList);

        return intersectsAoiResponseList;
    }

    private void checkDuplicateName(final String name) {
        aoiRepository.findByName(name).ifPresent(region -> {
            log.error("Aoi Duplicated Name Exception: {}", name);
            throw new AoiDuplicatedException(name);
        });
    }

}
