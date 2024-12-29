package com.example.examplemod.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import com.example.examplemod.LocateChatHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class SelectorCommand extends CommandBase {
	
	private int[] posDiff = new int[3]; // 座標比較用
	private String selector = "";
	private String zahyou = "";

    @Override
    public String getCommandName() {
        return "sl";
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
    	try {
        	posDiff[0] = LocateChatHandler.getPos2()[0] - LocateChatHandler.getPos1()[0];
        	posDiff[1] = LocateChatHandler.getPos2()[1] - LocateChatHandler.getPos1()[1];
        	posDiff[2] = LocateChatHandler.getPos2()[2] - LocateChatHandler.getPos1()[2];
        	
        	selector = "@a[x=" + LocateChatHandler.getPos1()[0] + ",dx=" + posDiff[0] + 
        			",y=" + LocateChatHandler.getPos1()[1] + ",dy=" + posDiff[1] +
        			",z=" + LocateChatHandler.getPos1()[2] + ",dz=" + posDiff[2] + "]";
        	
        	zahyou = "(" + LocateChatHandler.getPos1()[0] + " " + LocateChatHandler.getPos1()[1] + " " + LocateChatHandler.getPos1()[2] + ") - (" +
        			LocateChatHandler.getPos2()[0] + " " + LocateChatHandler.getPos2()[1] + " " + LocateChatHandler.getPos2()[2] +  ")";
        	
        	StringSelection selection = new StringSelection(selector);
    		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    		
    		sendChatMessage(EnumChatFormatting.LIGHT_PURPLE + "[!] sl : セレクター範囲を計算し、クリップボードに貼り付けしました。" +  
    				EnumChatFormatting.YELLOW + zahyou);
    	}catch (Exception e) {
    		sendChatMessage(EnumChatFormatting.DARK_RED + "[!] slを計算できませんでした。pos1,pos2を先に出していますか？");
    	}
    }
    
    

    private void sendChatMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
    }
}
