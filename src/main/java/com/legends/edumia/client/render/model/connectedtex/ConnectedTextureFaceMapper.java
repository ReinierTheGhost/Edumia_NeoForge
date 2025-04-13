package com.legends.edumia.client.render.model.connectedtex;

import net.minecraft.Util;
import net.minecraft.core.Direction;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.legends.edumia.client.render.model.connectedtex.ConnectedTexture3DContext.PositionOfInterest;
import com.legends.edumia.client.render.model.connectedtex.ConnectedTexture2DContext.RelativePosition;
public abstract class ConnectedTextureFaceMapper {
    public static final Map<Direction, ConnectedTextureFaceMapper> FACE_MAPPERS = (Map) Util.make(new HashMap(), (map) -> {
        map.put(Direction.DOWN, new ConnectedTextureFaceMapper() {
            public PositionOfInterest getPositionToCheck(RelativePosition pos) {
                switch (pos) {
                    case TOP_LEFT:
                        return PositionOfInterest.SOUTH_WEST;
                    case TOP:
                        return PositionOfInterest.SOUTH;
                    case TOP_RIGHT:
                        return PositionOfInterest.SOUTH_EAST;
                    case LEFT:
                        return PositionOfInterest.WEST;
                    case RIGHT:
                        return PositionOfInterest.EAST;
                    case BOTTOM_LEFT:
                        return PositionOfInterest.NORTH_WEST;
                    case BOTTOM:
                        return PositionOfInterest.NORTH;
                    case BOTTOM_RIGHT:
                        return PositionOfInterest.NORTH_EAST;
                    case CENTRE:
                    default:
                        throw new IllegalArgumentException(pos.name());
                }
            }
        });
        map.put(Direction.UP, new ConnectedTextureFaceMapper() {
            public PositionOfInterest getPositionToCheck(RelativePosition pos) {
                switch (pos) {
                    case TOP_LEFT:
                        return PositionOfInterest.NORTH_WEST;
                    case TOP:
                        return PositionOfInterest.NORTH;
                    case TOP_RIGHT:
                        return PositionOfInterest.NORTH_EAST;
                    case LEFT:
                        return PositionOfInterest.WEST;
                    case RIGHT:
                        return PositionOfInterest.EAST;
                    case BOTTOM_LEFT:
                        return PositionOfInterest.SOUTH_WEST;
                    case BOTTOM:
                        return PositionOfInterest.SOUTH;
                    case BOTTOM_RIGHT:
                        return PositionOfInterest.SOUTH_EAST;
                    case CENTRE:
                    default:
                        throw new IllegalArgumentException(pos.name());
                }
            }
        });
        map.put(Direction.NORTH, new ConnectedTextureFaceMapper() {
            public PositionOfInterest getPositionToCheck(RelativePosition pos) {
                switch (pos) {
                    case TOP_LEFT:
                        return PositionOfInterest.UP_EAST;
                    case TOP:
                        return PositionOfInterest.UP;
                    case TOP_RIGHT:
                        return PositionOfInterest.UP_WEST;
                    case LEFT:
                        return PositionOfInterest.EAST;
                    case RIGHT:
                        return PositionOfInterest.WEST;
                    case BOTTOM_LEFT:
                        return PositionOfInterest.DOWN_EAST;
                    case BOTTOM:
                        return PositionOfInterest.DOWN;
                    case BOTTOM_RIGHT:
                        return PositionOfInterest.DOWN_WEST;
                    case CENTRE:
                    default:
                        throw new IllegalArgumentException(pos.name());
                }
            }
        });
        map.put(Direction.SOUTH, new ConnectedTextureFaceMapper() {
            public PositionOfInterest getPositionToCheck(RelativePosition pos) {
                switch (pos) {
                    case TOP_LEFT:
                        return PositionOfInterest.UP_WEST;
                    case TOP:
                        return PositionOfInterest.UP;
                    case TOP_RIGHT:
                        return PositionOfInterest.UP_EAST;
                    case LEFT:
                        return PositionOfInterest.WEST;
                    case RIGHT:
                        return PositionOfInterest.EAST;
                    case BOTTOM_LEFT:
                        return PositionOfInterest.DOWN_WEST;
                    case BOTTOM:
                        return PositionOfInterest.DOWN;
                    case BOTTOM_RIGHT:
                        return PositionOfInterest.DOWN_EAST;
                    case CENTRE:
                    default:
                        throw new IllegalArgumentException(pos.name());
                }
            }
        });
        map.put(Direction.WEST, new ConnectedTextureFaceMapper() {
            public PositionOfInterest getPositionToCheck(RelativePosition pos) {
                switch (pos) {
                    case TOP_LEFT:
                        return PositionOfInterest.UP_NORTH;
                    case TOP:
                        return PositionOfInterest.UP;
                    case TOP_RIGHT:
                        return PositionOfInterest.UP_SOUTH;
                    case LEFT:
                        return PositionOfInterest.NORTH;
                    case RIGHT:
                        return PositionOfInterest.SOUTH;
                    case BOTTOM_LEFT:
                        return PositionOfInterest.DOWN_NORTH;
                    case BOTTOM:
                        return PositionOfInterest.DOWN;
                    case BOTTOM_RIGHT:
                        return PositionOfInterest.DOWN_SOUTH;
                    case CENTRE:
                    default:
                        throw new IllegalArgumentException(pos.name());
                }
            }
        });
        map.put(Direction.EAST, new ConnectedTextureFaceMapper() {
            public PositionOfInterest getPositionToCheck(RelativePosition pos) {
                switch (pos) {
                    case TOP_LEFT:
                        return PositionOfInterest.UP_SOUTH;
                    case TOP:
                        return PositionOfInterest.UP;
                    case TOP_RIGHT:
                        return PositionOfInterest.UP_NORTH;
                    case LEFT:
                        return PositionOfInterest.SOUTH;
                    case RIGHT:
                        return PositionOfInterest.NORTH;
                    case BOTTOM_LEFT:
                        return PositionOfInterest.DOWN_SOUTH;
                    case BOTTOM:
                        return PositionOfInterest.DOWN;
                    case BOTTOM_RIGHT:
                        return PositionOfInterest.DOWN_NORTH;
                    case CENTRE:
                    default:
                        throw new IllegalArgumentException(pos.name());
                }
            }
        });
    });

    private ConnectedTextureFaceMapper() {
    }

    abstract PositionOfInterest getPositionToCheck(RelativePosition var1);

    public static ConnectedTexture2DContext get2dFrom3d(ConnectedTexture3DContext ctx3d, Direction side) {
        ConnectedTextureFaceMapper faceMapper = (ConnectedTextureFaceMapper)FACE_MAPPERS.get(side);
        Set<RelativePosition> relativePositions = EnumSet.noneOf(RelativePosition.class);
        RelativePosition[] var4 = RelativePosition.values();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            RelativePosition rPos = var4[var6];
            if (rPos == RelativePosition.CENTRE) {
                relativePositions.add(rPos);
            } else if (ctx3d.has(faceMapper.getPositionToCheck(rPos))) {
                relativePositions.add(rPos);
            }
        }

        return new ConnectedTexture2DContext(relativePositions);
    }
}
