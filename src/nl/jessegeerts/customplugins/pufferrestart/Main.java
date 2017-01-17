package nl.jessegeerts.customplugins.pufferrestart;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Created by Jesse on 17-1-2017.
 */
public class Main extends JavaPlugin {
    private static Plugin plugin;
    public static Plugin getPlugin(){
        return plugin;
    }

    public void onEnable(){
        plugin=this;
        config();
        getLogger().info(getDescription().getName() + " Is made by Jesse Geerts");
        getLogger().warning("This plugin may never being sold! If it was sold by someone to you please fill a scam report and chargeback!.");

    }
    public void onDisable(){
        plugin=null;
    }
        private void config(){
        getConfig().addDefault("ScalesURL", "https://myscales.pufferpanel.com:5656/");
        getConfig().addDefault("Server", "Your-Access-Server");
        getConfig().addDefault("Token", "Your-Access-Token");
        getConfig().addDefault("Permission.Commands", "pufferrestart.plugin");
        getConfig().addDefault("NoPermission", "&cYou don't have permission for this command.");
        getConfig().addDefault("RestartAnnounce", "&4This server is going to restart within 5 seconds..");
        getConfig().addDefault("StopAnnounce", "&4This server is going to stop within 5 seconds..");
        saveConfig();
        }

    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args){
        if (cmd.getName().equalsIgnoreCase("pufferrestart")) {
            if(args.length==0){
                sender.sendMessage("§aPufferRestart v"+ getDescription().getVersion());
                sender.sendMessage("§aCreated by Jesse Geerts (www.spigotmc.org/members/20260/)");
                sender.sendMessage("§cCommands:\n/pufferrestart restart\n/pufferrestart stop\n/pufferrestart quickrestart\n/pufferrestart /quickstop\n/pufferrestart reloadconfig");
                sender.sendMessage("The quick's will execute the command immediatly and will not announce that the server is going to restart or stop within 5 seconds.");
            }
            BukkitScheduler scheduler = Bukkit.getScheduler();
            if(args.length==1){
                if(sender.hasPermission(getConfig().getString("Permission.Commands"))) {
                    if(args[0].equalsIgnoreCase("restart")){
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("RestartAnnounce")));
                        scheduler.runTaskLater(this, new Runnable() {
                            @Override
                            public void run() {
                                new PanelAPI().restart();
                            }
                        },300);
                    }
                    if(args[0].equalsIgnoreCase("stop")){
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("stoptAnnounce")));
                        scheduler.runTaskLater(this, new Runnable() {

                            @Override
                            public void run() {
                                new PanelAPI().stop();
                            }
                        },300);
                    }
                    if(args[0].equalsIgnoreCase("reloadconfig")){
                        reloadConfig();
                        sender.sendMessage("I hope you didn't mess the config up... I would recommend you to check the console log to being ensure that the plugin works properly");
                    }
                    if(args[0].equalsIgnoreCase("quickrestart")){
                        new PanelAPI().restart();
                    }
                    if(args[0].equalsIgnoreCase("quickstop")){
                        new PanelAPI().stop();
                    }
                }else{
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("NoPermission")));
                }
            }
        }


        return true;
    }
}
