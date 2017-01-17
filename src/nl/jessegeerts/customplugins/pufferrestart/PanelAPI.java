package nl.jessegeerts.customplugins.pufferrestart;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jesse on 17-1-2017.
 */
public class PanelAPI {

    public void restart(){
final FileConfiguration config = Main.getPlugin().getConfig();
        try {

            URL currentUrl = new URL(config.getString("ScalesURL") + "/servers/power/restart");

            HttpURLConnection connection = (HttpURLConnection) currentUrl.openConnection();
            connection.addRequestProperty("X-Access-Server", config.getString("Server"));
            connection.addRequestProperty("X-Access-Token", config.getString("Token"));
            connection.getResponseCode();
            System.out.println(connection.getURL().toString());
        }
        catch(IOException e) {
            System.err.println(e);
        }
    }

    public void stop(){
        final FileConfiguration config = Main.getPlugin().getConfig();
        try {

            URL currentUrl = new URL(config.getString("ScalesURL") + "/servers/power/stop");

            HttpURLConnection connection = (HttpURLConnection) currentUrl.openConnection();
            connection.addRequestProperty("X-Access-Server", config.getString("Server"));
            connection.addRequestProperty("X-Access-Token", config.getString("Token"));
            connection.getResponseCode();
            System.out.println(connection.getURL().toString());
        }
        catch(IOException e) {
            System.err.println(e);
        }
    }
}
