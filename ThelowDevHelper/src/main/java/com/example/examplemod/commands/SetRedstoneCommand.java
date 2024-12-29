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

public class SetRedstoneCommand extends CommandBase {
	private int[] playerLocate = new int[3];
	private String text = "";

    @Override
    public String getCommandName() {
        return "sr";
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
    	
    	if (args.length == 0) {
    		text = "setredstone " + playerLocate[0] + " " + playerLocate[1] + " " + playerLocate[2] ;
    		StringSelection selection = new StringSelection(text);
    		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    		sendChatMessage(EnumChatFormatting.LIGHT_PURPLE + "[!] sr : " + EnumChatFormatting.YELLOW + "現在のプレイヤー座標" + 
    				EnumChatFormatting.LIGHT_PURPLE + "へのsetredstoneを、クリップボードに貼り付けしました。");
    		
    	} else if (args[0].equalsIgnoreCase("me")) {
    		text = "setredstone " + playerLocate[0] + " " + playerLocate[1] + " " + playerLocate[2] ;
    		StringSelection selection = new StringSelection(text);
    		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    		sendChatMessage(EnumChatFormatting.LIGHT_PURPLE + "[!] sr : " + EnumChatFormatting.YELLOW + "現在のプレイヤー座標" + 
    				EnumChatFormatting.LIGHT_PURPLE + "へのsetredstoneを、クリップボードに貼り付けしました。");
    		
    	} else if (args[0].equalsIgnoreCase("p")) {
    		text = "setredstone " + LocateChatHandler.getPos1()[0] + " " + LocateChatHandler.getPos1()[1] + " " + LocateChatHandler.getPos1()[2] ;
    		StringSelection selection = new StringSelection(text);
    		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    		sendChatMessage(EnumChatFormatting.LIGHT_PURPLE + "[!] sr : " + EnumChatFormatting.YELLOW + "pos1" + 
    				EnumChatFormatting.LIGHT_PURPLE + "へのsetredstoneを、クリップボードに貼り付けしました。");
    		
    	} else if (args[0].equalsIgnoreCase("p1")) {
    		text = "setredstone " + LocateChatHandler.getPos1()[0] + " " + LocateChatHandler.getPos1()[1] + " " + LocateChatHandler.getPos1()[2] ;
    		StringSelection selection = new StringSelection(text);
    		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    		sendChatMessage(EnumChatFormatting.LIGHT_PURPLE + "[!] sr : " + EnumChatFormatting.YELLOW + "pos1" + 
    				EnumChatFormatting.LIGHT_PURPLE + "へのsetredstoneを、クリップボードに貼り付けしました。");
    		
    	} else if (args[0].equalsIgnoreCase("p2")) {
    		text = "setredstone " + LocateChatHandler.getPos2()[0] + " " + LocateChatHandler.getPos2()[1] + " " + LocateChatHandler.getPos2()[2] ;
    		StringSelection selection = new StringSelection(text);
    		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    		sendChatMessage(EnumChatFormatting.LIGHT_PURPLE + "[!] sr : " + EnumChatFormatting.YELLOW + "pos2" + 
    				EnumChatFormatting.LIGHT_PURPLE + "へのsetredstoneを、クリップボードに貼り付けしました。");
    		
    	} else {
    		sendChatMessage(EnumChatFormatting.DARK_RED + "[!] srに失敗しました。引数は[無し(player)|me(player)|p(pos1)|p1(pos1)|p2(pos2)]のみ使用可能です。");
    	}
    }
    
    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        final String[] options = new String[]{"me", "p", "p1", "p2"};
        return getListOfStringsMatchingLastWord(args, options);
    }

    private void sendChatMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
    }
    
    
}
