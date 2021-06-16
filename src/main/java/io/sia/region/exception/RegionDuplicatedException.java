package io.sia.region.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RegionDuplicatedException extends RuntimeException {

    @Getter
    final String information;
}
