package me.blocklime.homegui;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("HomeGUI aktif!");

        getCommand("homegui").setExecutor(new OpenGUICommand());
        getCommand("sethomegui").setExecutor(new SetHomeCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("HomeGUI kapatıldı!");
    }

    public static Main getInstance() {
        return instance;
    }
}
