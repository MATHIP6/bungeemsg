package fr.mathip.msg.Commands;

import fr.mathip.msg.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Reply extends Command {
    public Reply() {
        super("r");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (args.length == 0){
            commandSender.sendMessage(new TextComponent("§c/r <message>"));
            return;
        } else if (Main.getInstance().playerReply.containsKey(commandSender)){
            CommandSender target = Main.getInstance().playerReply.get(commandSender);
            if (target != null){
                StringBuilder bc = new StringBuilder();
                for (String arg : args){
                    bc.append(" " + arg);
                }
                BaseComponent component1 = new TextComponent("§e[§6" + commandSender.getName() + " §e-> §6toi§e]§6 " + bc.toString());
                component1.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + commandSender.getName()));
                component1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("/r").create()));
                target.sendMessage(component1);
                BaseComponent component2 = new TextComponent("§e[§6 toi §e-> §6" + target.getName() + "§e]§6" + bc.toString());
                component2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + target.getName()));
                component2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("/r").create()));
                commandSender.sendMessage(component2);
                Main.getInstance().playerReply.remove(commandSender, target);
                Main.getInstance().playerReply.put(target, commandSender);
            } else {
                commandSender.sendMessage(new TextComponent("§cCe joueur est hors-ligne !"));
            }

        } else {
            commandSender.sendMessage(new TextComponent("§cPersonne vous a envoyer de message !"));
        }
    }
}
