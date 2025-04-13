package com.legends.edumia.client.render.model.connectedtex;

import com.google.common.collect.Sets;
import com.legends.edumia.client.render.model.DynamicTextureRepository;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Transformation;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.IModelBuilder;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.SimpleUnbakedGeometry;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConnectedTextureUnbakedModel extends SimpleUnbakedGeometry<ConnectedTextureUnbakedModel> {

    private final List<BlockElement> elements;
    private final Map<String, UnresolvedTextureConnectionProperties> textureConnectionProperties;
    private final List<ConnectedTexture3DContext.PositionOfInterest> itemConnectionContextPoint;
    private final ConnectedTexture3DContext.BlockConnectionType connectionType;

    public ConnectedTextureUnbakedModel(List<BlockElement> elements, Map<String, UnresolvedTextureConnectionProperties> textureConnectionProperties, List<ConnectedTexture3DContext.PositionOfInterest> itemContext, ConnectedTexture3DContext.BlockConnectionType cType) {
        this.elements = elements;
        this.textureConnectionProperties = textureConnectionProperties;
        this.itemConnectionContextPoint = itemContext;
        this.connectionType = cType;
    }

    private Map<ResourceLocation, TextureConnectionProperties> getResolvedTextureConnectionProperties(IGeometryBakingContext owner){
        Function<String, ResourceLocation> textureResolver = (s) ->
                owner.getMaterial(s).texture();
        return this.textureConnectionProperties.entrySet().stream().collect(Collectors.toMap((e) ->
                textureResolver.apply(e.getKey()),
                (e) ->
                TextureConnectionProperties.resolveFrom(e.getValue(), textureResolver)));
    }

    @Override
    protected void addQuads(IGeometryBakingContext iGeometryBakingContext, IModelBuilder<?> iModelBuilder, ModelBaker modelBaker, Function<Material, TextureAtlasSprite> function, ModelState modelState) {

    }
    public Collection<Material> getTextures(IGeometryBakingContext owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors){
        Set<Material> textures = Sets.newHashSet();
        Iterator var5 = this.elements.iterator();

        Iterator var7;
        while (var5.hasNext()) {
            BlockElement part = (BlockElement) var5.next();

            Material texture;
            for (var7 = part.faces.values().iterator(); var7.hasNext(); textures.add(texture)){
                BlockElementFace face = (BlockElementFace) var7.next();
                texture = owner.getMaterial(face.texture());
                if (Objects.equals(texture.texture().toString(), MissingTextureAtlasSprite.getLocation().toString())){
                    missingTextureErrors.add(Pair.of(face.texture(), owner.getModelName()));
                }
            }
        }

        Map<ResourceLocation, TextureConnectionProperties> resolvedConnectionProps = this.getResolvedTextureConnectionProperties(owner);
        Set<Material> allConnectedTextures = Sets.newHashSet();

        var7 = textures.iterator();

        while (var7.hasNext()){
            Material texture = (Material)var7.next();
            ResourceLocation textureName = texture.texture();
            if (!Objects.equals(textureName.toString(), MissingTextureAtlasSprite.getLocation().toString())){
                TextureConnectionProperties connectionProperties = resolvedConnectionProps.getOrDefault(textureName, TextureConnectionProperties.defaultProps(textureName));
                List<ResourceLocation> connected = DynamicTextureRepository.INSTANCE.generateAllConnectedTextures(connectionProperties);
                connected.forEach((connectedRes) -> {
                Material connectedMaterial = new Material(texture.atlasLocation(), connectedRes);
                allConnectedTextures.add(connectedMaterial);
                });
            }
        }
        textures.addAll(allConnectedTextures);
        return textures;
    }

    @Override
    public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter,
                           ModelState modelState, ItemOverrides overrides) {
        TextureAtlasSprite particle = (TextureAtlasSprite) spriteGetter.apply(context.getMaterial("particle"));
        Builder builder = (new Builder(context, overrides)).setParticle(particle);
        
        return builder.build();
    }



    public static final class Builder implements IModelBuilder {

        private final ItemOverrides builderItemOverrideList;
        private final boolean builderAmbientOcclusion;
        private TextureAtlasSprite particleTexture;
        private final boolean isSideLight;
        private final boolean builderGui3d;
        private final ItemTransforms builderCamaraTransforms;

        private DeferredConnectedTextureModelBakery deferredConnectedModelBakery;
        private Transformation blockstateRotation;
        private ConnectedTexture3DContext itemContext;
        private ConnectedTexture3DContext.BlockConnectionType connectionType;
        public Builder(IGeometryBakingContext model, ItemOverrides overrides) {
            this(model.useAmbientOcclusion(), model.useBlockLight(), model.isGui3d(), model.getTransforms(), overrides);
        }
        public Builder(BlockModel model, ItemOverrides overrides, boolean g3d){
            this(model.hasAmbientOcclusion(), model.getGuiLight().lightLikeBlock(), g3d, model.getTransforms(), overrides);
        }
        private Builder(boolean ambOcc, boolean sideLight, boolean g3d, ItemTransforms transforms, ItemOverrides overrides) {
            this.builderItemOverrideList = overrides;
            this.builderAmbientOcclusion = ambOcc;
            this.isSideLight = sideLight;
            this.builderGui3d = g3d;
            this.builderCamaraTransforms = transforms;
        }

        public void setDeferredConnectedModelBakery(DeferredConnectedTextureModelBakery bakery) {
            this.deferredConnectedModelBakery = bakery;
        }

        public void setBlockstateRotation(Transformation rotation) {
            this.blockstateRotation = rotation;
        }

        @Override
        public Builder addCulledFace(@NotNull Direction facing, @NotNull BakedQuad quad) {
            throw new UnsupportedOperationException("Add them through the BlockModelQuadsHolder map instead");
        }

        @Override
        public Builder addUnculledFace( @NotNull BakedQuad quad) {
            throw new UnsupportedOperationException("Add them through the BlockModelQuadsHolder map instead");
        }

        public Builder setParticle(TextureAtlasSprite texture) {
            this.particleTexture = texture;
            return this;
        }

        public Builder createItemConnectedContext(List<ConnectedTexture3DContext.PositionOfInterest> points){
            if (points == null){
                this.itemContext = ConnectedTexture3DContext.newEmptyContent();
            }else {
                this.itemContext = ConnectedTexture3DContext.newContextFrom(points);
            }
            return this;
        }


        public Builder setConnectionType(ConnectedTexture3DContext.BlockConnectionType type) {
            if (type == null){
                this.connectionType = ConnectedTexture3DContext.BlockConnectionType.SAME_BLOCK;
            }else {
                this.connectionType = type;
            }

            return this;
        }

        @Override
        public BakedModel build() {
            if (this.particleTexture == null){
                throw new RuntimeException("Missing particle!");
            }else {
                return new ConnectedTextureBlockModel(this.builderAmbientOcclusion, this.isSideLight, this.builderGui3d, this.particleTexture,
                        this.builderCamaraTransforms, this.builderItemOverrideList, this.deferredConnectedModelBakery, this.blockstateRotation,
                        this.itemContext, this.connectionType);
            }
        }
    }

    public static class DeferredConnectedTextureModelBakery{

    }
}
