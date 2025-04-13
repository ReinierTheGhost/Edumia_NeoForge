package com.legends.edumia.client.render.model.connectedtex;

import net.minecraft.resources.ResourceLocation;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextureConnectionProperties {
    private final ResourceLocation textureName;
    private final Optional<Map<ConnectedTextureElement, ResourceLocation>> elementPathOverrides;
    private final boolean includeBaseElement;
    private final boolean makeFromSingleIcon;

    public TextureConnectionProperties(ResourceLocation textureName, Optional<Map<ConnectedTextureElement, ResourceLocation>> elementPathOverrides, boolean includeBaseElement, boolean makeFromSingleIcon) {
        this.textureName = textureName;
        this.elementPathOverrides = elementPathOverrides;
        this.includeBaseElement = includeBaseElement;
        this.makeFromSingleIcon = makeFromSingleIcon;
    }

    public static TextureConnectionProperties defaultProps(ResourceLocation textureName){
        return new TextureConnectionProperties(textureName, Optional.empty(), true, false);
    }

    public static TextureConnectionProperties resolveFrom(UnresolvedTextureConnectionProperties unresolved, Function<String, ResourceLocation> textureResolver){
        return new TextureConnectionProperties(textureResolver.apply(unresolved.textureName), resolveUnresolvedElementPathOverrides(unresolved.elementPathOverrides, textureResolver), unresolved.includeBaseElement, unresolved.makeFromSingleIcon);
    }

    private static Optional<Map<ConnectedTextureElement, ResourceLocation>> resolveUnresolvedElementPathOverrides(Optional<Map<ConnectedTextureElement, String>> unresolvedOpt, Function<String, ResourceLocation> textureResolver) {
        if (unresolvedOpt.isPresent()){
            Map<ConnectedTextureElement, String> unresolvedMap = unresolvedOpt.get();
            Map<ConnectedTextureElement, ResourceLocation> resolvedMap = unresolvedMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, (e) ->
                    textureResolver.apply(e.getValue())));
            return Optional.of(resolvedMap);
        } else {
            return Optional.empty();
        }
    }

    public ResourceLocation getBaseTextureName() {
        return textureName;
    }

    public Optional<ResourceLocation> getElementIconPath(ConnectedTextureElement element){
        if (element == ConnectedTextureElement.BASE){
            throw new IllegalArgumentException("This method should not bbe used to determine the base icon - this is a development error");
        } else {
            return this.elementPathOverrides.isPresent() ? Optional.ofNullable((this.elementPathOverrides.get()).get(element)) :
                    Optional.of(ResourceLocation.parse(this.textureName + element.getDefaultIconSuffix()));
        }
    }

    public boolean includeBaseElement() {
        return this.includeBaseElement;
    }

    public boolean makeFromSingleIcon() {
        return this.makeFromSingleIcon;
    }

    public ResourceLocation getCanonicalCacheKey() {
        return ResourceLocation.fromNamespaceAndPath(this.textureName.getNamespace(), String.format("%s.connectedpropertiesoverrides_%sincludebaseelement%s__makefromsingleicon%s", this.textureName.getPath(), this.getCanonicalFormForElementPathOverrides(), this.includeBaseElement, this.makeFromSingleIcon));
    }

    private String getCanonicalFormForElementPathOverrides() {
        if (this.elementPathOverrides.isPresent()) {
            Map<ConnectedTextureElement, ResourceLocation> map = this.elementPathOverrides.get();
            List<ConnectedTextureElement> sortedKeys = new ArrayList<>(map.keySet());
            Collections.sort(sortedKeys, Comparator.comparingInt((elem) -> {
                return elem.ordinal();
            }));
            return sortedKeys.stream().map((elem) -> {
                String elemName = elem.elementName;
                String pathOverride = ((ResourceLocation)map.get(elem)).toString().replace(':', '.');
                return elemName + "" + pathOverride;
            }).collect(Collectors.joining(""));
        } else {
            return "none";
        }
    }
}
