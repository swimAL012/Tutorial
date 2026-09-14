package org.AL.tutorial.container.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.AL.tutorial.blockEntity.IndustrialProcessingUnitBlockEntity;
import org.AL.tutorial.init.ModBlocks;
import org.AL.tutorial.init.ModMenuTypes;

public class IndustrialProcessingUnitMenu extends AbstractContainerMenu {

    public final IndustrialProcessingUnitBlockEntity blockEntity;

    private final Level level;

    private final ContainerData data;

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private static final int TE_SLOT_COUNT = 2;

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

        addDataSlots(this.data);

        this.addMachineSlot(this.blockEntity.getItemHandler());

        addPlayerInventory(inv, 8, 84);
        addPlayerHotbar(inv, 8, 142);


    }


    private void addMachineSlot(IItemHandler handler) {
        this.addSlot(new SlotItemHandler(handler, INPUT_SLOT, 78, 35));
        this.addSlot(new SlotItemHandler(handler, OUTPUT_SLOT, 137, 35));
    }

    private void addPlayerInventory(Inventory inv, int leftCol, int topRow) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(
                        inv,
                        col + row * 9 + 9,
                        leftCol + col * 18,
                        topRow + row * 18
                ));
            }
        }
    }

    private void addPlayerHotbar(Inventory inv, int leftCol, int topRow) {
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(
                    inv,
                    col,
                    leftCol + col * 18,
                    topRow
            ));
        }
    }

    public IndustrialProcessingUnitBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {

        Slot slot = this.getSlot(pIndex);

        if (slot == null || !slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack item = slot.getItem();
        ItemStack copy = item.copy();

        boolean flag = false;

        int PLAYER_INV_START = TE_SLOT_COUNT;
        int PLAYER_INV_END = TE_SLOT_COUNT + 27;

        int HOTBAR_START = PLAYER_INV_END;
        int HOTBAR_END = PLAYER_INV_END + 9;


        if (pIndex == INPUT_SLOT) {
                flag = moveItemStackTo(item, PLAYER_INV_START, HOTBAR_END, false);
        } else if (pIndex == OUTPUT_SLOT) {
            if (moveItemStackTo(item, HOTBAR_START, HOTBAR_END, false)) {
                moveItemStackTo(item, PLAYER_INV_START, PLAYER_INV_END, false);
                flag = true;
            }else {
                flag = moveItemStackTo(item, PLAYER_INV_START, PLAYER_INV_END, false);
            }
        } else if (pIndex >= PLAYER_INV_START && pIndex < HOTBAR_END) {
                flag = moveItemStackTo(item, INPUT_SLOT, INPUT_SLOT + 1, false);
        }

        if (flag) {
            if (item.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            slot.onTake(pPlayer, item);

            return copy;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(
                ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer,
                ModBlocks.INDUSTRIAL_PROCESSING_UNIT.get()
        );
    }
}
