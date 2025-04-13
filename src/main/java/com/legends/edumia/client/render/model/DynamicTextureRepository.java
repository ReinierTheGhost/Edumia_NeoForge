package com.legends.edumia.client.render.model;

import com.legends.edumia.client.render.model.connectedtex.ConnectedTextureElement;
import com.legends.edumia.client.render.model.connectedtex.TextureConnectionProperties;
import com.legends.edumia.utils.EdumiaLog;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class DynamicTextureRepository {
    public static final DynamicTextureRepository INSTANCE = new DynamicTextureRepository();
    private final Map<String, DynamicTextureResourcePack> virtualPacks = new HashMap<>();
    private final Map<ResourceLocation, List<ResourceLocation>> cachedConnectedLocations = new HashMap<>();



    private DynamicTextureRepository(){

    }

    private static ResourceLocation getConnectedTextureLocation(ResourceLocation base, Set<ConnectedTextureElement> elements){
        int key = ConnectedTextureElement.getIconSetKey(elements);
        return ResourceLocation.fromNamespaceAndPath(base.getNamespace(), base.getPath() + "_" + key);
    }

    public List<ResourceLocation> generateAllConnectedTextures(TextureConnectionProperties textureProperties){
        Minecraft mc =  Minecraft.getInstance();
        ResourceManager resMgr = mc.getResourceManager();
        ResourceLocation base = textureProperties.getBaseTextureName();
        boolean includeBase = textureProperties.includeBaseElement();
        String namespace = base.getNamespace();
        ResourceLocation cacheKeyLocation = textureProperties.getCanonicalCacheKey();
        if (this.cachedConnectedLocations.containsKey(cacheKeyLocation)){
            boolean checkResourceExists = resMgr.getResource(DynamicTextureResourcePack.createDynamicTextureSetIsLoadedMarker(cacheKeyLocation)).isPresent();
            if (checkResourceExists){
                return this.cachedConnectedLocations.get(cacheKeyLocation);
            }
        }

        List<ResourceLocation> allList = new ArrayList<>();

        try {
            ResourceLocation baseTextureFullPath = convertTextureFullPath(base);
            Resource resource = resMgr.getResourceOrThrow(baseTextureFullPath);
            NativeImage baseImage;
            try(InputStream inputStream = resource.open()) {
                baseImage = NativeImage.read(inputStream);
            }
            int iconWidth = baseImage.getWidth();
            int iconHeight = baseImage.getHeight();
            Map<ConnectedTextureElement, NativeImage> elementImages = new HashMap();
            ConnectedTextureElement[] var14 = ConnectedTextureElement.values();
            int var15 = var14.length;

            NativeImage connectedImage;
            for(int var16 = 0; var16 < var15; ++var16) {
                ConnectedTextureElement elem = var14[var16];
                if (elem == ConnectedTextureElement.BASE) {
                    if (includeBase) {
                        elementImages.put(elem, baseImage);
                    }
                } else if (textureProperties.makeFromSingleIcon()) {
                    elementImages.put(elem, this.makePartFromSingleIcon(baseImage, elem));
                } else {
                    Optional<ResourceLocation> optElementPath = textureProperties.getElementIconPath(elem);
                    if (optElementPath.isPresent()) {
                        ResourceLocation elementPath = convertTextureFullPath((ResourceLocation)optElementPath.get());
                        Resource resource1 = resMgr.getResourceOrThrow(elementPath);
                        try(InputStream inputStream = resource1.open()) {
                            connectedImage = NativeImage.read(inputStream);
                        }
                        if (connectedImage.getWidth() == iconWidth && connectedImage.getHeight() == iconHeight) {
                            elementImages.put(elem, connectedImage);
                        } else {
                            EdumiaLog.error("All connected texture icons for %s must have the same dimensions!", new Object[]{base});
                            EdumiaLog.error("%s: base icon is %dx%d, but %s icon is %dx%d", new Object[]{base, iconWidth, iconHeight, elem.elementName, connectedImage.getWidth(), connectedImage.getHeight()});
                            elementImages.put(elem, createErroredImage(iconWidth, iconHeight));
                        }
                    }
                }
            }

            Map<Integer, Set<ConnectedTextureElement>> permutationSet = includeBase ? ConnectedTextureElement.ALL_COMBINATIONS_WITH_BASE : ConnectedTextureElement.ALL_COMBINATIONS_WITHOUT_BASE;
            Iterator var31 = permutationSet.entrySet().iterator();

            label84:
            while(var31.hasNext()) {
                Map.Entry<Integer, Set<ConnectedTextureElement>> entry = (Map.Entry)var31.next();
                int key = (Integer)entry.getKey();
                Set<ConnectedTextureElement> elementSet = (Set)entry.getValue();
                List<ConnectedTextureElement> sortedList = ConnectedTextureElement.sortIconSet(elementSet);
                connectedImage = new NativeImage(baseImage.format(), iconWidth, iconHeight, true);
                if (includeBase) {
                    connectedImage.copyFrom(baseImage);
                }

                Iterator var21 = sortedList.iterator();

                while(true) {
                    NativeImage elementImage;
                    do {
                        ConnectedTextureElement elem;
                        do {
                            if (!var21.hasNext()) {
                                DynamicTexture dynamic = new DynamicTexture(connectedImage);
                                ResourceLocation connectedRes = getConnectedTextureLocation(base, elementSet);
                                ResourceLocation connectedFullPath = convertTextureFullPath(connectedRes);
                                mc.getTextureManager().register(connectedFullPath, dynamic);
                                this.getVirtualPack(namespace).addDynamicTexture(cacheKeyLocation, connectedFullPath, dynamic);
                                allList.add(connectedRes);
                                continue label84;
                            }

                            elem = (ConnectedTextureElement)var21.next();
                        } while(elem == ConnectedTextureElement.BASE);

                        elementImage = (NativeImage)elementImages.get(elem);
                    } while(elementImage == null);

                    for(int x = 0; x < connectedImage.getWidth(); ++x) {
                        for(int y = 0; y < connectedImage.getHeight(); ++y) {
                            int rgba = elementImage.getPixelRGBA(x, y);
                            int alpha = rgba >> 24 & 255;
                            if (alpha != 0) {
                                connectedImage.setPixelRGBA(x, y, rgba);
                            }
                        }
                    }
                }
            }
        } catch (IOException var28) {
            EdumiaLog.error("Error generating connected textures for %s", new Object[]{cacheKeyLocation});
            var28.printStackTrace();
        }

        this.cachedConnectedLocations.put(cacheKeyLocation, allList);

        return allList;
    }

    private NativeImage makePartFromSingleIcon(NativeImage baseImage, ConnectedTextureElement elem) {
        int sideWidth = 3;
        int sideHeight = 3;
        int fullWidth = 16;
        int fullHeight = 16;
        switch (elem) {
            case SIDE_LEFT:
                return this.copyAreaFromIcon(baseImage, 0.0, 0.0, 3.0, 16.0);
            case SIDE_RIGHT:
                return this.copyAreaFromIcon(baseImage, 13.0, 0.0, 16.0, 16.0);
            case SIDE_TOP:
                return this.copyAreaFromIcon(baseImage, 0.0, 0.0, 16.0, 3.0);
            case SIDE_BOTTOM:
                return this.copyAreaFromIcon(baseImage, 0.0, 13.0, 16.0, 16.0);
            case CORNER_TOPLEFT:
            case INVCORNER_TOPLEFT:
                return this.copyAreaFromIcon(baseImage, 0.0, 0.0, 3.0, 3.0);
            case CORNER_TOPRIGHT:
            case INVCORNER_TOPRIGHT:
                return this.copyAreaFromIcon(baseImage, 13.0, 0.0, 16.0, 3.0);
            case CORNER_BOTTOMLEFT:
            case INVCORNER_BOTTOMLEFT:
                return this.copyAreaFromIcon(baseImage, 0.0, 13.0, 3.0, 16.0);
            case CORNER_BOTTOMRIGHT:
            case INVCORNER_BOTTOMRIGHT:
                return this.copyAreaFromIcon(baseImage, 13.0, 13.0, 3.0, 16.0);
            default:
                throw new IllegalArgumentException("Unknown connected texture element " + elem.toString() + "!");
        }
    }

    private NativeImage copyAreaFromIcon(NativeImage baseImage, double x0, double y0, double x1, double y1) {
        int iconWidth = baseImage.getWidth();
        int iconHeight = baseImage.getHeight();
        NativeImage elementImage = new NativeImage(baseImage.format(), iconWidth, iconHeight, true);
        int x0I = (int)Math.round(x0 / 16.0D * iconWidth);
        int x1I = (int)Math.round(x1 / 16.0D * iconWidth);
        int y0I = (int)Math.round(y0 / 16.0D * iconHeight);
        int y1I = (int)Math.round(y1 / 16.0D * iconHeight);
        for (int y = y0I; y < y1I; y++) {
            for (int x = x0I; x < x1I; x++)
                elementImage.setPixelRGBA(x, y, baseImage.getPixelRGBA(x, y));
        }
        return elementImage;
    }

//    private static ResourceLocation getFilledVasselLocation(ResourceLocation)

    private static ResourceLocation convertTextureFullPath(ResourceLocation texture){
        return ResourceLocation.fromNamespaceAndPath(texture.getNamespace(), String.format("textures/%s.png", texture.getPath()));
    }

    private static NativeImage createErroredImage(int width, int height){
        NativeImage errored = new NativeImage(width, height, true);

        for (int x = 0; x < errored.getWidth(); ++x){
            for (int y = 0; y < errored.getHeight(); ++y){
                int rgb = 0;
                if ((x + y) % 2 == 0){
                    rgb = 16711680;
                }else {
                    rgb = 0;
                }
                errored.setPixelRGBA(x, y, -1677716 | rgb);
            }
        }
        return errored;
    }

    private DynamicTextureResourcePack getVirtualPack(String nameSpace){
        Minecraft mc = Minecraft.getInstance();
        MultiPackResourceManager resMgr = (MultiPackResourceManager) mc.getResourceManager();
        List<PackResources> resources = new ArrayList<>();
        DynamicTextureResourcePack pack = this.virtualPacks.get(nameSpace);
        if (pack == null || pack != null && !resMgr.getResource(pack.packIsLoadedMarkerResource).isPresent()){
            pack = new DynamicTextureResourcePack(PackType.CLIENT_RESOURCES, nameSpace);
            this.virtualPacks.put(nameSpace, pack);
            resources.add(pack);
        }
        return pack;
    }


}
