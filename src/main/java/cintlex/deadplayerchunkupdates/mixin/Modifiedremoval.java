package cintlex.deadplayerchunkupdates.mixin;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.level.GameRules;

@Mixin(ChunkMap.class)
public abstract class Modifiedremoval {
    @Final
    @Shadow
    private Int2ObjectMap<?> entityMap;

    @Inject(method = "removeEntity", at = @At("HEAD"), cancellable = true)
    private void removeButDontRemoveTrackingPlease(Entity e, CallbackInfo ci) {
        if (e instanceof ServerPlayer sp) {
            if (sp.level().getGameRules().getBoolean(GameRules.RULE_DO_IMMEDIATE_RESPAWN)) {
                return;
            }

            if (sp.getRemovalReason() == Entity.RemovalReason.KILLED) {
                Object ent = this.entityMap.remove(e.getId());
                if (ent != null) {
                    ((TEA) ent).br();
                }
                ci.cancel();
            }
        }
    }
}