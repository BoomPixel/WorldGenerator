package de.nightmoon.boompixel.Commands;

import de.nightmoon.boompixel.FlatWorldGenerator;
import de.nightmoon.boompixel.Utils.Variablen;
import de.nightmoon.boompixel.VoidWorldGenerator;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Created by Jo-Jo-Computer on 11.06.2017.
 */
public class WorldGenerator_CMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if(!(sender instanceof Player)) {
            sender.sendMessage(Variablen.ONLYPLAYER);
            return true;
        }

        if(player.hasPermission("nightmoon.vwg")) {

            if(args.length == 0) {
                sendHelp(player);
            } else if(args.length == 3) {
                if(args[0].equalsIgnoreCase("create")) {

                    if(args[1].equalsIgnoreCase("void")) {

                        String WORLDNAME = args[2];

                        if(Bukkit.getWorld(WORLDNAME) == null) {

                            try {

                                player.sendMessage(Variablen.PREFIX + "§cDie Welt wird erstellt...");
                                WorldCreator wc = new WorldCreator(WORLDNAME);
                                wc.generateStructures(false);
                                wc.generator(new VoidWorldGenerator());
                                Bukkit.getServer().createWorld(wc);

                                Bukkit.getWorld(WORLDNAME).getBlockAt(0, 100, 0).setType(Material.GOLD_BLOCK);
                                player.sendMessage(Variablen.PREFIX + "§7Die Welt wurde §aerfolgreich §7erstellt!");
                            } catch (Exception e) {
                                player.sendMessage(Variablen.PREFIX + "§cFehler!");
                                e.printStackTrace();
                            }

                        } else {
                            player.sendMessage(Variablen.PREFIX + "§cDiese Welt existiert bereits!");
                        }

                    } else if(args[1].equalsIgnoreCase("flat")) {
                        String WORLDNAME = args[2];

                        if(Bukkit.getWorld(WORLDNAME) == null) {

                            try {

                                player.sendMessage(Variablen.PREFIX + "§cDie Welt wird erstellt...");
                                WorldCreator wc = new WorldCreator(WORLDNAME);
                                wc.generateStructures(false);
                                wc.generator(new FlatWorldGenerator());
                                Bukkit.getServer().createWorld(wc);

                                Bukkit.getWorld(WORLDNAME).getBlockAt(0, 100, 0).setType(Material.GOLD_BLOCK);
                                player.sendMessage(Variablen.PREFIX + "§7Die Welt wurde §aerfolgreich §7erstellt!");
                            } catch (Exception e) {
                                player.sendMessage(Variablen.PREFIX + "§cFehler!");
                                e.printStackTrace();
                            }

                        } else {
                            player.sendMessage(Variablen.PREFIX + "§cDiese Welt existiert bereits!");
                        }
                    }



                }

            } else if(args.length == 2) {
                if(args[0].equalsIgnoreCase("tp")) {
                    String WORLDNAME = args[1];
                    if(!(Bukkit.getWorld(WORLDNAME) == null)) {
                        if(player.getWorld() != Bukkit.getWorld(WORLDNAME)) {
                            player.teleport(new Location(Bukkit.getWorld(WORLDNAME), 0, 101, 0));
                            player.setGameMode(GameMode.CREATIVE);
                        } else {
                            player.sendMessage(Variablen.PREFIX + "§cDu bist bereits in dieser Welt!");
                        }
                    } else {
                        player.sendMessage(Variablen.PREFIX + "§cDiese Welt existiert nicht!");
                    }

                } else if(args[0].equalsIgnoreCase("remove")) {
                    String WORLDNAME = args[1];
                    if(Bukkit.getWorld(WORLDNAME) != null) {

                        if(player.getWorld() != Bukkit.getWorld(WORLDNAME)) {
                            File f = new File(WORLDNAME);
                            player.sendMessage(Variablen.PREFIX + "§cWelt wird gelöscht...");
                            Bukkit.getServer().unloadWorld(WORLDNAME, false);
                            RemoveMap(f);
                            player.sendMessage(Variablen.PREFIX + "§7Die welt wurde §aerfolgreich §7gelöscht!");
                        } else {
                            player.sendMessage(Variablen.PREFIX + "§cDa du in der angegeben Welt bist, kann diese nicht gelöscht werden!");
                        }
                    } else {
                        player.sendMessage(Variablen.PREFIX + "§cDiese Welt existiert nicht!");
                    }

                }
            }

        } else {
            player.sendMessage(Variablen.NOPERM);
        }


        return false;
    }

    public boolean RemoveMap(File dir){
        if (dir.isDirectory()){
            File[] files = dir.listFiles();
            for (File aktFile: files){
                RemoveMap(aktFile);
            }

        }
        return dir.delete();
    }

    public void sendHelp(Player player) {
        player.sendMessage("§8§m-----------§r§8┃ §bVoidWorldGenerator §8§m┃-----------");
        player.sendMessage("");
        player.sendMessage(Variablen.PREFIX + "§6/wg create §8[§aFlat§7:§aVoid§8] §8[§aName§8] §8» §7Erstelle eine leere Map.");
        player.sendMessage(Variablen.PREFIX + "§6/wg tp §8[§aName§8] §8» §7Teleportiere dich zu einer Welt.");
        player.sendMessage(Variablen.PREFIX + "§6/wg remove §8[§aName§8] §8» §7Entferne eine Welt.");
        player.sendMessage("");
        player.sendMessage("§8§m-----------§r§8┃ §bVoidWorldGenerator §8§m┃-----------");
    }

}
