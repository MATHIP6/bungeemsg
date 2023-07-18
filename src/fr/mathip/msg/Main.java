package fr.mathip.msg;

import fr.mathip.msg.Commands.Reply;
import fr.mathip.msg.Commands.msg;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends Plugin {
    private static Main instance;
    public Map<CommandSender, CommandSender> playerReply = new HashMap<>();
    @Override
    public void onEnable() {
        instance = this;
        getProxy().getPluginManager().registerCommand(this, new msg());
        getProxy().getPluginManager().registerCommand(this, new Reply());
    }
    public static Main getInstance(){
        return instance;
    }
}
