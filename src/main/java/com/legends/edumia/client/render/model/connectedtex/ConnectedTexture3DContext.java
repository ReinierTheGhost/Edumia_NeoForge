package com.legends.edumia.client.render.model.connectedtex;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.math.Transformation;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConnectedTexture3DContext {

    private static final Map<Integer, ConnectedTexture3DContext> ALL_RELEVANT_3D_CONTEXTS = Util.make(new HashMap(), (map) ->{
        int maxCombinedBits = (1 << PositionOfInterest.values().length) - 1;

        for (int combinedBitsFlag = 0; combinedBitsFlag <= maxCombinedBits; ++combinedBitsFlag){
            ConnectedTexture3DContext ctx = new ConnectedTexture3DContext(combinedBitsFlag);
            if (!ctx.hasIrrelevantPositions()){
                map.put(combinedBitsFlag, ctx);
            }
        }
    });

    private static final Map<Integer, Map<Direction, ConnectedTexture2DContext>> CONTEXT_TO_FACE_2D_CONTEXT_MAP =
            ALL_RELEVANT_3D_CONTEXTS.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, (entry) -> {
                ConnectedTexture3DContext ctx3d = entry.getValue();
                return Stream.of(Direction.values()).collect(Collectors.toMap(UnaryOperator.identity(), (dir) ->
                        ConnectedTextureFaceMapper.get2dFrom3d(ctx3d, dir)
                ));
            }));
    private final int combinedPositionBitFlags;

    private ConnectedTexture3DContext(Set<PositionOfInterest> positionOfInterest){
        int combined = 0;

        PositionOfInterest poi;
        for (Iterator var3 = positionOfInterest.iterator(); var3.hasNext(); combined |=poi.bitFlag){
            poi = (PositionOfInterest) var3.next();
        }
        this.combinedPositionBitFlags = combined;
    }
    private ConnectedTexture3DContext(int combinedPositionBitFlags) {
        this.combinedPositionBitFlags = combinedPositionBitFlags;
    }

    public static ConnectedTexture3DContext newEmptyContent(){
        return new ConnectedTexture3DContext(ImmutableSet.of());
    }

    public static ConnectedTexture3DContext newContextFrom(Collection<PositionOfInterest> pois){
        return new ConnectedTexture3DContext(EnumSet.copyOf(pois));
    }


    public static ConnectedTexture3DContext gatherFromWorld(BlockAndTintGetter world, BlockPos pos, BlockState state, Transformation blockstateRotation, BlockConnectionType connectionType) {
        Set<PositionOfInterest> pois = EnumSet.noneOf(PositionOfInterest.class);
        BlockPos.MutableBlockPos movingPos = new BlockPos.MutableBlockPos();
        PositionOfInterest[] var7 = PositionOfInterest.values();
        int var8 = var7.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            PositionOfInterest poi = var7[var9];
            movingPos.set(pos);
            blockstateRotation.getClass();
            List<Direction> poiOffsets = poi.offsets.stream()
                    .map(offset -> blockstateRotation.rotateTransform(offset))
                    .collect(Collectors.toList());
            poiOffsets.forEach(movingPos::move);
            if (connectionType.connects(state, world.getBlockState(movingPos), poiOffsets)) {
                pois.add(poi);
            }
        }

        pruneIrrelevantPositions(pois);
        return new ConnectedTexture3DContext(pois);
    }

    private static void pruneIrrelevantPositions(Set<PositionOfInterest> pois) {
        pois.removeIf((poi) ->{
            boolean var10000;
            if (poi.isCompoundOffset()){
                pois.getClass();
                if (isIrrelevantCompoundOffsetPosition(poi, pois::contains)){
                    var10000 = true;
                    return var10000;
                }
            }

            var10000 = false;
            return var10000;
        });
    }

    private static boolean isIrrelevantCompoundOffsetPosition(PositionOfInterest poi, Predicate<PositionOfInterest> isOtherPoiContained){
        Stream var10000 = poi.offsets.stream();
        Map var10001 = PositionOfInterest.SIMPLE_OFFSET_POSITIONS;
        var10001.getClass();
        return var10000.map(var10001::get).noneMatch(isOtherPoiContained);
    }
    public boolean has(PositionOfInterest poi) {
        return (this.combinedPositionBitFlags & poi.bitFlag) != 0;
    }

    public int getCombinedBitFlags() {
        return combinedPositionBitFlags;
    }

    private boolean hasIrrelevantPositions(){
        return PositionOfInterest.COMPOUND_OFFSET_POSITIONS.stream().filter(this::has).anyMatch((poi) ->
                isIrrelevantCompoundOffsetPosition(poi, this::has));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this){
            return true;
        }else if (other != null && other.getClass() == this.getClass()){
            ConnectedTexture3DContext otherData = (ConnectedTexture3DContext) other;
            return this.combinedPositionBitFlags == otherData.combinedPositionBitFlags;
        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getCombinedBitFlags();
    }

    @Override
    public String toString() {
        String s = "ConnectedTexture3DContext[";
        int added = 0;
        PositionOfInterest[] var3 = PositionOfInterest.values();
        int var4 = var3.length;

        for (int var5  = 0; var5 < var4; ++var5){
            PositionOfInterest poi = var3[var5];
            if (this.has(poi)){
                if (added > 0){
                    s = s + ", ";
                }

                s = s + poi.name();
                ++added;
            }
        }

        s = s + "]";
        return s;
    }

    public boolean hasProperty(ModelProperty<?> prop){
        return false;
    }

    @Nullable
    public <T> T getData(ModelProperty<T> prop){
        return null;
    }

    @Nullable
    public <T> T setData(ModelProperty<T> prop, T data){
        return null;
    }

    public static enum BlockConnectionType {
        SAME_BLOCK("same_block", (state, otherState, offsets) -> {
            return state.getBlock() == otherState.getBlock();
        }),
        NO_CONNECTIONS("no_connections", (state, otherState, offsets) ->
            false
        );
        //GATE("gate", GateBlock::doBlocksConnectVisually),
        //CONNECTED_WATTLE("connected_wattle", WattleAndDaubBlock::doBlocksConnectVisually);

        private final String name;
        private final BlockConnectionTest connectionTest;
        private static final Map<String, BlockConnectionType> TYPES_BY_NAME = Stream.of(values()).collect(Collectors.toMap((type) ->
            type.name
        , UnaryOperator.identity()));

        private BlockConnectionType(String name, BlockConnectionTest connectionTest) {
            this.name = name;
            this.connectionTest = connectionTest;
        }

        public boolean connects(BlockState state, BlockState otherState, List<Direction> offsets) {
            return this.connectionTest.test(state, otherState, offsets);
        }

        public static BlockConnectionType getByName(String name) {
            return (BlockConnectionType)TYPES_BY_NAME.get(name);
        }

        @FunctionalInterface
        public interface BlockConnectionTest {
            boolean test(BlockState var1, BlockState var2, List<Direction> var3);
        }
    }
    public static enum PositionOfInterest{


        DOWN("down", new Direction[]{Direction.DOWN}),
        UP("up", new Direction[]{Direction.UP}),
        NORTH("north", new Direction[]{Direction.NORTH}),
        SOUTH("south", new Direction[]{Direction.SOUTH}),
        WEST("west", new Direction[]{Direction.WEST}),
        EAST("east", new Direction[]{Direction.EAST}),
        DOWN_NORTH("down_north", new Direction[]{Direction.DOWN, Direction.NORTH}),
        DOWN_SOUTH("down_south", new Direction[]{Direction.DOWN, Direction.SOUTH}),
        DOWN_WEST("down_west", new Direction[]{Direction.DOWN, Direction.WEST}),
        DOWN_EAST("down_east", new Direction[]{Direction.DOWN, Direction.EAST}),
        UP_NORTH("up_north", new Direction[]{Direction.UP, Direction.NORTH}),
        UP_SOUTH("up_south", new Direction[]{Direction.UP, Direction.SOUTH}),
        UP_WEST("up_west", new Direction[]{Direction.UP, Direction.WEST}),
        UP_EAST("up_east", new Direction[]{Direction.UP, Direction.EAST}),
        NORTH_WEST("north_west", new Direction[]{Direction.NORTH, Direction.WEST}),
        NORTH_EAST("north_east", new Direction[]{Direction.NORTH, Direction.EAST}),
        SOUTH_WEST("south_west", new Direction[]{Direction.SOUTH, Direction.WEST}),
        SOUTH_EAST("south_east", new Direction[]{Direction.SOUTH, Direction.EAST});

        public final int bitFlag = 1 << this.ordinal();
        public final String nameInJson;
        public final List<Direction> offsets;

        private static final Map<String, PositionOfInterest> POSITIONS_BY_NAME = Stream.of(values()).collect(Collectors.toMap((poi) ->
             poi.nameInJson
        , UnaryOperator.identity()));
        public static final Map<Direction, PositionOfInterest> SIMPLE_OFFSET_POSITIONS = Stream.of(values()).filter(PositionOfInterest::isSimpleOffset).collect(Collectors.toMap((poi) ->
           poi.offsets.get(0)
        , UnaryOperator.identity()));
        public static final List<PositionOfInterest> COMPOUND_OFFSET_POSITIONS = Stream.of(values()).filter(PositionOfInterest::isCompoundOffset).collect(Collectors.toList());
        PositionOfInterest(String s, Direction... offs) {
            this.nameInJson = s;
            this.offsets = Arrays.asList(offs);
            if (this.offsets.isEmpty()) {
                throw new IllegalArgumentException("Connected tex: position of interest '" + this.nameInJson + "' offsets must not be empty");
            }
        }

        public boolean isSimpleOffset(){
            return this.offsets.size() == 1;
        }

        public boolean isCompoundOffset(){
            return this.offsets.size() > 1;
        }

        public static PositionOfInterest getByJsonName(String name){
            return POSITIONS_BY_NAME.get(name);
        }
    }
}
