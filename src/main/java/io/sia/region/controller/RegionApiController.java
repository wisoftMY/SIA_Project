package io.sia.region.controller;

import com.vividsolutions.jts.io.ParseException;
import io.sia.aoi.domain.Aoi;
import io.sia.region.service.RegionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static io.sia.region.service.dto.RegionDto.*;

@RestController
@RequiredArgsConstructor
public class RegionApiController {

    private final RegionServiceImpl regionServiceImpl;

    @PostMapping("/regions")
    public RegionRegisterResponse register(final @RequestBody @Valid RegionRegisterRequest regionDTO) {
        final Long id = regionServiceImpl.register(regionDTO);

        return new RegionRegisterResponse(id);
    }

}
