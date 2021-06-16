package io.sia.region.service.dto;

import io.sia.common.dto.AreaDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RegionDto {

    private RegionDto() {
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegionRegisterRequest {

        @NotBlank
        private String name;

        @NotNull
        private List<AreaDto> area;

    }

    @Getter
    public static class RegionRegisterResponse {

        private final Long id;

        public RegionRegisterResponse(final Long id) {
            this.id = id;
        }
    }

    @Getter
    @Setter
    public static class RegionResponse {

        private Long id;
        private String name;
        private AreaDto area;
        private UUID deviceId;
        private UUID modelId;
        private LocalDateTime joined;
        private LocalDateTime updated;
        private String description;

    }
}
