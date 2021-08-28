package me.draimgoose.draimcraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DCCommand implements CommandExecutor {

    private final DraimCraft plugin;
    public DCCommand(final DraimCraft pl) {
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

}
