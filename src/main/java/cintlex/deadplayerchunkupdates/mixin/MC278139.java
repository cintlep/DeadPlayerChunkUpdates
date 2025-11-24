package cintlex.deadplayerchunkupdates.mixin;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

@Mixin(ServerPlayer.class)
public class MC278139 {
    @WrapOperation(
            method = "die",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;markClientUnloadedAfterDeath()V"
            )
    )
    private void keeploaded(ServerGamePacketListenerImpl connection, Operation<Void> rest) {
    }
}