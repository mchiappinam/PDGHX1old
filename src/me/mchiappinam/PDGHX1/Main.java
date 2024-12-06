package me.mchiappinam.pdghx1;

import java.io.File;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	protected static Economy econ = null;
	public String desafiar = null;
	public String negar = null;
	
	@Override
    public void onEnable() {
		getServer().getPluginManager().registerEvents(new Comando(this), this);
		
		getServer().getPluginCommand("x1").setExecutor(new Comando(this));
		
		File file = new File(getDataFolder(),"config.yml");
		if(!file.exists()) {
			try {
				saveResource("config_template.yml",false);
				File file2 = new File(getDataFolder(),"config_template.yml");
				file2.renameTo(new File(getDataFolder(),"config.yml"));
			}catch(Exception e) {}
		}
		if(!setupEconomy()) {
			getLogger().warning("ERRO: Vault (Economia) nao encontrado!");
			getLogger().warning("Desativando o plugin...");
			getServer().getPluginManager().disablePlugin(this);
        }
		getServer().getConsoleSender().sendMessage("§3[PDGHX1] §2ativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHX1] §2Acesse: http://pdgh.net/");
	}
	
	@Override
    public void onDisable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHX1] §2desativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHX1] §2Acesse: http://pdgh.net/");
    }
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
