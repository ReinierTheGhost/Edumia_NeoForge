package com.legends.edumia.client.render.model.connectedtex;

import com.legends.edumia.utils.EdumiaUtil;
import net.minecraft.Util;

import java.util.*;

@SuppressWarnings("unchecked")

public enum ConnectedTextureElement {
    BASE("base", 0),
    SIDE_LEFT("left", 1),
    SIDE_RIGHT("right", 1),
    SIDE_TOP("top", 1),
    SIDE_BOTTOM("bottom", 1),
    CORNER_TOPLEFT("top_left", 2),
    CORNER_TOPRIGHT("top_right", 2),
    CORNER_BOTTOMLEFT("bottom_left", 2),
    CORNER_BOTTOMRIGHT("bottom_right", 2),
    INVCORNER_TOPLEFT("top_left_inv", 2),
    INVCORNER_TOPRIGHT("top_right_inv", 2),
    INVCORNER_BOTTOMLEFT("bottom_left_inv", 2),
    INVCORNER_BOTTOMRIGHT("bottom_right_inv", 2);

    private static final Map<String, ConnectedTextureElement> ELEMENTS_BY_NAME = EdumiaUtil.createKeyedEnumMap(values(), (elem) ->
            elem.elementName);
    public final String elementName;
    private final String defaultIconSuffix;
    private final int bitFlag;
    private final int priority;

