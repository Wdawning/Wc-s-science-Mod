package io.github.dawncraft.client.event;

import io.github.dawncraft.capability.CapabilityLoader;
import io.github.dawncraft.capability.IMagic;
import io.github.dawncraft.client.gui.magic.GuiMagic;
import io.github.dawncraft.config.KeyLoader;
import io.github.dawncraft.entity.magicile.EnumSpellAction;
import io.github.dawncraft.network.MessageSpellSkillChange;
import io.github.dawncraft.network.NetworkLoader;
import io.github.dawncraft.util.WebBrowserV1;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/**
 * @author QingChenW
 *
 */
public class InputHandler
{
    public InputHandler(FMLInitializationEvent event) {}

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        Minecraft mc = Minecraft.getMinecraft();
        if(mc.getRenderViewEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) mc.getRenderViewEntity();
            // Switch key was pressed
            if (KeyLoader.change.isPressed())
            {
                GuiIngameDawn.getIngameDawnGUI().changeMode();
            }
            // Spell key was pressed
            if (GuiIngameDawn.getIngameDawnGUI().spellMode)
            {
                for(int i = 0; i < mc.gameSettings.keyBindsHotbar.length; i++)
                {
                    if(mc.gameSettings.keyBindsHotbar[i].isPressed())
                    {
                        if(player.hasCapability(CapabilityLoader.magic, null))
                        {
                            IMagic magic = player.getCapability(CapabilityLoader.magic, null);

                            if(magic.getSpellAction() == EnumSpellAction.NONE || i != magic.getSpellIndex())
                            {
                                GuiIngameDawn.getIngameDawnGUI().setSpellingSkill(i);
                                NetworkLoader.instance.sendToServer(new MessageSpellSkillChange(i));
                            }
                        }
                    }
                }
            }
            // Magic key was pressed
            if (KeyLoader.magic.isPressed())
            {
                mc.displayGuiScreen(new GuiMagic(player));
            }
            // Use key was pressed
            if (KeyLoader.use.isPressed())
            {
                /*
              //public float defaultFov;
              if(KeyLoader.aim.isPressed())
              {
              if(mc.gameSettings.fovSetting != 10.0F)
              {
                  defaultFov = mc.gameSettings.fovSetting;
                  mc.gameSettings.fovSetting = 10.0F;
              }
              else
              {
                  mc.gameSettings.fovSetting = defaultFov;
              }

              EntityPlayerSP player = mc.thePlayer;
              player.addChatMessage(new ChatComponentTranslation("chat.dawncraft.zoom"));
              }*/
            }
        }
        // Wiki key was pressed
        if (KeyLoader.Encyclopedia.isPressed())
        {
            WebBrowserV1 webBrowser = new WebBrowserV1("我的世界中文维基百科", "http://minecraft-zh.gamepedia.com/Minecraft_Wiki");
        }
    }
}