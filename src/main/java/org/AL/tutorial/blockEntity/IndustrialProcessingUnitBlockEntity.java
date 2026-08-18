package org.AL.tutorial.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.AL.tutorial.init.ModBlockEntity;

public class IndustrialProcessingUnitBlockEntity extends BlockEntity {

    private int progress = 0;

    public IndustrialProcessingUnitBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntity.INDUSTRIAL_PROCESSING_UNIT_BE.get(), pPos, pBlockState);
    }

    public void tick() {
        progress++;
        setChanged();
    }

    public int getProgress(){
        return progress;
    }

    public Component getDebugMassages(){
        return Component.literal("progress:" + getProgress());
    }


    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("progress", progress);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        progress = pTag.getInt("progress");
    }
}
