package com.example.examplemod;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LocateChatHandler {
	// 座標データを保存する配列
    private static int[] pos1 = new int[3]; // x, y, z
    private static int[] pos2 = new int[3];

    // チャットイベントの監視
    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();

        // 正規表現パターン: 座標を抽出
        Pattern pattern = Pattern.compile("\\(FAWE\\) pos(\\d) set to \\((-?\\d+), (-?\\d+), (-?\\d+)\\)");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            int posIndex = Integer.parseInt(matcher.group(1)); // pos1 or pos2
            double x = Double.parseDouble(matcher.group(2));
            double y = Double.parseDouble(matcher.group(3));
            double z = Double.parseDouble(matcher.group(4));

            // pos1 または pos2 に座標を格納
            if (posIndex == 1) {
                getPos1()[0] = (int)x;
                getPos1()[1] = (int)y;
                getPos1()[2] = (int)z;
                sendChatMessage(EnumChatFormatting.LIGHT_PURPLE + "pos1 の座標更新 > " + EnumChatFormatting.YELLOW + "(" + x + "/" + y + "/" + z + ")");
                
            } else if (posIndex == 2) {
                getPos2()[0] = (int)x;
                getPos2()[1] = (int)y;
                getPos2()[2] = (int)z;
                sendChatMessage(EnumChatFormatting.LIGHT_PURPLE + "pos2 の座標更新 > " + EnumChatFormatting.YELLOW + "(" + x + "/" + y + "/" + z + ")");
                
            }
        }
    }
    
    private void sendChatMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
    }

	public static int[] getPos1() {
		return pos1;
	}

	public void setPos1(int[] pos1) {
		this.pos1 = pos1;
	}

	public static int[] getPos2() {
		return pos2;
	}

	public void setPos2(int[] pos2) {
		this.pos2 = pos2;
	}
}
