package io.github.dawncraft.core;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@Name("DawnCore")
@MCVersion("1.12.2")
@TransformerExclusions("io.github.dawncraft.core")
//@SortingIndex(1001)
public class DawnCorePlugin implements IFMLLoadingPlugin
{
    @Override
    public String getModContainerClass()
    {
        return "io.github.dawncraft.core.DawnCoreModContainer";
    }

    @Override
    public String getSetupClass()
    {
        return "io.github.dawncraft.core.DawnCoreSetuper";
    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[] {"io.github.dawncraft.core.asm.DawnCoreTransformer"};
    }

    /**
     * {@link DawnCoreSetuper#injectData(Map)}
     */
    @Override
    public void injectData(Map<String, Object> data) {}
}
