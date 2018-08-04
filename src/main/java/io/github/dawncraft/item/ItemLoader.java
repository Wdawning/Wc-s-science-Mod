package io.github.dawncraft.item;

import io.github.dawncraft.Dawncraft;
import io.github.dawncraft.api.block.BlockSkullBase;
import io.github.dawncraft.api.item.*;
import io.github.dawncraft.block.BlockLoader;
import io.github.dawncraft.creativetab.CreativeTabsLoader;
import io.github.dawncraft.fluid.FluidLoader;
import io.github.dawncraft.potion.PotionLoader;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Register some items.
 *
 * @author QingChenW
 */
public class ItemLoader
{
    // Action
    public static final EnumAction SHOOT = EnumHelper.addAction("SHOOT");
    public static final EnumAction RELOAD = EnumHelper.addAction("RELOAD");

    // Energy
    public static Item bucketPetroleum = new ItemBucket(BlockLoader.fluidPetroleum)
            .setUnlocalizedName("petroleumBucket").setCreativeTab(CreativeTabsLoader.tabEnergy)
            .setContainerItem(Items.bucket);
    
    // Magnetism
    public static Item magnet = new Item().setUnlocalizedName("magnet").setCreativeTab(CreativeTabsLoader.tabMagnet);
    public static Item magnetIngot = new Item().setUnlocalizedName("magnetIngot")
            .setCreativeTab(CreativeTabsLoader.tabMagnet);
    public static Item magnetStick = new Item().setUnlocalizedName("magnetStick")
            .setCreativeTab(CreativeTabsLoader.tabMagnet);
    public static Item magnetBall = new ItemMagnetBall().setUnlocalizedName("magnetBall")
            .setCreativeTab(CreativeTabsLoader.tabMagnet);
    public static Item magnetCard = new ItemMagnetCard().setUnlocalizedName("magnetCard")
            .setCreativeTab(CreativeTabsLoader.tabMagnet);
    public static Item magnetDoor = new ItemMagnetDoor().setUnlocalizedName("magnetDoor")
            .setCreativeTab(CreativeTabsLoader.tabMagnet);
    
    public static final Item.ToolMaterial MAGNET_TOOL = EnumHelper.addToolMaterial("MAGNET", 2, 285, 6.0F, 2.0F, 11)
            .setRepairItem(new ItemStack(magnetIngot));
    public static final ItemArmor.ArmorMaterial MAGNET_ARMOR = EnumHelper.addArmorMaterial("MAGNET",
            Dawncraft.MODID + ":" + "magnet", 17, new int[] { 1, 5, 4, 2 }, 11);
    public static Item magnetAxe = new ItemAxe(MAGNET_TOOL).setUnlocalizedName("magnetAxe");
    public static Item magnetPickaxe = new ItemPickaxe(MAGNET_TOOL).setUnlocalizedName("magnetPickaxe");
    public static Item magnetHammer = new ItemHammer(MAGNET_TOOL).setUnlocalizedName("magnetHammer");
    public static Item magnetSpade = new ItemSpade(MAGNET_TOOL).setUnlocalizedName("magnetSpade");
    public static Item magnetHoe = new ItemHoe(MAGNET_TOOL).setUnlocalizedName("magnetHoe");
    public static Item magnetSword = new ItemSword(MAGNET_TOOL).setUnlocalizedName("magnetSword");
    public static Item magnetWand = new ItemWand(MAGNET_TOOL, 0.8F).setUnlocalizedName("magnetWand");
    public static Item magnetHelmet = new ItemArmor(MAGNET_ARMOR, MAGNET_ARMOR.ordinal(), 0)
            .setUnlocalizedName("magnetHelmet");
    public static Item magnetChestplate = new ItemArmor(MAGNET_ARMOR, MAGNET_ARMOR.ordinal(), 1)
            .setUnlocalizedName("magnetChestplate");
    public static Item magnetLeggings = new ItemArmor(MAGNET_ARMOR, MAGNET_ARMOR.ordinal(), 2)
            .setUnlocalizedName("magnetLeggings");
    public static Item magnetBoots = new ItemArmor(MAGNET_ARMOR, MAGNET_ARMOR.ordinal(), 3)
            .setUnlocalizedName("magnetBoots");
    
    // Machine
    public static Item copperIngot = new Item().setUnlocalizedName("copperIngot")
            .setCreativeTab(CreativeTabsLoader.tabMachine);
    
    // Computer
    public static Item simpleCPU = new ItemWithInfo(false).setUnlocalizedName("simpleCPU")
            .setCreativeTab(CreativeTabsLoader.tabComputer);
    public static Item advancedCPU = new ItemWithInfo(false).setUnlocalizedName("advancedCPU")
            .setCreativeTab(CreativeTabsLoader.tabComputer);
    public static Item superCPU = new ItemWithInfo(false).setUnlocalizedName("superCPU")
            .setCreativeTab(CreativeTabsLoader.tabComputer);
    
