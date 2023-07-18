package fr.mathip.msg.Commands;

import fr.mathip.msg.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class msg extends Command implements TabExecutor {
    public msg() {
        super("msg", "", "m");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (args.length == 1){
            commandSender.sendMessage(new TextComponent("§c/msg <player> <message>"));
            return;
        } else {
            ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[0]);
            System.out.println(target);
            if (target != null && target.isConnected()){
                StringBuilder bc = new StringBuilder();
                for (String arg : args){
                    if (arg != args[0]){
                        bc.append(" " + arg);
                    }
                }
                BaseComponent component1 = new TextComponent("§e[§6" + commandSender.getName() + " §e-> §6toi§e]§6 " + bc.toString());
                component1.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + commandSender.getName()));
                component1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("/r").create()));
                target.sendMessage(component1);
                BaseComponent component2 = new TextComponent("§e[§6 toi §e-> §6" + target.getName() + "§e]§6" + bc.toString());
                component2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + target.getName()));
                component2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("/r").create()));
                commandSender.sendMessage(component2);
                Main.getInstance().playerReply.remove(target, commandSender);
                Main.getInstance().playerReply.put(target, commandSender);
            } else {
                commandSender.sendMessage(new TextComponent("§cCe joueur est hors-ligne !"));
            }
        }
    }
    @Override
    public Iterable <String> onTabComplete(CommandSender sender, String[] args) {
        ArrayList<String> subcommands = new ArrayList<>();
        List<String> matches = new ArrayList<>();
        Collection<ProxiedPlayer> players = BungeeCord.getInstance().getPlayers();
        for (ProxiedPlayer p : players){
            subcommands.add(p.getName());
        }
        matches = subcommands.stream().filter(val -> val.startsWith(args[0])).collect(Collectors.toList());

        return matches;
    }
}
