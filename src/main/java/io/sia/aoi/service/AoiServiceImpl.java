package io.sia.aoi.service;

import io.sia.aoi.domain.Aoi;
import io.sia.aoi.repository.AoiNativeQueryRepository;
import io.sia.aoi.repository.AoiRepository;
import io.sia.common.Area;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.sia.aoi.service.dto.AoiDto.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AoiServiceImpl implements AoiService {

    private final AoiRepository aoiRepository;
    private final AoiNativeQueryRepository aoiNativeQueryRepository;

    @Transactional
    public Long register(final AoiRegisterRequest aoiDto) {
        Polygon polygon = Area.createPolygon(aoiDto.getArea());
        Aoi aoi = Aoi.createAoi(aoiDto, polygon);
        aoiRepository.save(aoi);

        return aoi.getId();
    }

    @Transactional(readOnly = true)
    public List<intersectsAoiResponse> getIntersectsAoiByRegionId(final Long id) throws ParseException {
        final List<Aoi> aois = aoiNativeQueryRepository.getIntersectsRegionByRegionId(id);
        final List<List<Area>> areasList = Area.createAreaFromPolygon(aois.stream().map(Aoi::getArea).collect(Collectors.toList()));
        final List<intersectsAoiResponse> intersectsAoiResponseList = Aoi.mergeAoisWithAreas(aois, areasList);

        return intersectsAoiResponseList;
    }

}
