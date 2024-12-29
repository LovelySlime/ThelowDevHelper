package com.example.examplemod;

import com.example.examplemod.commands.SelectorCommand;
import com.example.examplemod.commands.SetRedstoneCommand;
import com.example.examplemod.commands.ZahyouCommand;
import com.example.examplemod.config.ConfigHandler;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = ThelowDevHelper.MODID,
        name = ThelowDevHelper.MODNAME,
        version = ThelowDevHelper.VERSION)
public class ThelowDevHelper { // select ExampleMod and hit shift+F6 to rename it

    public static final String MODID = "ThelowDevHelper";      // the id of your mod, it should never change, it is used by forge and servers to identify your mods
    public static final String MODNAME = "ThelowDevHelper";// the name of your mod
    public static final String VERSION = "1.0";           // the current version of your mod

    // this method is one entry point of you mod
    // it is called by forge when minecraft is starting
    // it is called before the other methods below
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // loads the config file from the disk
        ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // register your commands here
        ClientCommandHandler.instance.registerCommand(new SelectorCommand());
        ClientCommandHandler.instance.registerCommand(new SetRedstoneCommand());
        ClientCommandHandler.instance.registerCommand(new ZahyouCommand());

        // the ExampleKeybind has a method with the @SubscribeEvent annotation
        // for that code to run, that class needs to be registered on the MinecraftForge EVENT_BUS
        // register your other EventHandlers here
        //MinecraftForge.EVENT_BUS.register(new ExampleKeybindListener());
        //MinecraftForge.EVENT_BUS.register(new ExampleHUD());
        MinecraftForge.EVENT_BUS.register(new LocateChatHandler());

        if (Loader.isModLoaded("patcher")) {
            // this code will only run if the mod with id "patcher" is loaded
            // you can use it to load or not while modules of your mod that depends on other mods
        }

    }
}
