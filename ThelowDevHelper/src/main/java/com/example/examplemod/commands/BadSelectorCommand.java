package com.example.examplemod.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import com.example.examplemod.BadSelectorChatHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class BadSelectorCommand  extends CommandBase {
	private String text;
	@Override
    public String getCommandName() {
        return "fixselector";
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
    	text = BadSelectorChatHandler.getReplacedCommand();
    	
    	StringSelection selection = new StringSelection(text);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		
		sendChatMessage(EnumChatFormatting.LIGHT_PURPLE + "[!] fixselector : 最後のコマンドの修正版をクリップボードに貼り付けしました。");
    }
    
    

    private void sendChatMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
    }

}
