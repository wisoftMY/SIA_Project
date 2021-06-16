package io.sia.aoi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AoiNotFountException extends RuntimeException {

    @Getter
    final String information;
}
