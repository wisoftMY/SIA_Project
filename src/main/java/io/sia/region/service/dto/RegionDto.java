package io.sia.region.service.dto;

import io.sia.common.Area;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class RegionDto {

    private RegionDto() {
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegionRegisterRequest {

        @NotBlank
        private String name;

        @NotBlank
        private List<Area> area;

    }

    @Getter
    public static class RegionRegisterResponse {

        private final Long id;

        public RegionRegisterResponse(final Long id) {
            this.id = id;
        }
    }
}
