package com.denghb.simplex.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * redis prefix
 */
@Getter
@AllArgsConstructor
public enum RedisKey {

    RATE,
    RESUBMIT;

}
