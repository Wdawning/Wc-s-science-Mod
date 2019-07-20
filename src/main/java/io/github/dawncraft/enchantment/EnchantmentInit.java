package io.github.dawncraft.enchantment;

import com.google.common.base.Predicate;

import io.github.dawncraft.Dawncraft;
import io.github.dawncraft.api.item.ItemWand;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * Register some enchantments.
 *
 * @author QingChenW
 */
@Mod.EventBusSubscriber(modid = Dawncraft.MODID)
public class EnchantmentInit
{
    // Enchantment type
    public static final EnumEnchantmentType WAND = EnumHelper.addEnchantmentType("WAND", new Predicate<Item>()
    {
        @Override
        public boolean apply(Item item)
        {
            return item instanceof ItemWand;
        }
    });

    public static Enchantment enhancement = new EnchantmentWandEnhancement().setName("enhancement");
    public static Enchantment fireBurn = new EnchantmentFireBurn().setName("fireBurn");

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event)
    {
        registerEnchantment(enhancement, "enhancement");
        registerEnchantment(fireBurn, "fire_burn");
    }

    private static void registerEnchantment(Enchantment enchantment, String name)
    {
        ForgeRegistries.ENCHANTMENTS.register(enchantment.setRegistryName(name));
    }
}
