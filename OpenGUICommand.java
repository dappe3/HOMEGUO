package me.blocklime.homegui;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class OpenGUICommand implements CommandExecutor, Listener {

    public OpenGUICommand() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Bu komut sadece oyuncular içindir!");
            return true;
        }

        Player p = (Player) sender;

        Inventory gui = Bukkit.createInventory(null, 27, "§aHome GUI");

        ItemStack homeItem = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = homeItem.getItemMeta();
        meta.setDisplayName("§aEve Işınlan");
        homeItem.setItemMeta(meta);

        gui.setItem(13, homeItem);

        p.openInventory(gui);
        return true;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (e.getClickedInventory() == null) return;
        if (!e.getView().getTitle().equals("§aHome GUI")) return;

        e.setCancelled(true);

        Player p = (Player) e.getWhoClicked();

        if (e.getSlot() == 13) {

            Location loc = (Location) Main.getInstance().getConfig()
                    .get("homes." + p.getUniqueId());

            if (loc == null) {
                p.sendMessage("§cEv konumu ayarlanmamış!");
                return;
            }

            p.teleport(loc);
            p.sendMessage("§aEve ışınlandın!");
        }
    }
}
