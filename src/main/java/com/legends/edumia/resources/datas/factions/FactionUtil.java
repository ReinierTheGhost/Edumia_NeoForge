package com.legends.edumia.resources.datas.factions;

import com.legends.edumia.commands.CommandUtils;
import com.legends.edumia.exceptions.FactionResourceLocationException;
import com.legends.edumia.exceptions.IdenticalFactionException;
import com.legends.edumia.exceptions.NoFactionException;
import com.legends.edumia.exceptions.SpawnResourceLocationException;
import com.legends.edumia.resources.StateSaverAndLoader;
import com.legends.edumia.resources.datas.factions.data.SpawnDataHandler;
import com.legends.edumia.resources.persistent_datas.AffiliationData;
import com.legends.edumia.resources.persistent_datas.PlayerData;
import com.legends.edumia.utils.LoggerUtil;
import com.legends.edumia.utils.ModColors;
import com.legends.edumia.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class FactionUtil {

    public static boolean updateFaction(ServerPlayer player, @Nullable Faction faction, @Nullable ResourceLocation spawnId)
            throws IdenticalFactionException, SpawnResourceLocationException, FactionResourceLocationException, NoFactionException {
        if(!assertUpdateFactionValues(player, faction, spawnId))
            return false;

        PlayerData playerState = StateSaverAndLoader.getPlayerState(player);
        Faction previousFaction = null;
        try{
            previousFaction = playerState.getCurrentFaction(player.level());
        } catch (FactionResourceLocationException ignored){}

        // [CLEAR] If the next faction is null
        if(faction == null){
            return clearFaction(player);
        }

        // [REPLACE] If previous faction is not null and next faction is not null
        if(previousFaction != null){
            sendOnLeaveCommand(player, previousFaction);
            // Send leaving message to affected player
            MutableComponent targetText = Component.translatable("event.edumia.leave.faction.success",
                    previousFaction.getFullName());
            player.sendSystemMessage(targetText.withColor(ModColors.WARNING.color));
        }

        // [JOIN] Add new affiliation data
        ResourceLocation foundSpawnId = getSpawnId(faction, spawnId);
        AffiliationData newAffiliationData = new AffiliationData(faction.getDispositionString(), faction.getId(), foundSpawnId);
        playerState.setAffiliationData(newAffiliationData);
        sendOnJoinCommand(player, faction);

        // Send join message to affected player
        MutableComponent targetText = Component.translatable("event.edumia.join.faction.success", faction.getFullName());
        player.sendSystemMessage(targetText.withColor(ModColors.SUCCESS.color));

        sendOnFactionJoinMessage(player);
        return true;
    }

    private static boolean assertUpdateFactionValues(ServerPlayer player, Faction faction, ResourceLocation spawnId)
            throws IdenticalFactionException, SpawnResourceLocationException {
        // Verify player
        if(player == null) return false;

        // Verify faction
        // Fetch player datas
        PlayerData playerState = StateSaverAndLoader.getPlayerState(player);
        ResourceLocation previousFactionId = playerState.getCurrentFactionId();

        if(previousFactionId == null)
            return true;

        // If there is no faction update, return true
        if(previousFactionId == faction.getId() || spawnId == playerState.getCurrentSpawnId()) {
            throw new IdenticalFactionException();
        };

        // Verify spawnId
        if(getSpawnId(faction, spawnId) == null)
            return false;

        return true;
    }

    private static ResourceLocation getSpawnId(Faction faction, ResourceLocation spawnId) throws SpawnResourceLocationException {
        SpawnDataHandler spawnDataHandler = faction.getSpawnData();
        if(spawnDataHandler != null){
            if(spawnId == null)
                spawnId = spawnDataHandler.getDefaultSpawn();

            boolean spawnExistInFaction = spawnDataHandler.getAllSpawnIdentifiers().contains(spawnId);

            if(spawnId == null || !spawnExistInFaction){
                throw new SpawnResourceLocationException();
            }
        }
        return spawnId;
    }

    private static void sendOnJoinCommand(ServerPlayer player, Faction faction) {
        if(player == null || faction == null) return;

        Optional<List<String>> joinCommand = faction.getJoinCommands();
        if(joinCommand.isEmpty()) return;

        List<String> commands = joinCommand.orElse(null);

        if(commands.isEmpty()) return;
        CommandUtils.sendAllCommands(player, commands);
    }

    private static void sendOnLeaveCommand(ServerPlayer player, Faction previousFaction) {
        if(player == null || previousFaction == null) return;

        Optional<List<String>> leaveCommand = previousFaction.getLeaveCommands();
        if(leaveCommand.isEmpty()) return;

        List<String> commands = leaveCommand.orElse(null);

        if(commands.isEmpty()) return;
        CommandUtils.sendAllCommands(player, commands);
    }

    public static void sendOnFactionJoinMessage(Player player) {
        PlayerData data = StateSaverAndLoader.getPlayerState(player);

        if(data.hasAffilition()){
            ResourceLocation factionId = data.getCurrentFactionId();
            Faction faction = null;
            try {
                faction = data.getFaction(player.level());
                if(faction == null) return;
            } catch (FactionResourceLocationException e) {
                LoggerUtil.logError("Couldn't find faction by id <%s>".formatted(factionId));
                return;
            }

            MutableComponent targetText = Component.translatable("event.edumia.join.faction.success", faction.getFullName());
            ((ServerPlayer) player).connection.send(
                    new ClientboundSetTitleTextPacket(Component.nullToEmpty(""))
            );
            ((ServerPlayer) player).connection.send(
                    new ClientboundSetSubtitleTextPacket(targetText.withColor(ModColors.SUCCESS.color))
            );
        }
    }

    public static boolean clearFaction(ServerPlayer player) throws FactionResourceLocationException, NoFactionException {
        PlayerData data = StateSaverAndLoader.getPlayerState(player);
        if(data.hasAffilition()){
            Faction faction = data.getFaction(player.level());
            data.setAffiliationData(null);

            sendOnLeaveCommand(player, faction);
            MutableComponent targetText = Component.translatable("event.edumia.leave.faction.success", faction.getFullName());
            player.sendSystemMessage(targetText.withColor(ModColors.WARNING.color));
            return true;
        } else {
            throw new NoFactionException();
        }
    }

    /**
     * Simply used to explore spawn points.
     * @param target player to teleport
     * @param spawnId spawn identifier the player need to teleport to
     * @return if the process was a success or not
     */
    public static boolean forceTeleportToSpawnMiddleEarthId(ServerPlayer target, ResourceLocation spawnId){
        BlockPos spawnBlockPos = null;
        spawnBlockPos = getSpawnBlockPos(target.level(), spawnId);
        if(spawnBlockPos == null)
            return false;
        ModDimensions.teleportPlayerToEdumia(target, new Vec3(spawnBlockPos.getX(), spawnBlockPos.getY(), spawnBlockPos.getZ()),
                false, false);
        return true;
    }

    public static BlockPos getSpawnBlockPos(Level world, ResourceLocation spawnId) {
        BlockPos spawnBlockPos = null;
        for(Faction faction: FactionLookup.getAllFactions(world)){
            SpawnDataHandler spawnDataHandler = faction.getSpawnData();
            if(spawnDataHandler != null)
                spawnBlockPos = spawnDataHandler.getSpawnBlockPos(spawnId);
            if(spawnBlockPos != null) {
                return spawnBlockPos;
            }

            if(faction.getSubFactions() != null){
                for(ResourceLocation subfactionId : faction.getSubFactions()){
                    try {
                        Faction subFaction = null;
                        subFaction = FactionLookup.getFactionById(world, subfactionId);
                        SpawnDataHandler subFacspawnDataHandler = subFaction.getSpawnData();
                        if(subFacspawnDataHandler != null)
                            spawnBlockPos = subFacspawnDataHandler.getSpawnBlockPos(spawnId);
                        if(spawnBlockPos != null) {
                            return spawnBlockPos;
                        }
                    } catch (FactionResourceLocationException e) {
                        return null;
                    }
                }
            }
        }
        return  null;
    }
}
