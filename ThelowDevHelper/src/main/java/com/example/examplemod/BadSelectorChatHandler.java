package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BadSelectorChatHandler {
	private String command = ""; // コマンドを格納する変数
	private static String replacedCommand = "";
	
	@SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();

        if (message.contains("@a") && (message.contains("delaycommand") || message.contains("sequencecommand"))) {
        	Minecraft.getMinecraft().thePlayer.playSound(
                    "random.anvil_land", // 金床の音
                    1.0F,
                    1.0F 
                );
        	sendChatMessage(EnumChatFormatting.RED + "    ■    ");
        	sendChatMessage(EnumChatFormatting.RED + "    ■    @aを使っています。@.aではありませんか？");
        	sendChatMessage(EnumChatFormatting.RED + "          詳しくは開発Discord:提案とwikiを参照してください。");
        	sendChatMessage(EnumChatFormatting.RED + "    ■    /fixselector で修正版を取得できます");
        

            // "command set:" または "コマンドを設定しました:" に一致するかチェック
            if (message.startsWith("command set") || message.startsWith("コマンドを設定しました")) {
            	

                // メッセージからコマンド部分を取得
                String cmd = message.split(" ", 2)[1].trim();

                // コマンド変数に代入
                command = cmd;

                // @a を @.a に置換
                setReplacedCommand(command.replace("@a", "@.a"));
            }
                	
                
        }
        
        
    }
    
    private void sendChatMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
    }

	public static String getReplacedCommand() {
		return replacedCommand;
	}

	public static void setReplacedCommand(String replacedCommand) {
		BadSelectorChatHandler.replacedCommand = replacedCommand;
	}

}
