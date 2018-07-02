package com.cage.library.infrastructure.math;

import java.math.BigDecimal;

/**
 * Created by luyunfeng on 17/3/22.
 */

public class BDUtils {

    public static BigDecimal getBigDecimal(double value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal getBigDecimal(Object value) {
        try {
            if (value instanceof BigDecimal) {
                return (BigDecimal) value;
            }
            return new BigDecimal(value.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }

    public static BigDecimal subtract(Object minuend, Object subtrahend) {
        return getBigDecimal(minuend).subtract(getBigDecimal(subtrahend));
    }

    public static BigDecimal add(Object number, Object augend) {
        return getBigDecimal(number).add(getBigDecimal(augend));
    }

    public static BigDecimal add(Object number, Object augend, int scale) {
        return getBigDecimal(number).add(getBigDecimal(augend)).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal divide(Object number, Object divisor) {
        return getBigDecimal(number).divide(getBigDecimal(divisor), 2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal multiply(Object number, Object multiplicand) {
        return multiply(number, multiplicand, 2);
    }

    public static BigDecimal multiply(Object number, Object multiplicand, int scale) {
        return getBigDecimal(number).multiply(getBigDecimal(multiplicand)).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }
}
