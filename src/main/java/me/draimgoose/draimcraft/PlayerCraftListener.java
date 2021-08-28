package me.draimgoose.draimcraft;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerCraftListener implements Listener {

    private final DraimCraft plugin;
    public PlayerCraftListener(final DraimCraft pl) {
        this.plugin = pl;
    }

    @EventHandler
    public void onPlayerCraft(CraftItemEvent event) {

        if (event.getWhoClicked().hasPermission("draimcraft.bypass")) return;

        boolean dcEnabled = plugin.isDCEnabled();

        if (!(dcEnabled)) return;

        boolean blockAll = plugin.isBlockAllItemsEnabled();

        if (blockAll) {

            String msg = ChatColor.translateAlternateColorCodes('&', plugin.getNotPermittedToCraftMessage())
                    .replace("%item%", event.getCurrentItem().getType().name().toLowerCase());
            event.setCancelled(true);
            event.getWhoClicked().sendMessage(msg);

        } else {
            List<Material> materialList = new ArrayList<>();

            for (String mat : plugin.getDefaultBlockList()) {
                try {
                    materialList.add(Material.valueOf(mat));
                } catch (Exception e) {
                    plugin.getLogger().info("Список материалов в конфигурации содержит значение, которое не распознается. " +
                            "Убедитесь, что вы используете значения из https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html");
                    return;
                }
            }
            for (Material indMat : materialList) {
                if (event.getCurrentItem().getType() == indMat) {
                    String msg = ChatColor.translateAlternateColorCodes('&', plugin.getNotPermittedToCraftMessage())
                            .replace("%item%", event.getCurrentItem().getType().name().toLowerCase());
                    event.setCancelled(true);
                    event.getWhoClicked().sendMessage(msg);
                }
            }

            if (!(plugin.isGroupsEnabled())) {
                return;
            }
            for (String group : plugin.getGroupNames()) {
                if (event.getWhoClicked().hasPermission("draimcraft.groups." + group)) {

                    List<Material> matGroupList = new ArrayList<>();

                    for (String mat : plugin.getGroupMaterials(group)) {
                        try {
                            matGroupList.add(Material.valueOf(mat));
                        } catch (Exception e) {
                            plugin.getLogger().info("Список материалов в группе " + group + " содержит значение, которое не распознается. " +
                                    "Убедитесь, что вы используете значения из https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html");
                            return;
                        }
                    }
                    for (Material inMat : matGroupList) {
                        if (event.getCurrentItem().getType() == inMat) {
                            String msg = ChatColor.translateAlternateColorCodes('&', plugin.getNotPermittedToCraftMessage())
                                    .replace("%item%", event.getCurrentItem().getType().name().toLowerCase());
                            event.setCancelled(true);
                            event.getWhoClicked().sendMessage(msg);
                        }
                    }

                }
            }

        }

    }

}
