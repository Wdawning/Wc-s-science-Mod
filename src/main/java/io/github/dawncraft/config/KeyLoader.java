package io.github.dawncraft.config;

import org.lwjgl.input.Keyboard;

import io.github.dawncraft.dawncraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Register keys.
 *
 * @author QingChenW
 */
public class KeyLoader
{
    public static KeyBinding change = new KeyBinding("key." + dawncraft.MODID + ".switch", Keyboard.KEY_GRAVE, "key.categories." + dawncraft.MODID);
    public static KeyBinding magic = new KeyBinding("key." + dawncraft.MODID + ".magic", Keyboard.KEY_R, "key.categories." + dawncraft.MODID);
    public static KeyBinding use = new KeyBinding("key." + dawncraft.MODID + ".use", Keyboard.KEY_LMENU, "key.categories." + dawncraft.MODID);
    public static KeyBinding Encyclopedia =  new KeyBinding("key." + dawncraft.MODID + ".wiki", Keyboard.KEY_H, "key.categories." + dawncraft.MODID);
    
    public KeyLoader(FMLInitializationEvent event)
    {
        this.register(KeyLoader.change);
        this.register(KeyLoader.magic);
        this.register(KeyLoader.use);
        this.register(KeyLoader.Encyclopedia);
    }

    private void register(KeyBinding keybinding)
    {
        ClientRegistry.registerKeyBinding(keybinding);
    }
}
