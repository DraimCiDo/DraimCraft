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
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerCraftListener(this), this);
        getCommand("draimcraft").setExecutor(new DCCommand(this));
    }

   public void updateConfig() {
        enabled = getConfig().getBoolean("draimcraft.enabled");
        configReloaded = getConfig().getString("draimcraft.config-reloaded");
        incorrectSyntax = getConfig().getString("draimcraft.incorrect-syntax");
        message = getConfig().getString("draimcraft.message");
        blockAllItems = getConfig().getBoolean("draimcraft.block-all-items");
        materialList = getConfig().getStringList("draimcraft.default-block-list");
        enableGroups = getConfig().getBoolean("draimcraft.enable-groups");
       groupNames = getConfig().getConfigurationSection("draimcraft.groups").getKeys(false);
    }

    public boolean isDCEnabled() {
        return enabled;
    }

    public String getConfigReloadedMessage() {
        return configReloaded;
    }

    public String getIncorrectSyntaxMessage() {
        return incorrectSyntax;
    }

    public String getNotPermittedToCraftMessage() {
        return message;
    }

    public boolean isBlockAllItemsEnabled() {
        return blockAllItems;
    }

    public List<String> getDefaultBlockList() {
        return materialList;
    }

    public boolean isGroupsEnabled() {
        return enableGroups;
    }

    public Set<String> getGroupNames() {
        return groupNames;
    }

    public List<String> getGroupMaterials(String groupName) {
        return getConfig().getStringList("draimcraft.groups." + groupName);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
