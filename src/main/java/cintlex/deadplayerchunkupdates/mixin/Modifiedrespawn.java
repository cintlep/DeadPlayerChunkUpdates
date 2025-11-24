package cintlex.deadplayerchunkupdates.mixin;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerList.class)
public class Modifiedrespawn {

    @Inject(method = "respawn", at = @At("HEAD"))
    private void cleanupDeadPerson(
            ServerPlayer spold,
            boolean keep,
            Entity.RemovalReason rr,
            CallbackInfoReturnable<ServerPlayer> cir
    ) {
        if (spold.level().getGameRules().getBoolean(GameRules.RULE_DO_IMMEDIATE_RESPAWN)) {
            return;
        }

        if (spold.getRemovalReason() == Entity.RemovalReason.KILLED) {
            ServerLevel sl = spold.level();
            ChunkMap cm = sl.getChunkSource().chunkMap;
            spold.setRemoved(Entity.RemovalReason.DISCARDED);
            ((CMA) cm).ups(spold, false);
        }
    }
}