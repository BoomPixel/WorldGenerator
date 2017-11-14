package de.nightmoon.boompixel;

import de.nightmoon.boompixel.Commands.WorldGenerator_CMD;
import de.nightmoon.boompixel.Listeners.PlayerJoin_EVENT;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Admin on 11.06.2017.
 */
public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        registerCommands();
        registerListeners();

        for(Player all : Bukkit.getOnlinePlayers()) {
            all.setGameMode(GameMode.CREATIVE);
        }

    }

    @Override
    public void onDisable() {



    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoin_EVENT(), this);
    }

    public void registerCommands() {
        getCommand("worldgenerator").setExecutor(new WorldGenerator_CMD());
    }

    public static Main getInstance() {
        return instance;
    }
}
