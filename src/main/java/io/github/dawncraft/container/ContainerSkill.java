package io.github.dawncraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

import io.github.dawncraft.capability.CapabilityLoader;
import io.github.dawncraft.entity.player.SkillInventoryPlayer;
import io.github.dawncraft.skill.SkillStack;

public abstract class ContainerSkill extends Container
{
    public List<SkillStack> inventorySkillStacks = Lists.<SkillStack>newArrayList();
    public List<SlotSkill> inventorySkillSlots = Lists.<SlotSkill>newArrayList();
    private final Set<SlotSkill> dragSkillSlots = Sets.<SlotSkill>newHashSet();
    protected List<ILearning> learners = Lists.<ILearning>newArrayList();

    public SlotSkill getSkillSlotFromInventory(ISkillInventory inventory, int slotId)
    {
        for (int i = 0; i < this.inventorySkillSlots.size(); ++i)
        {
            SlotSkill slot = this.inventorySkillSlots.get(i);

            if (slot.isHere(inventory, slotId))
            {
                return slot;
            }
        }
        return null;
    }
    
    protected SlotSkill addSkillSlotToContainer(SlotSkill slot)
    {
        slot.slotNumber = this.inventorySkillSlots.size();
        this.inventorySkillSlots.add(slot);
        this.inventorySkillStacks.add(null);
        return slot;
    }
    
    public SlotSkill getSkillSlot(int slotId)
    {
        return this.inventorySkillSlots.get(slotId);
    }
    
    public List<SkillStack> getSkillStacks()
    {
        List<SkillStack> list = Lists.<SkillStack>newArrayList();

        for (int i = 0; i < this.inventorySkillSlots.size(); ++i)
        {
            list.add(this.inventorySkillSlots.get(i).getStack());
        }

        return list;
    }

    public void putSkillStackInSlot(int slotId, SkillStack stack)
    {
        this.getSkillSlot(slotId).putStack(stack);
    }
    
    @SideOnly(Side.CLIENT)
    public void putSkillStacksInSlots(SkillStack[] skillStacks)
    {
        for (int i = 0; i < skillStacks.length; ++i)
        {
            this.getSkillSlot(i).putStack(skillStacks[i]);
        }
    }

    public SkillStack skillSlotClick(int slotId, int clickedButton, int mode, EntityPlayer player)
    {
        return null;
    }

    public SkillStack transferSkillStackInSlot(EntityPlayer player, int index)
    {
        SlotSkill slot = this.inventorySkillSlots.get(index);
        return slot != null ? slot.getStack() : null;
    }

    public void onLearnGuiOpened(ILearning listener)
    {
        if (this.learners.contains(listener))
        {
            throw new IllegalArgumentException("Listener already listening");
        }
        else
        {
            this.learners.add(listener);
            listener.updateLearningInventory(this, this.getSkillStacks());
            this.detectAndSendChanges();
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        SkillInventoryPlayer skillInventoryPlayer = player.getCapability(CapabilityLoader.playerMagic, null).getInventory();
        
        if (skillInventoryPlayer.getSkillStack() != null)
        {
            skillInventoryPlayer.setSkillStack(null);
        }
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for (int i = 0; i < this.inventorySkillSlots.size(); ++i)
        {
            SkillStack newStack = this.inventorySkillSlots.get(i).getStack();
            SkillStack oldStack = this.inventorySkillStacks.get(i);

            if (!SkillStack.areSkillStacksEqual(oldStack, newStack))
            {
                oldStack = newStack == null ? null : newStack.copy();
                this.inventorySkillStacks.set(i, oldStack);

                for (int j = 0; j < this.learners.size(); ++j)
                {
                    this.learners.get(j).sendSlotContents(this, i, oldStack);
                }
            }
        }
    }

    public void onLearnMatrixChanged(ISkillInventory inventory)
    {
        this.detectAndSendChanges();
    }
}
