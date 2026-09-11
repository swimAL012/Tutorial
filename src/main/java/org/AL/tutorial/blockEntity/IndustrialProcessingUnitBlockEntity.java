package org.AL.tutorial.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.AL.tutorial.container.menu.IndustrialProcessingUnitMenu;
import org.AL.tutorial.init.ModBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IndustrialProcessingUnitBlockEntity extends BlockEntity implements MenuProvider {

    private int progress = 0;
    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex){
                case 0 -> progress;
                default -> 0;
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            if (pIndex == 0)  progress = pValue;
        }

        @Override
        public int getCount() {
            return 1;
        }
    };

    public IndustrialProcessingUnitBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntity.INDUSTRIAL_PROCESSING_UNIT_BE.get(), pPos, pBlockState);
    }

    public void tick() {
        progress++;
        setChanged();
    }

    public int getProgress() {
        return progress;
    }

    public Component getDebugMassages() {
        return Component.literal("progress:" + getProgress());
    }


    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("progress", progress);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        progress = pTag.getInt("progress");
    }

    @Override
    @NotNull
    public  Component getDisplayName() {
        return Component.translatable("be.title.industrial_processing_unit");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return new IndustrialProcessingUnitMenu(pContainerId, pPlayerInventory, this, data);
    }
}
