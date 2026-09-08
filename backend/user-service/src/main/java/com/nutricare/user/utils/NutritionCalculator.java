package com.nutricare.user.utils;

import com.nutricare.user.model.ActivityLevel;
import com.nutricare.user.model.Goal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class NutritionCalculator {
    public NutritionCalculator() {
    }

    public static BigDecimal calculateBmi(BigDecimal weight, BigDecimal height) {
        BigDecimal heightSquare = height.multiply(height);
        return weight.divide(heightSquare, 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateBmr(BigDecimal weight, BigDecimal height, Integer age, Boolean gender) {
        BigDecimal bmr = weight.multiply(BigDecimal.TEN)
                .add(height.multiply(new BigDecimal("6.25")))
                .subtract(BigDecimal.valueOf(age).multiply(new BigDecimal("5")));

        if (gender) {
            bmr = bmr.add(BigDecimal.valueOf(5));
        } else
            bmr = bmr.subtract(BigDecimal.valueOf(161));

        return bmr;
    }

    public static BigDecimal calculateTdee(BigDecimal bmr, ActivityLevel activityLevel) {
        return bmr.multiply(BigDecimal.valueOf(activityLevel.getFactor()));
    }

    public static BigDecimal calculateCalorieTarget(BigDecimal tdee, Goal goal, BigDecimal adjustment) {
        return switch (goal) {
            case LOSE -> tdee.subtract(adjustment);
            case GAIN -> tdee.add(adjustment);
            case MAINTAIN -> tdee;
        };
    }

    public static BigDecimal calculateProtein(BigDecimal calorieTarget) {
        BigDecimal minEnergy = BigDecimal.valueOf(0.1);
        BigDecimal maxEnergy = BigDecimal.valueOf(0.35);
        BigDecimal proteinKcal = calorieTarget.multiply(maxEnergy.add(minEnergy).divide(BigDecimal.valueOf(2)));

        return proteinKcal.divide(BigDecimal.valueOf(4));
    }

    public static BigDecimal calculateFat(BigDecimal calorieTarget) {
        BigDecimal minEnergy = BigDecimal.valueOf(0.2);
        BigDecimal maxEnergy = BigDecimal.valueOf(0.35);
        BigDecimal fatKcal = calorieTarget.multiply(maxEnergy.add(minEnergy).divide(BigDecimal.valueOf(2)));

        return fatKcal.divide(BigDecimal.valueOf(9), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateCarbon(BigDecimal calorieTarget, BigDecimal protein, BigDecimal fat) {
        BigDecimal proteinKcal = protein.multiply(BigDecimal.valueOf(4));
        BigDecimal fatKcal = fat.multiply(BigDecimal.valueOf(9));
        BigDecimal carbonKcal = calorieTarget.subtract(proteinKcal).subtract(fatKcal);

        return carbonKcal.divide(BigDecimal.valueOf(4));
    }

    public static BigDecimal calculateFiber(BigDecimal calorieTarget) {
        return calorieTarget.divide(BigDecimal.valueOf(1000)).multiply(BigDecimal.valueOf(14));
    }

    public static BigDecimal calculateWater(Boolean gender) {
        if (gender) return BigDecimal.valueOf(2.5);
        else return BigDecimal.valueOf(2.0);
    }

    public static BigDecimal calculateSodium(Boolean gender) {
        return BigDecimal.valueOf(2);
    }
}
