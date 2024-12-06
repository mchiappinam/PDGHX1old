package me.mchiappinam.pdghx1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Comando implements CommandExecutor, Listener {
	int tdelayTeleportArena;
	int tdelayDesafiar;
	int tdelayTeleportSpawn;
	int tdelayArenaInvincibility;
	int tdelayArenaTimeLimit;
	int posTeleportArena = 0;
	boolean iniciado = false;
	boolean dentroArena = false;
	List<String> arraydesafiar = new ArrayList<String>();
	List<String> arraynegar = new ArrayList<String>();
	
	private Main plugin;
	public Comando(Main main) {
		plugin=main;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (e.getPlayer().getWorld().getName().equalsIgnoreCase("world_x1")) {
			e.getPlayer().getServer().dispatchCommand(e.getPlayer().getServer().getConsoleSender(), ""+plugin.getConfig().getString("CmdSpawn").replaceAll("@player", e.getPlayer().getName()));
			e.getPlayer().sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §fVocê se logou no mundo de x1 e foi teleportado para o spawn!");
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (iniciado) {
			double taxa = plugin.getConfig().getDouble("TaxaMoney");
			Player desafiar = plugin.getServer().getPlayer(plugin.desafiar);
			Player negar = plugin.getServer().getPlayer(plugin.negar);
			if(arraydesafiar.contains(e.getPlayer().getName())) {
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+e.getPlayer().getName()+" §cdesconectou-se depois do x1 começar.");
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+negar.getName()+" §6venceu o x1.");
				Main.econ.depositPlayer(negar.getName(), taxa*2);
	        	somPigDeathAll();
				delayTeleportSpawn(negar);
				clearPlayers();
			}else if(arraynegar.contains(e.getPlayer().getName())) {
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+e.getPlayer().getName()+" §cdesconectou-se depois do x1 começar.");
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+desafiar.getName()+" §6venceu o x1.");
				Main.econ.depositPlayer(desafiar.getName(), taxa*2);
	        	somPigDeathAll();
				delayTeleportSpawn(desafiar);
	        	clearPlayers();
			}
		}else if (!iniciado) {
			double taxa = plugin.getConfig().getDouble("TaxaMoney");
			Player desafiar = plugin.getServer().getPlayer(plugin.desafiar);
			Player negar = plugin.getServer().getPlayer(plugin.negar);
			if(arraydesafiar.contains(e.getPlayer().getName())) {
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+e.getPlayer().getName()+" §cdesconectou-se antes do x1 começar.");
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §fTaxa devolvida e x1 cancelado.");
				Main.econ.depositPlayer(negar.getName(), taxa);
				delayTeleportSpawn(negar);
				clearPlayers();
			}else if(arraynegar.contains(e.getPlayer().getName())) {
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+e.getPlayer().getName()+" §cdesconectou-se antes do x1 começar.");
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §fTaxa devolvida e x1 cancelado.");
				Main.econ.depositPlayer(desafiar.getName(), taxa);
				delayTeleportSpawn(desafiar);
	        	clearPlayers();
			}
		}
	}
		
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e) {
		if (iniciado) {
			double taxa = plugin.getConfig().getDouble("TaxaMoney");
			Player desafiar = plugin.getServer().getPlayer(plugin.desafiar);
			Player negar = plugin.getServer().getPlayer(plugin.negar);
			if(arraydesafiar.contains(e.getPlayer().getName())) {
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+e.getPlayer().getName()+" §cdesconectou-se depois do x1 começar.");
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+negar.getName()+" §6venceu o x1.");
				Main.econ.depositPlayer(negar.getName(), taxa*2);
	        	somPigDeathAll();
				delayTeleportSpawn(negar);
				clearPlayers();
			}else if(arraynegar.contains(e.getPlayer().getName())) {
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+e.getPlayer().getName()+" §cdesconectou-se depois do x1 começar.");
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+desafiar.getName()+" §6venceu o x1.");
				Main.econ.depositPlayer(desafiar.getName(), taxa*2);
	        	somPigDeathAll();
				delayTeleportSpawn(desafiar);
	        	clearPlayers();
			}
		}else if (!iniciado) {
			double taxa = plugin.getConfig().getDouble("TaxaMoney");
			Player desafiar = plugin.getServer().getPlayer(plugin.desafiar);
			Player negar = plugin.getServer().getPlayer(plugin.negar);
			if(arraydesafiar.contains(e.getPlayer().getName())) {
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+e.getPlayer().getName()+" §cdesconectou-se antes do x1 começar.");
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §fTaxa devolvida e x1 cancelado.");
				Main.econ.depositPlayer(negar.getName(), taxa);
				delayTeleportSpawn(negar);
				clearPlayers();
			}else if(arraynegar.contains(e.getPlayer().getName())) {
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+e.getPlayer().getName()+" §cdesconectou-se antes do x1 começar.");
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §fTaxa devolvida e x1 cancelado.");
				Main.econ.depositPlayer(desafiar.getName(), taxa);
				delayTeleportSpawn(desafiar);
	        	clearPlayers();
			}
		}
	}

	@EventHandler
	private void onDeath(PlayerDeathEvent e) {
        double taxa = plugin.getConfig().getDouble("TaxaMoney");
		Player desafiar = plugin.getServer().getPlayer(plugin.desafiar);
		Player negar = plugin.getServer().getPlayer(plugin.negar);
		Player p = e.getEntity().getPlayer();
	  		if(e.getEntity().getKiller() instanceof Player) {
	  			Player killer = e.getEntity().getKiller();
	  			if((iniciado) && (dentroArena)) {
	  				if(arraydesafiar.contains(killer.getName())) {
	  				    World pWorld = p.getWorld();
	  				    pWorld.strikeLightningEffect(p.getLocation());
						plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §c§l"+killer.getName()+" §fmatou §c§l"+p.getName());
						plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+killer.getName()+" §6venceu o x1.");
						Main.econ.depositPlayer(killer.getName(), taxa*2);
			        	somPigDeathAll();
						delayTeleportSpawn(killer);
			        	clearPlayers();
	  				}else if(plugin.desafiar.contains(p.getName())){
	  					
	  				}
	  			}else if(arraydesafiar.contains(p.getName()) && (iniciado) && (dentroArena)) {
	  				World pWorld = p.getWorld();
	  				pWorld.strikeLightningEffect(p.getLocation());
	  				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §c§l"+p.getName()+" §fmorreu");
	  				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+negar.getName()+" §6venceu o x1.");
	  				somPigDeathAll();
	  				Main.econ.depositPlayer(negar.getName(), taxa*2);
	  				delayTeleportSpawn(negar);
	  				clearPlayers();
	  			}else if(arraynegar.contains(p.getName()) && (iniciado) && (dentroArena)) {
	  				World pWorld = p.getWorld();
	  				pWorld.strikeLightningEffect(p.getLocation());
	  				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §c§l"+p.getName()+" §fmorreu");
	  				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+desafiar.getName()+" §6venceu o x1.");
	  				somPigDeathAll();
	  				Main.econ.depositPlayer(desafiar.getName(), taxa*2);
	  				delayTeleportSpawn(desafiar);
	  				clearPlayers();
			}
	  		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("x1")) {
			if(args.length==0) {
				sendHelp(sender);
				return true;
			}
			if(args[0].equalsIgnoreCase("desafiar")) {
				if(args.length<2) {
					sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cUse /x1 desafiar <nick>");
					return true;
				}
				if(sender.getName().contains(args[1])) {
					sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cVocê não pode se desafiar.");
					return true;
				}
				String nome = args[1];
		        double taxa = plugin.getConfig().getDouble("TaxaMoney");
				Player player = plugin.getServer().getPlayerExact(args[1]);
		        if (iniciado) {
					sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cUm desafio já está acontecendo.");
				    return true;
				}
		        if (!(plugin.desafiar == null) || (!(plugin.negar == null))) {
					sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cAlguém já foi desafiado.");
				    return true;
				}
				if(player == null) {
				    sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cO jogador não está online.");
				    return true;
				}
		        if (!(Main.econ.getBalance(sender.getName()) >= taxa)) {
				    sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cVocê não tem money suficiente.");
				    sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cMoney necessário: §a$"+taxa+"§c.");
				    return true;
		        }
		        if (!(Main.econ.getBalance(nome) >= taxa)) {
				    sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cO jogador §a"+nome+" §cnão tem money suficiente.");
				    sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cMoney necessário: §a$"+taxa+"§c.");
				    return true;
		        }
		        	arraydesafiar.clear();
		        	arraynegar.clear();
		        	plugin.desafiar = sender.getName();
			        plugin.negar = nome;
			        arraydesafiar.add(sender.getName());
			        arraynegar.add(nome);
		        	Main.econ.withdrawPlayer(sender.getName(), taxa);
					plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+nome+" §ffoi desafiado pelo §a"+sender.getName());
					plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §fTaxa: §a$"+taxa);
					plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §fPrêmio: §a$"+taxa*2);
					delayDesafiar();
				return true;
			}else if(args[0].equalsIgnoreCase("negar")) {
		        if (iniciado) {
					sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cUm desafio já está acontecendo.");
				    return true;
				}
		        if (!(arraynegar.contains(sender.getName()))) {
					sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cVocê não foi desafiado.");
				    return true;
				}
		        double taxa = plugin.getConfig().getDouble("TaxaMoney");
				Player desafiar = plugin.getServer().getPlayer(plugin.desafiar);
				Player negar = plugin.getServer().getPlayer(plugin.negar);
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+negar.getName()+" §fnegou o desafio para §a"+desafiar.getName());
	        	Main.econ.depositPlayer(desafiar.getName(), taxa);
				cAllTasks();
	        	clearPlayers();
				return true;
			}else if(args[0].equalsIgnoreCase("aceitar")) {
		        double taxa = plugin.getConfig().getDouble("TaxaMoney");
		        if (iniciado) {
					sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cUm desafio já está acontecendo.");
				    return true;
				}
		        if (arraynegar.contains(sender.getName())) {
		        }else{
					sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cVocê não foi desafiado.");
				    return true;
				}
		        if (!(Main.econ.getBalance(sender.getName()) >= taxa)) {
				    sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cVocê não tem money suficiente.");
				    sender.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cMoney necessário: §a$"+taxa+"§c.");
				    return true;
		        }
                if(((Player)sender).isInsideVehicle()) {
				     sender.sendMessage("§3§l[Gladiador] §cVocê está dentro de um veículo!");
				     return true;
				}
                if(((Player)sender).isDead()) {
				     sender.sendMessage("§3§l[Gladiador] §cVocê está morto!");
				     return true;
				}
				Player desafiar = plugin.getServer().getPlayer(plugin.desafiar);
				Player negar = plugin.getServer().getPlayer(plugin.negar);
		        iniciado = true;
				Main.econ.withdrawPlayer(sender.getName(), taxa);
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+negar.getName()+" §faceitou o desafio para §a"+desafiar.getName());
				cAllTasks();
				delayTeleportArena();
				return true;
			}
			sendHelp(sender);
			return true;
		}
		return false;
	}
	
	private void sendHelp(CommandSender sender) {
        double taxa = plugin.getConfig().getDouble("TaxaMoney");
		sender.sendMessage("§3§lPDGH X1 - Comandos:");
		sender.sendMessage("§2/x1 desafiar <nick> -§a- Desafia alguém para o x1.");
		sender.sendMessage("§2/x1 aceitar -§a- Aceita o x1 desafiado.");
		sender.sendMessage("§2/x1 negar -§a- Nega o x1 desafiado.");
		sender.sendMessage("§c§lTaxa de §a$"+taxa+"§c§l.");
		sender.sendMessage("§c§lPrêmio de §a$"+taxa*2+"§c§l.");
		sender.sendMessage("§c§lLimite de 1 x1 por vez.");
	}
	
	private void delayDesafiar() {
		final Player desafiar = plugin.getServer().getPlayer(plugin.desafiar);
		final Player negar = plugin.getServer().getPlayer(plugin.negar);
        final double taxa = plugin.getConfig().getDouble("TaxaMoney");
		tdelayDesafiar = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
    		int timer = 60;
	      public void run() {
		        if ((timer == 5) || (timer == 10) || (timer == 20) || (timer == 50)) {
		        	plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+negar.getName()+" §ftem §a"+timer+" §fsegundos para aceitar ou negar o desafio.");
		        }
				if(timer <1) {
					cdelayDesafiar();
				}
		        if(timer == 0) {
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+negar.getName()+" §fdemorou mais que §c1 §fminuto para aceitar o x1 desafiado por §a"+desafiar.getName());
				plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §fTaxa devolvida e x1 cancelado.");
	        	Main.econ.depositPlayer(desafiar.getName(), taxa);
	        	clearPlayers();
	        }
	    		timer--;
	      }
		}, 0, 20*1);
	}
	
	private void delayTeleportArena() {
		final Player desafiar = plugin.getServer().getPlayer(plugin.desafiar);
		final Player negar = plugin.getServer().getPlayer(plugin.negar);
		tdelayTeleportArena = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
    		int timer = 20;
	      public void run() {
				if(timer <1) {
					cdelayTeleportArena();
				}
		        if(timer == 20) {
		        	desafiar.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①]"+ChatColor.WHITE+"⋙Você será teleportado para o x1 em §a"+timer+" §fsegundos");
			        negar.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①]"+ChatColor.WHITE+"⋙Você será teleportado para o x1 em §a"+timer+" §fsegundos");
		        	  desafiar.playSound(desafiar.getLocation(), Sound.BLAZE_HIT, 1.0F, 1.0F);
		        	  negar.playSound(negar.getLocation(), Sound.BLAZE_HIT, 1.0F, 1.0F);
			        }
	        if (((timer <= 10) && (timer > 1))) {
	        	desafiar.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①]"+ChatColor.WHITE+"⋙Você será teleportado para o x1 em §a"+timer+" §fsegundos");
		        negar.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①]"+ChatColor.WHITE+"⋙Você será teleportado para o x1 em §a"+timer+" §fsegundos");
	        	  desafiar.playSound(desafiar.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
	        	  negar.playSound(negar.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
	        }
	        if(timer == 1) {
	        	desafiar.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①]"+ChatColor.WHITE+"⋙Você será teleportado para o x1 em §a"+timer+" §fsegundo");
	        	negar.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①]"+ChatColor.WHITE+"⋙Você será teleportado para o x1 em §a"+timer+" §fsegundo");
	        	  desafiar.playSound(desafiar.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
	        	  negar.playSound(negar.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
		        }
	        if(timer == 0) {
	            Random randomgen = new Random();
	            int i = randomgen.nextInt(2) + 1;
	          if(i == 1) {
		          posTeleportArena = 1;
		          delayArenaInvincibility();
	          }else if(i == 2) {
		          posTeleportArena = 2;
		          delayArenaInvincibility();
	          }
	          	dentroArena = true;
		    }
    		timer--;
	      }
		}, 0, 20*1);
	}
	
	private void delayTeleportSpawn(final Player p) {
		tdelayTeleportSpawn = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
    		int timer = 15;
	      public void run() {
				if(timer <1) {
					cdelayTeleportSpawn();
				}
		        if(timer == 15) {
		        	p.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §6§lVocê venceu o x1, parabéns!");
		        	p.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①]"+ChatColor.WHITE+"⋙Você será teleportado em §a"+timer+" §fsegundos");
			        }
	        if (((timer <= 3) && (timer > 1))) {
	        	p.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①]"+ChatColor.WHITE+"⋙Você será teleportado em §a"+timer+" §fsegundos");
	        	p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
	        }
	        if(timer == 1) {
	        	p.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①]"+ChatColor.WHITE+"⋙Você será teleportado em §a"+timer+" §fsegundo");
	        	p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
		        }
	        if(timer == 0) {
	        	p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0F, 1.0F);
		    	p.getPlayer().getServer().dispatchCommand(p.getPlayer().getServer().getConsoleSender(), ""+plugin.getConfig().getString("CmdSpawn").replaceAll("@player", p.getPlayer().getName()));
		        p.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §c§lTeleportado");
		    }
    		timer--;
	      }
		}, 0, 20*1);
	}
	
	private void cAllTasks() {
		Bukkit.getScheduler().cancelAllTasks();
		cdelayTeleportArena();
		cdelayDesafiar();
		cdelayTeleportSpawn();
		cdelayArenaInvincibility();
		cdelayArenaTimeLimit();
	}

	private void cdelayTeleportArena() {
		Bukkit.getScheduler().cancelTask(tdelayTeleportArena);
	}

	private void cdelayDesafiar() {
	    Bukkit.getScheduler().cancelTask(tdelayDesafiar);
	}

	private void cdelayTeleportSpawn() {
		Bukkit.getScheduler().cancelTask(tdelayTeleportSpawn);
	}

	private void cdelayArenaInvincibility() {
		Bukkit.getScheduler().cancelTask(tdelayArenaInvincibility);
	}

	private void cdelayArenaTimeLimit() {
		Bukkit.getScheduler().cancelTask(tdelayArenaTimeLimit);
	}

	private void delayArenaInvincibility() {
		final Player desafiar = plugin.getServer().getPlayer(plugin.desafiar);
		final Player negar = plugin.getServer().getPlayer(plugin.negar);
        World w = plugin.getServer().getWorld("world_x1");
        final Location loc1 = new Location(w, 7, 71, -10);
        loc1.setPitch(0);
        loc1.setYaw(60);
        final Location loc2 = new Location(w, -6, 71, 6);
        loc2.setPitch(0);
        loc2.setYaw(240);
  	  tdelayArenaInvincibility = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
  		int timer = 5;
  		public void run() {
  			if(timer <1) {
  				cdelayArenaInvincibility();
				}
		        if ((timer <= 5) && (timer > 1)) {
			          desafiar.playSound(desafiar.getLocation(), Sound.BURP, 1.0F, 1.0F);
			          negar.playSound(negar.getLocation(), Sound.BURP, 1.0F, 1.0F);
		        	if (posTeleportArena == 1) {
			              desafiar.teleport(loc1);
			              negar.teleport(loc2);
		        	}else if(posTeleportArena == 2) {
			              desafiar.teleport(loc2);
			              negar.teleport(loc1);
		        	}
			          desafiar.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §6§lIniciando o x1 em §c"+timer+" §6§lsegundos");
			          negar.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §6§lIniciando o x1 em §c"+timer+" §6§lsegundos");
		        }
		        if (timer == 1) {
			          desafiar.playSound(desafiar.getLocation(), Sound.BURP, 1.0F, 1.0F);
			          negar.playSound(negar.getLocation(), Sound.BURP, 1.0F, 1.0F);
		        	if (posTeleportArena == 1) {
			              desafiar.teleport(loc1);
			              negar.teleport(loc2);
		        	}else if(posTeleportArena == 2) {
			              desafiar.teleport(loc2);
			              negar.teleport(loc1);
		        	}
			          desafiar.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §6§lIniciando o x1 em §c"+timer+" §6§lsegundo");
			          negar.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §6§lIniciando o x1 em §c"+timer+" §6§lsegundo");
		        }
		        if (timer == 0) {
			        	if (posTeleportArena == 1) {
				              desafiar.teleport(loc1);
				              negar.teleport(loc2);
			        	}else if(posTeleportArena == 2) {
				              desafiar.teleport(loc2);
				              negar.teleport(loc1);
			        	}
			          delayArenaTimeLimit();
			          desafiar.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §c§lVALENDO");
			          negar.sendMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §c§lVALENDO");
		              desafiar.playSound(desafiar.getLocation(), Sound.ANVIL_LAND, 1.0F, 1.0F);
		              negar.playSound(negar.getLocation(), Sound.ANVIL_LAND, 1.0F, 1.0F);
		        }
	    		timer--;
	      }
		}, 0, 20*1);
	}

	private void delayArenaTimeLimit() {
        final double taxa = plugin.getConfig().getDouble("TaxaMoney");
        final Player desafiar = plugin.getServer().getPlayer(plugin.desafiar);
        final Player negar = plugin.getServer().getPlayer(plugin.negar);
	  	  tdelayArenaTimeLimit = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
	    		int timer = 600;
	    		public void run() {
	      			if(timer <1) {
	      				cdelayArenaTimeLimit();
	    				}
	    		        if ((timer == 5) || (timer == 10) || (timer == 30)) {
	    		        	plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+timer+" §fsegundos para o x1 finalizar automaticamente.");
	    		        	plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §fCaso empatar apenas metade da taxa será devolvida.");
	    		        }
	    		        if ((timer == 60) || (timer == 120) || (timer == 180) || (timer == 240) || (timer == 300)) {
	    		        	if(timer/60 == 1) {
		    		        	plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+timer/60+" §fminuto para o x1 finalizar automaticamente.");
	    		        	}else{
		    		        	plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §a"+timer/60+" §fminutos para o x1 finalizar automaticamente.");
	    		        	}
	    		        	plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §fCaso empatar apenas metade da taxa será devolvida.");
	    		        }
	    		        if (timer == 0) {
	    					plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §6Empate entre §a"+desafiar.getName()+" §6vs §a"+negar.getName());
	    					plugin.getServer().broadcastMessage("§3[ⓅⒹⒼⒽ Ⓧ①] §cApenas metade da taxa foi devolvida.");
	    					Main.econ.depositPlayer(desafiar.getName(), taxa/2);
	    					Main.econ.depositPlayer(negar.getName(), taxa/2);
	    					desafiar.sendMessage("§6§lBelo x1, tente novamente. :)");
	    					negar.sendMessage("§6§lBelo x1, tente novamente. :)");
	    					desafiar.getPlayer().getServer().dispatchCommand(desafiar.getPlayer().getServer().getConsoleSender(), ""+plugin.getConfig().getString("CmdSpawn").replaceAll("@player", desafiar.getPlayer().getName()));
	    					negar.getPlayer().getServer().dispatchCommand(negar.getPlayer().getServer().getConsoleSender(), ""+plugin.getConfig().getString("CmdSpawn").replaceAll("@player", negar.getPlayer().getName()));
	    					cAllTasks();
	    		        	clearPlayers();
	    		        }
	    			timer--;
	    		}
	  	  }, 0, 20*1);
	}

	private void somPigDeathAll() {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
        	p.playSound(p.getLocation(), Sound.PIG_DEATH, 1.0F, 1.0F);
		}
	}

	private void clearPlayers() {
    	arraydesafiar.clear();
    	arraynegar.clear();
        plugin.negar = null;
        plugin.desafiar = null;
        iniciado = false;
        dentroArena = false;
        posTeleportArena = 0;
	}

	/**public void disabling() {
		if(!(plugin.desafiar == null)) {
			Player desafiar = plugin.getServer().getPlayer(plugin.desafiar);
		    desafiar.getPlayer().getServer().dispatchCommand(desafiar.getPlayer().getServer().getConsoleSender(), ""+plugin.getConfig().getString("CmdSpawn").replaceAll("@player", desafiar.getPlayer().getName()));	
		}
		if(!(plugin.negar == null)) {
			Player negar = plugin.getServer().getPlayer(plugin.negar);
			negar.getPlayer().getServer().dispatchCommand(negar.getPlayer().getServer().getConsoleSender(), ""+plugin.getConfig().getString("CmdSpawn").replaceAll("@player", negar.getPlayer().getName()));	
		}
	}*/
	
	
	
	
}
