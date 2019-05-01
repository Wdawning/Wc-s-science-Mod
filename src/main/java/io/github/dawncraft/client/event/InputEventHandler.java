package io.github.dawncraft.client.event;

import io.github.dawncraft.capability.CapabilityLoader;
import io.github.dawncraft.capability.IPlayerMagic;
import io.github.dawncraft.client.ClientProxy;
import io.github.dawncraft.client.gui.container.GuiMagic;
import io.github.dawncraft.config.KeyLoader;
import io.github.dawncraft.network.MessageSpellSkillChange;
import io.github.dawncraft.network.NetworkLoader;
import io.github.dawncraft.skill.EnumSpellAction;
import io.github.dawncraft.skill.SkillStack;
import io.github.dawncraft.util.WebBrowserV3;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/**
 * Handle some input events.
 *
 * @author QingChenW
 */
public class InputEventHandler
{
    public InputEventHandler() {}

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.getRenderViewEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) mc.getRenderViewEntity();
            // Switch key was pressed
            if (KeyLoader.change.isPressed())
            {
                ClientProxy.getIngameGUIDawn().changeMode();
            }
            // Spell key was pressed
            if (!mc.playerController.isSpectator() && ClientProxy.getIngameGUIDawn().spellMode)
            {
                for (int i = 0; i < mc.gameSettings.keyBindsHotbar.length; i++)
                {
                    if (mc.gameSettings.keyBindsHotbar[i].isPressed())
                    {
                        IPlayerMagic playerMagic = player.getCapability(CapabilityLoader.playerMagic, null);
                        SkillStack skillStack = playerMagic.getSkillInventory().getStackInSlot(i);
                        if (skillStack != null && (playerMagic.getSpellAction() == EnumSpellAction.NONE || !playerMagic.getSkillInventory().getStackInSlot(i).isSkillStackEqual(playerMagic.getSkillInSpell())))
                        {
                            ClientProxy.getIngameGUIDawn().setSpellIndex(i);
                            NetworkLoader.instance.sendToServer(new MessageSpellSkillChange(i));
                        }
                    }
                }
            }
            // Magic key was pressed
            if (KeyLoader.reload.isPressed())
            {
                mc.displayGuiScreen(new GuiMagic(player));
            }
            // Use key was pressed
            if (KeyLoader.use.isPressed())
            {
                
            }
        }
        // Wiki key was pressed
        if (KeyLoader.encyclopedia.isPressed())
        {
            new WebBrowserV3("Wiki", "Minecraft_Wiki");
            //mc.displayGuiScreen(new GuiEncyclopedia());
        }
    }
}
