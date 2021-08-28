package me.draimgoose.draimcraft;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Set;

public final class DraimCraft extends JavaPlugin {

    private boolean enabled;
    private String configReloaded;
    private String incorrectSyntax;
    private String message;
    private boolean blockAllItems;
    private List<String> materialList;
    private Boolean enableGroups;
    private Set<String> groupNames;

    @Override
    public void onEnable() {
        // Plugin startup logic
        updateConfig();
    }

    private void updateConfig() {
        enabled = getConfig().getBoolean("draimcraft.enabled");
        configReloaded = getConfig().getString("draimcraft.config-reloaded");
        incorrectSyntax = getConfig().getString("draimcraft.incorrect-syntax");
        message = getConfig().getString("draimcraft.message");
        blockAllItems = getConfig().getBoolean("draimcraft.block-all-items");
        materialList = getConfig().getStringList("draimcraft.default-block-list");
        enableGroups = getConfig().getBoolean("draimcraft.enable-groups");
        groupNames = getConfig().getConfigurationSection("draimcraft.group").getKeys(false);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
