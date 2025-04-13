package com.legends.edumia.client.render.model;


import com.legends.edumia.utils.EdumiaLog;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class DynamicTextureResourcePack implements PackResources {

    private final String namespace;

    private final Map<ResourceLocation, Supplier<InputStream>> inputStreams = new HashMap<>();
    public final ResourceLocation packIsLoadedMarkerResource;

    private final Map<ResourceLocation, ResourceLocation> textureSetsLoadedMarkers = new HashMap<>();

    public DynamicTextureResourcePack(PackType type, String s){
        this.namespace = s;
        this.packIsLoadedMarkerResource = ResourceLocation.fromNamespaceAndPath(this.namespace, "dynamic_tex_virtual_resource_pack_is_loaded_marker");
    }

    public void addDynamicTexture(ResourceLocation baseSetPath, ResourceLocation dynamicFullPath, DynamicTexture tex) {
        NativeImage image = tex.getPixels();
        Supplier<InputStream> sup = () -> {
            try {
                return new ByteArrayInputStream(image.asByteArray());
            } catch (Exception var3) {
                EdumiaLog.error("Failed to setup dynamic texture resource: %s", new Object[]{dynamicFullPath});
                var3.printStackTrace();
                return new ByteArrayInputStream(new byte[0]);
            }
        };
        this.inputStreams.put(dynamicFullPath, sup);
        if (!this.textureSetsLoadedMarkers.containsKey(baseSetPath)) {
            this.textureSetsLoadedMarkers.put(baseSetPath, createDynamicTextureSetIsLoadedMarker(baseSetPath));
        }

    }

    public static ResourceLocation createDynamicTextureSetIsLoadedMarker(ResourceLocation baseSetPath) {
        return ResourceLocation.fromNamespaceAndPath(baseSetPath.getNamespace(), baseSetPath.getPath() + "set_loaded_marker");
    }
    @Nullable
    @Override
    public IoSupplier<InputStream> getRootResource(String... p_252049_) {
        return null;
    }

    @Nullable
    @Override
    public IoSupplier<InputStream> getResource(PackType p_215339_, ResourceLocation p_249034_) {
        return null;
    }

    @Override
    public void listResources(PackType p_10289_, String p_251379_, String p_251932_, ResourceOutput p_249347_) {

    }

    @Override
    public Set<String> getNamespaces(PackType p_10283_) {
        return null;
    }

    @Nullable
    @Override
    public <T> T getMetadataSection(MetadataSectionSerializer<T> p_10291_) throws IOException {
        return null;
    }

    @Override
    public PackLocationInfo location() {
        return null;
    }

    @Override
    public String packId() {
        return null;
    }

    @Override
    public void close() {

    }
}
