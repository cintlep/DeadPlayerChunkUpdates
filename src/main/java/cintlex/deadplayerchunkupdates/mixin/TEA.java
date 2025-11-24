package cintlex.deadplayerchunkupdates.mixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "net.minecraft.server.level.ChunkMap$TrackedEntity")
public interface TEA {
    @Invoker("broadcastRemoved")
    void br();
}