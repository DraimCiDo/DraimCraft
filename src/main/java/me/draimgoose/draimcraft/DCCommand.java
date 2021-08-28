package me.draimgoose.draimcraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DCCommand implements CommandExecutor {

    private final DraimCraft plugin;
    public DCCommand(final DraimCraft pl) {
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("draimcraft")) {
            if(!(sender.hasPermission("draimcraft.reload"))) {
                sender.sendMessage(ChatColor.RED + "У вас нет прав");
                return true;
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {

                    plugin.reloadConfig();
                    plugin.updateConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigReloadedMessage()));
                    return true;

                } else {
                    String wrongSyntax = ChatColor.translateAlternateColorCodes('&',
                            plugin.getIncorrectSyntaxMessage()).replace("%command%", "/draimcraft reload");
                    sender.sendMessage(wrongSyntax);
                    return true;
                }

            } else {
                String wrongSyntax = ChatColor.translateAlternateColorCodes('&',
                        plugin.getIncorrectSyntaxMessage()).replace("%command%", "/draimcraft reload");
                sender.sendMessage(wrongSyntax);
                return true;

            }

        }
        return true;
    }

}
