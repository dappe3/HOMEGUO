package me.blocklime.homegui;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Bu komut sadece oyuncular içindir!");
            return true;
        }

        Player p = (Player) sender;
        Location loc = p.getLocation();

        Main.getInstance().getConfig().set("homes." + p.getUniqueId(), loc);
        Main.getInstance().saveConfig();

        p.sendMessage("§aEv konumun kaydedildi!");
        return true;
    }
}
