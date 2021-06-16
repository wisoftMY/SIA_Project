package io.sia.aoi.service.dto;

import io.sia.common.Area;
import io.sia.common.dto.AreaDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AoiDto {

    private AoiDto() {
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AoiRegisterRequest {

        @NotBlank
        private String name;

        @NotNull
        private List<AreaDto> area;

    }

    @Getter
    public static class AoiRegisterResponse {

        private final Long id;

        public AoiRegisterResponse(Long id) {
            this.id = id;
        }
    }

    @Getter
    public static class intersectsAoiResponse {
        private final Long id;
        private final String name;
        private final List<Area> area;

        public intersectsAoiResponse(Long id, String name, List<Area> area) {
            this.id = id;
            this.name = name;
            this.area = area;
        }
    }

    @Data
    @AllArgsConstructor
    public static class Result<T> {
        private T aois;
    }

}
