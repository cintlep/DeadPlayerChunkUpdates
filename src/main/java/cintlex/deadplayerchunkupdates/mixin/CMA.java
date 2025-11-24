package cintlex.deadplayerchunkupdates.mixin;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
@Mixin(ChunkMap.class)
public interface CMA {
    @Invoker("updatePlayerStatus")
    void ups(ServerPlayer sp, boolean a);
}