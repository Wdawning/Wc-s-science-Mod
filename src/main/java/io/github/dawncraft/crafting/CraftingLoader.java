package io.github.dawncraft.crafting;

import io.github.dawncraft.block.BlockLoader;
import io.github.dawncraft.item.ItemLoader;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Register recipes, smelting and fuels.
 *
 * @author QingChenW
 */
public class CraftingLoader
{
    public CraftingLoader(FMLInitializationEvent event)
    {
        registerRecipe();
        registerSmelting();
        registerFuel();
    }

    private static void registerRecipe()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.magnet), new Object[]
                {
                        "B R", "A A", "AAA", 'A', "ingotMagnet", 'B', new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeDamage()), 'R', Items.redstone
                }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.magnetBlock), new Object[]
                {
                        "###", "###", "###", '#', "ingotMagnet"
                }));
        GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.magnetIngot, 9), BlockLoader.magnetBlock);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.magnetStick, 4), new Object[]
                {
                        "#", "#", '#', "ingotMagnet"
                }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.magnetBall, 4), new Object[]
                {
                        "#", '#', "ingotMagnet"
                }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.magnetCard, 1), new Object[]
                {
                        "#*", "**", '#', "ingotMagnet", '*', Items.paper
                }));// TODO 改成和书一样的无序合成
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.magnetDoor, 3), new Object[]
                {
                        "## ", "## ", "## ", '#', "ingotMagnet"
                }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.magnetRail, 8), new Object[]
                {
                        "# #", "#*#", "#M#", '#', Items.iron_ingot , '*', Items.stick, 'M', ItemLoader.magnet
                }));
        
        GameRegistry.addRecipe(new ItemStack(BlockLoader.simpleComputer, 2), new Object[]
                {
                        "###", "# #", "###", '#', Items.iron_ingot
                });
        GameRegistry.addRecipe(new ItemStack(BlockLoader.advancedComputer, 2), new Object[]
                {
                        "###", "# #", "###", '#', Items.gold_ingot
                });
        GameRegistry.addRecipe(new ItemStack(BlockLoader.superComputer, 2), new Object[]
                {
                        "###", "# #", "###", '#', Items.diamond
                });
        //Food
        
        //Tools
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.magnetSword), new Object[]
                {
                        " # ", " # ", " * ", '#', "ingotMagnet", '*', ItemLoader.magnetStick
                }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.magnetWand), new Object[]
                {
                        "#*#", " * ", " * ", '#', "ingotMagnet", '*', ItemLoader.magnetStick
                }));
        GameRegistry.addRecipe(new ItemStack(ItemLoader.goldiamondSword), new Object[]
                {
                        " % ", " & ", " * ", '%', Items.gold_ingot, '&', Items.diamond, '*', Items.stick
                });
        // Armors
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.magnetHelmet), new Object[]
                {
                        "###", "# #", '#', "ingotMagnet"
                }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.magnetChestplate), new Object[]
                {
                        "# #", "###", "###", '#', "ingotMagnet"
                }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.magnetLeggings), new Object[]
                {
                        "###", "# #", "# #", '#', "ingotMagnet"
                }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.magnetBoots), new Object[]
                {
                        "# #", "# #", '#', "ingotMagnet"
                }));
    }

    private static void registerSmelting()
    {
        GameRegistry.addSmelting(BlockLoader.magnetOre, new ItemStack(ItemLoader.magnetIngot), 0.7F);
        GameRegistry.addSmelting(Items.egg, new ItemStack(ItemLoader.cakeEgg), 0.3F);
    }

    private static void registerFuel()
    {
        GameRegistry.registerFuelHandler(new IFuelHandler()
        {
            @Override
            public int getBurnTime(ItemStack fuel)
            {
                return ItemLoader.bucketPetroleum != fuel.getItem() ? 0 : 25600;
            }
        });
    }
}
