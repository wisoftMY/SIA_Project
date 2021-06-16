package io.sia.region.service;

import org.springframework.stereotype.Component;

import static io.sia.region.service.dto.RegionDto.*;

@Component
public interface RegionService {

    Long register(final RegionRegisterRequest regionDto);
}
