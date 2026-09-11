package org.AL.tutorial.container.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.AL.tutorial.blockEntity.IndustrialProcessingUnitBlockEntity;
import org.AL.tutorial.init.ModBlocks;
import org.AL.tutorial.init.ModMenuTypes;

public class IndustrialProcessingUnitMenu extends AbstractContainerMenu {

    public final IndustrialProcessingUnitBlockEntity blockEntity;

    private final Level level;

    private final ContainerData data;

    public IndustrialProcessingUnitMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv,
                inv.player.level().getBlockEntity(buf.readBlockPos()),
                new SimpleContainerData(1));
    }


    public IndustrialProcessingUnitMenu(int id, Inventory inv, BlockEntity blockEntity, ContainerData data) {
        super(ModMenuTypes.INDUSTRIAL_PROCESSING_UNIT_MENU.get(), id);

        this.blockEntity = (IndustrialProcessingUnitBlockEntity) blockEntity;

        this.level = inv.player.level();

        this.data = data;

        addDataSlots(data);
    }

    public IndustrialProcessingUnitBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(
                ContainerLevelAccess.create(level,blockEntity.getBlockPos()),
                pPlayer,
                ModBlocks.INDUSTRIAL_PROCESSING_UNIT.get()
        );
    }
}
