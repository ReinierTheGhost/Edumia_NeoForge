package com.legends.edumia.client;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;

import java.util.List;
import java.util.stream.Collectors;

public class EdumiaClientUtil {

    /**
     * to make the tooltip lines fit on the screen
     * @param lines
     * @param font
     * @param stringWidth
     * @return
     */
    public static List<? extends FormattedCharSequence> trimEachLineToWidth(List<FormattedText> lines, Font font, int stringWidth) {
        return lines.stream().flatMap((line) -> {
            return font.split(line, stringWidth).stream();
        }).collect(Collectors.toList());
    }

    public static int getAlphaInt(float alphaF){
        int alphaI = (int)(alphaF * 255.0f);
        return Mth.clamp(alphaI, 0, 255);
    }
    public static int getAlphaIntForFontRendering(float alphaF){
        int alphaI = getAlphaInt(alphaF);
        return Math.max(alphaI, 4);
    }
    public static int getRBEForFontRendering(int rgb, float alphaF){
        return rgb | getAlphaIntForFontRendering(alphaF) << 24;
    }

}
