package io.sia.region.service;

import com.vividsolutions.jts.io.ParseException;
import io.sia.aoi.domain.Aoi;
import io.sia.region.domain.Region;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.sia.region.service.dto.RegionDto.*;

@Component
public interface RegionService {

    Long register(final RegionRegisterRequest regionDto);
}
