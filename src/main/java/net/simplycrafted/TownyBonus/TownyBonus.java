package net.simplycrafted.TownyBonus;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Hashtable;

/**
 * Copyright © Brian Ronald
 * 10/08/14
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */
public class TownyBonus extends JavaPlugin {
    private static Towny towny;

    @Override
    public void  onEnable () {
        towny = (Towny) getServer().getPluginManager().getPlugin("Towny");

    }

    @Override
    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("townybonus")) {
            Hashtable<String,Town> towns = towny.getTownyUniverse().getTownsMap();
            for (String townName : towns.keySet()) {
                StringBuilder stringBuilder = new StringBuilder();
                Town town = towns.get(townName);
                stringBuilder.append(String.format("%s has %d residents; increasing plot bonus from %s ", townName, town.getNumResidents(), town.getBonusBlocks()));
                town.addBonusBlocks(town.getNumResidents());
                stringBuilder.append(String.format("to %d.", town.getBonusBlocks()));
                sender.sendMessage(stringBuilder.toString());
            }
        }
        return true;
    }
}