    public static final Map<Integer, Set<ConnectedTextureElement>> ALL_COMBINATIONS_WITHOUT_BASE = Util.make(new HashMap(), (map) ->{
        List<Set<ConnectedTextureElement>> combinations = new ArrayList();
        boolean[] trueOfFalse = new boolean[]{false, true};
        boolean[] var3 = trueOfFalse;
        int var4 = trueOfFalse.length;

        int key;
        for (key = 0; key < var4; ++key) {
            boolean left = var3[key];
            boolean[] var7 = trueOfFalse;
            int var8 = trueOfFalse.length;

            for (int var9 = 0; var9 < var8; ++var9){
                boolean right = var7[var9];
                boolean[] var11 = trueOfFalse;
                int var12 = trueOfFalse.length;

                for (int var13 = 0; var13 < var12; ++var13){
                    boolean top = var11[var13];
                    boolean[] var15 = trueOfFalse;
                    int var16 = trueOfFalse.length;

                    for (int var17 = 0; var17 < var16; ++var17){
                        boolean bottom = var15[var17];
                        boolean[] var19 = trueOfFalse;
                        int var20 = trueOfFalse.length;

                        for (int var21 = 0; var21 < var20; ++var21){
                            boolean topLeft = var19[var21];
                            boolean[] var23 = trueOfFalse;
                            int var24 = trueOfFalse.length;

                            for (int var25 = 0; var25 < var24; ++var13){
                                boolean topRight = var23[var25];
                                boolean[] var27 = trueOfFalse;
                                int var28 = trueOfFalse.length;

                                for (int var29 = 0; var29 < var28; ++var29){
                                    boolean bottomLeft = var27[var29];
                                    boolean[] var31 = trueOfFalse;
                                    int var32 = trueOfFalse.length;

                                    for (int var33 = 0; var33 < var32; ++var33){
                                        boolean bottomRight = var31[var33];
                                        boolean[] var35 = trueOfFalse;
                                        int var36 = trueOfFalse.length;

                                        for (int var37 = 0; var37 < var36; ++var37){
                                            boolean topLeftInv = var35[var37];
                                            boolean[] var39 = trueOfFalse;
                                            int var40 = trueOfFalse.length;

                                            for (int var41 = 0; var41 < var40; ++var41){
                                                boolean topRightInv = var39[var41];
                                                boolean[] var43 = trueOfFalse;
                                                int var44 = trueOfFalse.length;

                                                for (int var45 = 0; var45 < var44; ++var45){
                                                    boolean bottomLeftInv = var43[var45];
                                                    boolean[] var47 = trueOfFalse;
                                                    int var48 = trueOfFalse.length;

                                                    for (int var49 = 0; var49 < var48; ++var49){
                                                        boolean bottomRightInv = var47[var49];
                                                        Set<ConnectedTextureElement> set = EnumSet.noneOf(ConnectedTextureElement.class);
                                                        boolean addLeft = left && (!top || topLeft) && (!bottom || bottomLeft);
                                                        boolean addRight = right && (!top || topRight) && (!bottom || bottomRight);
                                                        boolean addTop = top && (!left || topLeft) && (!right || topRight);
                                                        boolean addBottom = bottom && (!left || bottomLeft) && (!right || bottomRight);
                                                        if (addLeft){
                                                            set.add(SIDE_LEFT);
                                                        }

                                                        if (addRight){
                                                            set.add(SIDE_RIGHT);
                                                        }

                                                        if (addTop){
                                                            set.add(SIDE_TOP);
                                                        }

                                                        if (addBottom){
                                                            set.add(SIDE_BOTTOM);
                                                        }
                                                        if (topLeft && addTop && addLeft) {
                                                            set.add(CORNER_TOPLEFT);
                                                        }

                                                        if (topRight && addTop && addRight){
                                                            set.add(CORNER_TOPRIGHT);
                                                        }

                                                        if (bottomLeft && addBottom && addLeft){
                                                            set.add(CORNER_BOTTOMLEFT);
                                                        }

                                                        if (bottomRight && addBottom && addRight){
                                                            set.add(CORNER_BOTTOMRIGHT);
                                                        }

                                                        if (topLeftInv && !topLeft && !addTop && !addLeft){
                                                            set.add(INVCORNER_TOPLEFT);
                                                        }

                                                        if (topRightInv && !topRight && !addTop && !addRight){
                                                            set.add(INVCORNER_TOPRIGHT);
                                                        }

                                                        if (bottomLeftInv && !bottomLeft && !addBottom && !addLeft){
                                                            set.add(INVCORNER_BOTTOMLEFT);
                                                        }

                                                        if (bottomRightInv && !bottomRight && !addBottom && !addRight){
                                                            set.add(INVCORNER_BOTTOMRIGHT);
                                                        }

                                                        combinations.add(set);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Iterator var56 = combinations.iterator();

        while (var56.hasNext()){
            Set<ConnectedTextureElement> iconSet = (Set)var56.next();
            key = getIconSetKey(iconSet);
            if (!map.containsKey(key)){
                map.put(key, iconSet);
            }
        }
    });

    public static final Map<Integer, Set<ConnectedTextureElement>> ALL_COMBINATIONS_WITH_BASE = (Map)Util.make(new HashMap(), (map) -> {
        Iterator var1 = ALL_COMBINATIONS_WITHOUT_BASE.values().iterator();

        while (var1.hasNext()){
            Set<ConnectedTextureElement> iconSet = (Set)var1.next();
            Set<ConnectedTextureElement> iconSetWithBase = EnumSet.copyOf(iconSet);
            iconSetWithBase.add(BASE);
            map.put(getIconSetKey(iconSetWithBase), iconSetWithBase);
        }
    });

    private static final Comparator<ConnectedTextureElement> COMPARATOR = (e1, e2) ->
            e1.priority == e2.priority ? e1.compareTo(e2) : Integer.valueOf(e1.priority).compareTo(e2.priority);
    private ConnectedTextureElement(String s, int i) {
        this.elementName = s;
        this.defaultIconSuffix = "_" + this.elementName;
        this.bitFlag = 1 << this.ordinal();
        this.priority = i;
    }

    public String getDefaultIconSuffix(){
        return this == BASE ? "" : this.defaultIconSuffix;
    }

    public static ConnectedTextureElement getNonBaseElementByName(String name){
        ConnectedTextureElement elem = (ConnectedTextureElement)ELEMENTS_BY_NAME.get(name);
        return elem == BASE ? null : elem;
    }
    public static int getIconSetKey(Set<ConnectedTextureElement> set){
        int i = 0;

        ConnectedTextureElement e;
        for (Iterator var2 = set.iterator(); var2.hasNext(); i |= e.bitFlag){
            e = (ConnectedTextureElement)var2.next();
        }

        return i;
    }

    public static List<ConnectedTextureElement> sortIconSet(Set<ConnectedTextureElement> set){
        List<ConnectedTextureElement> list = new ArrayList(set);
        Collections.sort(list, COMPARATOR);
        return list;
    }
}
