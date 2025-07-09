package com.legends.edumia.resources.forge;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public enum MetalTypes implements StringRepresentable {
    EMPTY(-1, "empty", null, null, false, 0),

    COPPER(0, "copper", Items.COPPER_INGOT, null, true, 11823181),

    IRON(2, "iron", Items.IRON_INGOT, Items.IRON_NUGGET, true, 15527148),
    GOLD(3, "gold", Items.GOLD_INGOT, Items.GOLD_NUGGET, true, 14594349),

    NETHERITE(4, "netherite", Items.NETHERITE_INGOT, null, true, 6445145),
    ;

    private final int id;
    private final String name;
    private final Item ingot;
    private final Item nugget;
    private final boolean vanilla;
    private final int color;

    MetalTypes(int id, String name, Item ingot, Item nugget, boolean vanilla, int color){
        this.id = id;
        this.name = name;
        this.ingot = ingot;
        this.nugget = nugget;
        this.vanilla = vanilla;
        this.color = color;
    }

    public boolean isVanilla() {
        return vanilla;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Item getIngot() {
        return ingot;
    }

    public Item getNugget() {
        return nugget;
    }

    public static MetalTypes getValue(int value) {
        for(MetalTypes e: MetalTypes.values()) {
            if(e.id == value) {
                return e;
            }
        }
        return null;
    }

    public static MetalTypes getMetalByIngot(Item ingot) {
        for(MetalTypes e: MetalTypes.values()) {
            if(e.getIngot() == ingot) {
                return e;
            }
        }
        return null;
    }

    public static MetalTypes getMetalByNugget(Item nugget) {
        for(MetalTypes e: MetalTypes.values()) {
            if(e.getNugget() == nugget) {
                return e;
            }
        }
        return null;
    }
}
