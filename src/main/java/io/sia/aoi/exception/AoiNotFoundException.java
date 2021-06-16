package io.sia.aoi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@AllArgsConstructor
public class AoiNotFoundException extends RuntimeException {

    @Getter
    final String information;

}
