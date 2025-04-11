package com.legends.edumia.items;


import net.minecraft.world.food.FoodProperties;

public class EdumiaFoodComponent {
    public static final FoodProperties TOMATO = new FoodProperties.Builder().nutrition(2).build();
    public static final FoodProperties CHEESE = new FoodProperties.Builder().nutrition(4).build();
    public static final FoodProperties RICE_BALL = new FoodProperties.Builder().nutrition(4).build();
    public static final FoodProperties RED_GRAPES = new FoodProperties.Builder().nutrition(2).build();
    public static final FoodProperties LETTUCE = new FoodProperties.Builder().nutrition(3).build();
    public static final FoodProperties RAMEN_VEGI = new FoodProperties.Builder().nutrition(8).build();
    public static final FoodProperties RAMEN_SHRIMPS = new FoodProperties.Builder().nutrition(9).build();
    public static final FoodProperties RAMEN_PORK = new FoodProperties.Builder().nutrition(10).build();
    public static final FoodProperties RAMEN_BEEF = new FoodProperties.Builder().nutrition(10).build();
    public static final FoodProperties RAMEN = new FoodProperties.Builder().nutrition(8).build();
    public static final FoodProperties PAPRIKA = new FoodProperties.Builder().nutrition(3).build();
    public static final FoodProperties BROCCOLI = new FoodProperties.Builder().nutrition(3).build();
    public static final FoodProperties BANANA = new FoodProperties.Builder().nutrition(2).saturationModifier(2f).build();
    public static final FoodProperties BANANA_BREAD = new FoodProperties.Builder().nutrition(5).saturationModifier(6f).build();
    public static final FoodProperties MANGO = new FoodProperties.Builder().nutrition(4).saturationModifier(2.4F).build();
    public static final FoodProperties DATE = new FoodProperties.Builder().nutrition(2).saturationModifier(1.2f).build();
}
