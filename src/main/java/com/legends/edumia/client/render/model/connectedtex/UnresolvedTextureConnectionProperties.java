package com.legends.edumia.client.render.model.connectedtex;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.legends.edumia.client.render.model.BlockModelUtil;
import com.legends.edumia.utils.EdumiaLog;
import net.minecraft.util.GsonHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class UnresolvedTextureConnectionProperties {
    public final String textureName;
    public final Optional<Map<ConnectedTextureElement, String>> elementPathOverrides;
    public final boolean includeBaseElement;
    public final boolean makeFromSingleIcon;

    public UnresolvedTextureConnectionProperties(String textureName, Optional<Map<ConnectedTextureElement, String>> elementPathOverrides, boolean includeBaseElement, boolean makeFromSingleIcon) {
        this.textureName = textureName;
        this.elementPathOverrides = elementPathOverrides;
        this.includeBaseElement = includeBaseElement;
        this.makeFromSingleIcon = makeFromSingleIcon;
    }



    public static UnresolvedTextureConnectionProperties read(String textureName, JsonObject json) {
        Optional elementPathOverrides;
        if (json.has("connection_elements")) {
            Map<ConnectedTextureElement, String> elementsMap = new HashMap();
            JsonObject elementsObj = GsonHelper.getAsJsonObject(json, "connection_elements");
            Iterator var5 = elementsObj.entrySet().iterator();

            while(var5.hasNext()) {
                Map.Entry<String, JsonElement> entry = (Map.Entry)var5.next();
                String elementName = (String)entry.getKey();
                ConnectedTextureElement element = ConnectedTextureElement.getNonBaseElementByName(elementName);
                if (element != null) {
                    String overrideString = ((JsonElement)entry.getValue()).getAsString();
                    if (BlockModelUtil.validateTextureString(overrideString)) {
                        elementsMap.put(element, overrideString);
                    } else {
                        EdumiaLog.error("Error loading TextureConnectionProperties for texture '%s' - override texture '%s' is not a valid texture path or #reference", new Object[]{textureName, overrideString});
                    }
                } else {
                    EdumiaLog.error("Error loading TextureConnectionProperties for connected texture '%s' - no connected texture element by name '%s'", new Object[]{textureName, elementName});
                }
            }

            elementPathOverrides = Optional.of(elementsMap);
        } else {
            elementPathOverrides = Optional.empty();
        }

        boolean includeBaseElement = GsonHelper.getAsBoolean(json, "include_base", true);
        boolean makeFromSingleIcon = GsonHelper.getAsBoolean(json, "make_from_single_icon", false);
        return new UnresolvedTextureConnectionProperties(textureName, elementPathOverrides, includeBaseElement, makeFromSingleIcon);
    }
}
