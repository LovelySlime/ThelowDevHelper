package com.example.examplemod.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import com.example.examplemod.LocateChatHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ZahyouCommand  extends CommandBase{
	private int[] playerLocate = new int[3];
	private int[] locate = new int[3];
	private boolean arg1Success = false;
	private boolean arg2Success = false;
	
	private String text = "";


    @Override
    public String getCommandName() {
        return "pos";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName();
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
    	Minecraft mc = Minecraft.getMinecraft();
    	BlockPos pos = mc.thePlayer.getPosition();

    	playerLocate[0] = pos.getX();
    	playerLocate[1] = pos.getY();
    	playerLocate[2] = pos.getZ();
    	
    	if (args.length == 0 || args[0].equalsIgnoreCase("me")) {
    		locate[0] = playerLocate[0];
    		locate[1] = playerLocate[1];
    		locate[2] = playerLocate[2];
    		arg1Success = true;
    	} else if (args[0].equalsIgnoreCase("p") || args[0].equalsIgnoreCase("p1") || args[0].equalsIgnoreCase("pos1")) {
    		locate[0] = LocateChatHandler.getPos1()[0];
    		locate[1] = LocateChatHandler.getPos1()[1];
    		locate[2] = LocateChatHandler.getPos1()[2];
    		arg1Success = true;
    	} else if (args[0].equalsIgnoreCase("p2") || args[0].equalsIgnoreCase("pos2")) {
    		locate[0] = LocateChatHandler.getPos2()[0];
    		locate[1] = LocateChatHandler.getPos2()[1];
    		locate[2] = LocateChatHandler.getPos2()[2];
    		arg1Success = true;
    	} else {
    		arg1Success = false;
    		sendChatMessage(EnumChatFormatting.DARK_RED + "[!] posに失敗しました。引数1は[無し(player)|me(player)|p(pos1)|p1(pos1)|p2(pos2)]のみ使用可能です。");
    	}
    	
    	if (arg1Success && (args.length <= 1 || args[1].equalsIgnoreCase("normal") || args[1].equalsIgnoreCase("n"))) {
    		text = locate[0] + " " + locate[1] + " " + locate[2] ;
    		arg2Success = true;
    	} else if (arg1Success && (args[1].equalsIgnoreCase("t") || args[1].equalsIgnoreCase("tl") || args[1].equalsIgnoreCase("thelow"))) {
    		text = "dungeon:" + locate[0] + "," + locate[1] + "," + locate[2];
    		arg2Success = true;
    	} else {
    		arg2Success = false;
    		if (arg1Success) {
    			sendChatMessage(EnumChatFormatting.DARK_RED + "[!] posに失敗しました。引数2は[無し/n/normal(normal)|t/tl/thelow(thelow)]のみ使用可能です。");
    		}
    	}
    	
    	if (arg1Success && arg2Success) {
    		StringSelection selection = new StringSelection(text);
    		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    		sendChatMessage(EnumChatFormatting.LIGHT_PURPLE + "pos : 指定したコンディションの座標を、クリップボードに貼り付けしました。");
    	}
    }

    private void sendChatMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
    }
    
    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        // 第1引数の場合
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "me", "p1", "p2", "p");
        }

        // 第2引数の場合
        if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, "normal", "thelow", "n", "t", "tl");
        }

        return null;
    }
}
