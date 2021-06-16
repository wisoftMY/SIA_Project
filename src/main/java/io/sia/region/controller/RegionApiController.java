package io.sia.region.controller;

import io.sia.region.service.RegionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
