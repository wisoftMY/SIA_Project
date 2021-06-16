package io.sia.aoi.controller;

import io.sia.aoi.service.AoiServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static io.sia.aoi.service.dto.AoiDto.*;

@RestController
@RequiredArgsConstructor
public class AoiApiController {

    private final AoiServiceImpl aoiServiceImpl;

    @PostMapping("/aois")
    public AoiRegisterResponse register(final @RequestBody @Valid AoiRegisterRequest aoiDTO) {
        final Long id = aoiServiceImpl.register(aoiDTO);

        return new AoiRegisterResponse(id);
    }

    @SneakyThrows
    @GetMapping("/regions/{region-id}/aois/intersects")
    public Result<List<intersectsAoiResponse>> getIntersectsRegionByRegionId(
            @PathVariable("region-id") Long id) {
        final List<intersectsAoiResponse> intersectsAoiResponse = aoiServiceImpl.getIntersectsAoiByRegionId(id);
        return new Result<>(intersectsAoiResponse);
    }

}
