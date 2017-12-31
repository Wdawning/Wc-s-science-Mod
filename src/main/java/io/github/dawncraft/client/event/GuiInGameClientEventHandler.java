package io.github.dawncraft.client.event;

import io.github.dawncraft.Dawncraft;
import io.github.dawncraft.capability.CapabilityLoader;
import io.github.dawncraft.config.ConfigLoader;
import io.github.dawncraft.item.ItemLoader;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author QingChenW
 *
 */
public class GuiInGameClientEventHandler extends Gui
{
    public static GuiInGameClientEventHandler instance;
    public static final ResourceLocation DCTEXTURES = new ResourceLocation(Dawncraft.MODID + ":" + "textures/gui/widgets.png");
    public boolean magicMode = false;
    public boolean isSpelling = false;
    public int magicIndex;
    private Minecraft mc = Minecraft.getMinecraft();
    
    public GuiInGameClientEventHandler(FMLInitializationEvent event) {this.instance = this;}
    
    @SubscribeEvent
    public void PreGUIRender(RenderGameOverlayEvent.Pre event)
    {
        if (this.mc.getRenderViewEntity() instanceof EntityPlayer)
        {
            int air,mana,a,b;
            EntityPlayer entityplayer = (EntityPlayer)this.mc.getRenderViewEntity();
            int width = event.resolution.getScaledWidth();
            int height = event.resolution.getScaledHeight();
            int w1,h1;
            
            if(this.mc.playerController.gameIsSurvivalOrAdventure())
            {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.getTextureManager().bindTexture(DCTEXTURES);
                GlStateManager.enableAlpha();
                
                if(entityplayer.hasCapability(CapabilityLoader.mana, null))
                {
                    mana = entityplayer.getCapability(CapabilityLoader.mana, null).getMana();
                }
                else
                {
                    mana = 20;
                }
                w1 = width / 2 + 91;
                h1 = height - 39 - 9 - 1;
                int ii,x,y;
                int u = 22;
                
                if(ConfigLoader.manaRenderType)
                {
                    u = u + 9;
                }

                for (ii = 0; ii < 10; ++ii)
                {
                    x = w1 - ii * 8 - 9;
                    y = h1;

                    this.drawTexturedModalRect(x, y, 0, u, 9, 9);

                    if (ii * 2 + 1 < mana)
                    {
                        this.drawTexturedModalRect(x, y, 9, u, 9, 9);
                    }

                    if (ii * 2 + 1 == mana)
                    {
                        this.drawTexturedModalRect(x, y, 17, u, 9, 9);
                    }
                }
            }
            
            if(event.type == ElementType.AIR)
            {
                event.setCanceled(true);
                w1 = width / 2 + 91;
                h1 = height - 39 - 9 - 1 - 9 - 1;
                
                this.mc.mcProfiler.startSection("air");
                if (entityplayer.isInsideOfMaterial(Material.water))
                {
                    air = this.mc.thePlayer.getAir();
                    
                    a = MathHelper.ceiling_double_int((air - 2) * 10.0D / 300.0D);
                    b = MathHelper.ceiling_double_int(air * 10.0D / 300.0D) - a;

                    for (int i = 0; i < a + b; ++i)
                    {
                        if (i < a)
                        {
                            this.drawTexturedModalRect(w1 - i * 8 - 9, h1, 16, 18, 9, 9);
                        }
                        else
                        {
                            this.drawTexturedModalRect(w1 - i * 8 - 9, h1, 25, 18, 9, 9);
                        }
                    }
                    this.mc.mcProfiler.endSection();
                }
            }
            
            if(event.type == ElementType.HOTBAR)
            {
                if(this.magicMode)
                {
                    event.setCanceled(true);
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    this.mc.getTextureManager().bindTexture(DCTEXTURES);
                    GlStateManager.enableAlpha();

                    w1 = width / 2 - 91;
                    h1 = height - 22;
                    
                    this.drawTexturedModalRect(w1, h1, 0, 0, 182, 22);
                    if(this.isSpelling)
                    {
                        this.drawTexturedModalRect(w1 - 1 + this.magicIndex * 20, h1 - 1, 182, 0, 24, 22);
                    }
                    
                    //GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                    for (int i = 0; i < 9; ++i)
                    {
                        int x = w1 + 3 + i * 20;
                        int y = height - 16 - 3;
                        if(i == 0)
                        {
                            this.mc.getTextureManager().bindTexture(new ResourceLocation(Dawncraft.MODID + ":" + "textures/gui/skill.png"));
                            this.drawTexturedModalRect(x, y, 0, 0, 16, 16);
                            this.mc.getTextureManager().bindTexture(DCTEXTURES);
                        }
                    }
                }
            }
            
            if(this.magicMode && this.isSpelling)
            {
                if(this.magicIndex == 0)
                {
                    String s = I18n.format("magic.prefix.spell", I18n.format("skill.heal.name"));
                    this.drawCenteredString(this.mc.fontRendererObj, s, width / 2, height - 54, 16777215);
                }
            }
            
            if(entityplayer.isUsingItem() && entityplayer.getItemInUse().getItem() == ItemLoader.gunRPG)
            {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.getTextureManager().bindTexture(DCTEXTURES);
                GlStateManager.enableAlpha();
                this.drawTexturedModalRect(width / 2 - 20, height / 2 - 20, 214, 0, 42, 42);
            }
            
            this.mc.getTextureManager().bindTexture(super.icons);
        }
    }
    
    @SubscribeEvent
    public void TextRender(RenderGameOverlayEvent.Text event)
    {
        event.left.add(0, String.format("Welcome to play Dawncraft Mod, %s!", Minecraft.getMinecraft().thePlayer.getName()));
        event.left.add(1, String.format("Dawncraft Mod's version is %s!", Dawncraft.VERSION));
        event.left.add(2, String.format("This word will remove in the future!"));
        EntityPlayer entityplayer = (EntityPlayer)this.mc.getRenderViewEntity();
        int mp = 20;
        if(entityplayer.hasCapability(CapabilityLoader.mana, null))
        {
            mp = entityplayer.getCapability(CapabilityLoader.mana, null).getMana();
        }
        event.left.add(3, String.format("Your Mana is %s!", mp));
    }
}