    // Science
    
    // Furniture
    
    // Food
    public static Item bottle = new Item().setUnlocalizedName("bottle").setCreativeTab(CreativeTabsLoader.tabCuisine);
    public static Item faeces = (ItemFood) new ItemFood(1, 0.0F, true)
    {
        @Override
        public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
        {
            if (!worldIn.isRemote)
            {
                player.addPotionEffect(new PotionEffect(Potion.weakness.id, 200, 1));
                player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 1));
                player.addPotionEffect(new PotionEffect(Potion.hunger.id, 200, 1));
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 1));
            }
            super.onFoodEaten(stack, worldIn, player);
        }
    }.setAlwaysEdible().setUnlocalizedName("faeces").setCreativeTab(CreativeTabsLoader.tabCuisine);
    public static Item cookedEgg = new ItemFood(2, 0.2F, false).setUnlocalizedName("eggCooked").setCreativeTab(CreativeTabsLoader.tabCuisine);
    public static Item honeyChicken = new ItemFood(8, 0.6F, true).setUnlocalizedName("chickenHoney").setCreativeTab(CreativeTabsLoader.tabCuisine);
    public static Item honeyStew = new ItemSoup(2).setUnlocalizedName("honeyStew").setCreativeTab(CreativeTabsLoader.tabCuisine);
    public static Item frogStew = new ItemSoup(4).setUnlocalizedName("frogStew").setCreativeTab(CreativeTabsLoader.tabCuisine);
    public static Item honey = new Item().setUnlocalizedName("honey").setCreativeTab(CreativeTabsLoader.tabCuisine);
    public static Item frog = new Item().setUnlocalizedName("frog").setCreativeTab(CreativeTabsLoader.tabCuisine);
    
    // Guns
    public static Item gunAK47 = new ItemGunRifle(423, 30, 69, 2, 1, 0.85F, 0.70F, 0.65F, 6.0F)
            .setUnlocalizedName("gunAK47").setCreativeTab(CreativeTabsLoader.tabWeapon);
    public static Item gunRPG = new ItemGunLauncher(28, 3, 100, -1, 1, 0.5F, 0.5F, 0.95F).setUnlocalizedName("gunRPG")
            .setCreativeTab(CreativeTabsLoader.tabWeapon);
    public static Item gunBullet = new Item().setUnlocalizedName("gunBullet")
            .setCreativeTab(CreativeTabsLoader.tabWeapon);
    public static Item gunRocket = new Item().setUnlocalizedName("gunRocket")
            .setCreativeTab(CreativeTabsLoader.tabWeapon).setMaxStackSize(16);
    
    // Magic
    public static Item magicDust = new Item().setUnlocalizedName("magicDust")
            .setCreativeTab(CreativeTabsLoader.tabMagic);
    public static Item magicBook = new ItemMagicBook().setUnlocalizedName("magicBook")
            .setCreativeTab(CreativeTabsLoader.tabMagic);
    public static Item metalEssence = new Item().setUnlocalizedName("metalEssence")
            .setCreativeTab(CreativeTabsLoader.tabMagic);
    public static Item woodEssence = new Item().setUnlocalizedName("woodEssence")
            .setCreativeTab(CreativeTabsLoader.tabMagic);
    public static Item waterEssence = new Item().setUnlocalizedName("waterEssence")
            .setCreativeTab(CreativeTabsLoader.tabMagic);
    public static Item fireEssence = new Item().setUnlocalizedName("fireEssence")
            .setCreativeTab(CreativeTabsLoader.tabMagic);
    public static Item dirtEssence = new Item().setUnlocalizedName("dirtEssence")
            .setCreativeTab(CreativeTabsLoader.tabMagic);
    
    // ColourEgg
    public static Item skull = new ItemSkullBase(new String[] {"savage", "barbarianking", "gerking"})
    {
        @Override
        public BlockSkullBase getSkullBlock()
        {
            return (BlockSkullBase) BlockLoader.skull;
        }
    }.setUnlocalizedName("skull").setCreativeTab(CreativeTabsLoader.tabColourEgg);
    public static Item gerHeart = new ItemFood(2, 1.0F, false)
    {
        @Override
        public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
        {
            if (!worldIn.isRemote)
            {
                player.addPotionEffect(new PotionEffect(PotionLoader.potionGerPower.id, 400, 2));
                player.addExperience(2000);
            }
            super.onFoodEaten(stack, worldIn, player);
        }
    }.setAlwaysEdible().setUnlocalizedName("gerHeart").setCreativeTab(CreativeTabsLoader.tabColourEgg);
    public static Item brainDead = new ItemFood(2, 8.0F, false)
    {
        @Override
        public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
        {
            if (!worldIn.isRemote)
            {
                player.addPotionEffect(new PotionEffect(PotionLoader.potionBrainDead.id, 160, 1));
                player.addExperience(29);
            }
            super.onFoodEaten(stack, worldIn, player);
        }
    }.setAlwaysEdible().setUnlocalizedName("brainDead").setCreativeTab(CreativeTabsLoader.tabColourEgg);
    public static Item funny = new Item().setUnlocalizedName("funny").setCreativeTab(CreativeTabsLoader.tabColourEgg);
    
    public static Item dj = new ItemRecordDawn("dj").setUnlocalizedName("record")
            .setCreativeTab(CreativeTabsLoader.tabColourEgg);
    public static Item wz = new ItemRecordDawn("wzsongs").setUnlocalizedName("record")
            .setCreativeTab(CreativeTabsLoader.tabColourEgg);
    
    public static final Item.ToolMaterial GOLDIAMOND = EnumHelper.addToolMaterial("GOLDIAMOND", 3, 797, 10.0F, 2.0F, 16);
    public static final Item.ToolMaterial MJOLNIR = EnumHelper.addToolMaterial("MJOLNIR", 4, 2586, 10.0F, 2.0F, 24);
    public static Item goldiamondSword = new ItemSword(ItemLoader.GOLDIAMOND).setUnlocalizedName("goldiamondSword")
            .setCreativeTab(CreativeTabsLoader.tabColourEgg);
    public static Item mjolnir = new ItemHammer(ItemLoader.MJOLNIR)
    {
        @Override
        public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
        {
            target.addPotionEffect(new PotionEffect(PotionLoader.potionParalysis.getId(), 60, 0));
            attacker.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 60, 0));

            return super.hitEntity(stack, target, attacker);
        }
    }.setUnlocalizedName("mjolnir").setCreativeTab(CreativeTabsLoader.tabColourEgg);
    
    public ItemLoader(FMLPreInitializationEvent event)
    {
        // Energy
        register(bucketPetroleum, "petroleum_bucket");
        FluidContainerRegistry.registerFluidContainer(FluidLoader.fluidPetroleum,
                new ItemStack(ItemLoader.bucketPetroleum), FluidContainerRegistry.EMPTY_BUCKET);
        
        // Magnet
        register(magnet, "magnet");
        register(magnetIngot, "magnet_ingot");
        register(magnetStick, "magnet_stick");
        register(magnetBall, "magnet_ball");
        register(magnetCard, "magnet_card");
        register(magnetDoor, "magnet_door");
        
        register(magnetAxe, "magnet_axe");
        register(magnetPickaxe, "magnet_pickaxe");
        register(magnetHammer, "magnet_hammer");
        register(magnetSpade, "magnet_shovel");
        register(magnetHoe, "magnet_hoe");
        register(magnetSword, "magnet_sword");
        register(magnetWand, "magnet_wand");
        register(magnetHelmet, "magnet_helmet");
        register(magnetChestplate, "magnet_chestplate");
        register(magnetLeggings, "magnet_leggings");
        register(magnetBoots, "magnet_boots");
        
        // Machine
        register(copperIngot, "copper_ingot");
        
        // Computer
        register(simpleCPU, "simple_CPU");
        register(advancedCPU, "advanced_CPU");
        register(superCPU, "super_CPU");
        
        // Materials
        
        // Furniture
        
        // Food
        register(bottle, "bottle");
        register(faeces, "faeces");
        register(cookedEgg, "cooked_egg");
        register(honeyChicken, "honey_chicken");
        register(honeyStew, "honey_stew");
        register(frogStew, "frog_stew");
        register(honey, "honey");
        register(frog, "frog");
        
        // Magic
        register(magicDust, "magic_dust");
        register(magicBook, "magic_book");
        register(metalEssence, "metal_essence");
        register(woodEssence, "wood_essence");
        register(waterEssence, "water_essence");
        register(fireEssence, "fire_essence");
        register(dirtEssence, "dirt_essence");
        
        // Flans
        register(gunAK47, "gun_ak47");
        register(gunBullet, "gun_bullet");
        register(gunRPG, "gun_rpg");
        register(gunRocket, "gun_rocket");
        
        // ColourEgg
        register(skull, "skull");
        register(gerHeart, "ger_heart");
        register(brainDead, "brain_dead");
        register(funny, "funny");
        
        register(dj, "record_dj");
        register(wz, "record_wzsongs");
        
        register(goldiamondSword, "goldiamond_sword");
        register(mjolnir, "mjolnir");
        
        // Tools, Weapons and Armors

    }
    
    /**
     * Register a item with a string id.
     *
     * @param item The item to be registered
     * @param name The item's string id
     */
    private static void register(Item item, String name)
    {
        GameRegistry.registerItem(item, name);
    }
}
