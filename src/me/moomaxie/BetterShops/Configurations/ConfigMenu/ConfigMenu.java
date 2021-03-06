package me.moomaxie.BetterShops.Configurations.ConfigMenu;

import me.moomaxie.BetterShops.Configurations.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;

/**
 * ***********************************************************************
 * Copyright Max Hubbard (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated documents with similar branding
 * are the sole property of Max. Distribution, reproduction, taking snippets, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 * ************************************************************************
 */
public class ConfigMenu {


    public static void openConfigMenu(Inventory inv, Player p) {

        boolean open = false;

        if (inv == null) {
            inv = Bukkit.createInventory(p, 54, "§7[BetterShops] §dConfig");
            open = true;
        } else {
            inv.clear();
        }

        //Glass
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 7);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);

        for (int i = 0; i < 9;i++) {
            inv.setItem(i,glass);
        }

        for (int i = 8; i < 54;i= i+9) {
            inv.setItem(i,glass);
        }

        for (int i = 0; i < 54;i= i+9) {
            inv.setItem(i,glass);
        }

        // General Options

        ItemStack general = new ItemStack(Material.MAP);
        ItemMeta generalMeta = general.getItemMeta();
        generalMeta.setDisplayName("§eGeneral Options");
        general.setItemMeta(generalMeta);

        ItemStack autoAdd = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemMeta autoAddMeta = autoAdd.getItemMeta();
        if (Config.autoAddItems()) {
            autoAddMeta.setDisplayName("§eAuto Add §7- §aOn");
        } else {
            MaterialData data = autoAdd.getData();
            data.setData((byte) 14);
            autoAdd.setData(data);
            autoAdd = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 14);
            autoAddMeta.setDisplayName("§eAuto Add §7- §cFalse");
        }
        autoAddMeta.setLore(Arrays.asList("§7Have Chest Contents Automatically","§7be Added To The Shop","§e§lClick §7to toggle"));
        autoAdd.setItemMeta(autoAddMeta);

        ItemStack permissions = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemMeta permissionsMeta = permissions.getItemMeta();
        if (Config.usePerms()) {
            
            permissionsMeta.setDisplayName("§ePermissions §7- §aOn");
        } else {
            MaterialData data = permissions.getData();
            data.setData((byte) 14);
            permissions.setData(data);
            permissions = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 14);
            permissionsMeta.setDisplayName("§ePermissions §7- §cFalse");
        }
        permissionsMeta.setLore(Arrays.asList("§7Use permissions","§e§lClick §7to toggle"));
        permissions.setItemMeta(permissionsMeta);

        ItemStack anvil = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemMeta anvilMeta = anvil.getItemMeta();
        if (Config.useAnvil()) {

            anvilMeta.setDisplayName("§eAnvil §7- §aOn");
        } else {
            MaterialData data = anvil.getData();
            data.setData((byte) 14);
            anvil.setData(data);
            anvil = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 14);
            anvilMeta.setDisplayName("§eAnvil §7- §cFalse");
        }
        anvilMeta.setLore(Arrays.asList("§7Use Anvil to rename items","§e§lClick §7to toggle"));
        anvil.setItemMeta(anvilMeta);

        ItemStack useOnClose = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemMeta useOnCloseMeta = useOnClose.getItemMeta();
        if (Config.useWhenClosed()) {

            useOnCloseMeta.setDisplayName("§eUse Shop When Closed §7- §aOn");
        } else {
            MaterialData data = useOnClose.getData();
            data.setData((byte) 14);
            useOnClose.setData(data);
            useOnClose = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 14);
            useOnCloseMeta.setDisplayName("§eUse Shop When Closed §7- §cFalse");
        }
        useOnCloseMeta.setLore(Arrays.asList("§7Allows players to use shop when closed", "§7if they had opened it while the shop was open","§e§lClick §7to toggle"));
        useOnClose.setItemMeta(useOnCloseMeta);

        ItemStack titles = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemMeta titlesMeta = titles.getItemMeta();
        if (Config.useTitles()) {

            titlesMeta.setDisplayName("§eTitles §7- §aOn");
        } else {
            MaterialData data = titles.getData();
            data.setData((byte) 14);
            titles.setData(data);
            titles = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 14);
            titlesMeta.setDisplayName("§eTitles §7- §cFalse");
        }
        titlesMeta.setLore(Arrays.asList("§7Use 1.8 Titles","§e§lClick §7to toggle"));
        titles.setItemMeta(titlesMeta);

        ItemStack deleteOnBreak = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemMeta deleteOnBreakMeta = deleteOnBreak.getItemMeta();
        if (Config.useDeleteByBreak()) {

            deleteOnBreakMeta.setDisplayName("§eDelete By Break §7- §aOn");
        } else {
            MaterialData data = deleteOnBreak.getData();
            data.setData((byte) 14);
            deleteOnBreak.setData(data);
            deleteOnBreak = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 14);
            deleteOnBreakMeta.setDisplayName("§eDelete By Break §7- §cFalse");
        }
        deleteOnBreakMeta.setLore(Arrays.asList("§7Delete shops by breaking the chest","§e§lClick §7to toggle"));
        deleteOnBreak.setItemMeta(deleteOnBreakMeta);

        ItemStack metrics = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemMeta metricsMeta = metrics.getItemMeta();
        if (Config.useMetrics()) {

            metricsMeta.setDisplayName("§eMetrics §7- §aOn");
        } else {
            MaterialData data = metrics.getData();
            data.setData((byte) 14);
            metrics.setData(data);
            metrics = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 14);
            metricsMeta.setDisplayName("§eMetrics §7- §cFalse");
        }
        metricsMeta.setLore(Arrays.asList("§7Toggle Server Metrics", "§3Requires a restart to take effect","§e§lClick §7to toggle"));
        metrics.setItemMeta(metricsMeta);

        inv.setItem(10,general);
        inv.setItem(11,autoAdd);
        inv.setItem(12,permissions);
        inv.setItem(13,useOnClose);
        inv.setItem(14,titles);
        inv.setItem(15,deleteOnBreak);
        inv.setItem(16,metrics);

        // Shop Creation

        ItemStack shopCreation = new ItemStack(Material.MAP);
        ItemMeta shopCreationMeta = shopCreation.getItemMeta();
        shopCreationMeta.setDisplayName("§eShop Creation Options");
        shopCreation.setItemMeta(shopCreationMeta);

        ItemStack creationLimit = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemMeta creationLimitMeta = creationLimit.getItemMeta();
        if (Config.useLimit()) {

            creationLimitMeta.setDisplayName("§eCreation Limit §7- §aOn");
        } else {
            MaterialData data = creationLimit.getData();
            data.setData((byte) 14);
            creationLimit.setData(data);
            creationLimit = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 14);
            creationLimitMeta.setDisplayName("§eCreation Limit §7- §cFalse");
        }
        creationLimitMeta.setLore(Arrays.asList("§7Use Shop Creation Limits","§e§lClick §7to toggle"));
        creationLimit.setItemMeta(creationLimitMeta);

        ItemStack limitAmount = new ItemStack(Material.NAME_TAG);
        ItemMeta limitAmountMeta = limitAmount.getItemMeta();
        limitAmountMeta.setDisplayName("§eCreation Limit Amount: §d" + Config.getLimit());
        limitAmountMeta.setLore(Arrays.asList("§7Set Creation Limit", "§e§lClick §7to change"));
        limitAmount.setItemMeta(limitAmountMeta);

        ItemStack costOnShops = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemMeta costOnShopsMeta = costOnShops.getItemMeta();
        if (Config.useCreationCost()) {

            costOnShopsMeta.setDisplayName("§eCost On Shops §7- §aOn");
        } else {
            MaterialData data = costOnShops.getData();
            data.setData((byte) 14);
            costOnShops.setData(data);
            costOnShops = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 14);
            costOnShopsMeta.setDisplayName("§eCost On Shops §7- §cFalse");

        }
        costOnShopsMeta.setLore(Arrays.asList("§7Forces players to pay for each shop creation","§e§lClick §7to toggle"));
        costOnShops.setItemMeta(costOnShopsMeta);

        ItemStack costAmount = new ItemStack(Material.NAME_TAG);
        ItemMeta costAmountMeta = costAmount.getItemMeta();
        costAmountMeta.setDisplayName("§eCost On Shops Amount: §d" + Config.getCreationCost());
        costAmountMeta.setLore(Arrays.asList("§7Set shop creation cost", "§e§lClick §7to change"));
        costAmount.setItemMeta(costAmountMeta);

        inv.setItem(19,shopCreation);
        inv.setItem(20,creationLimit);
        inv.setItem(21,limitAmount);
        inv.setItem(22,costOnShops);
        inv.setItem(23,costAmount);

        
        // NPC Options

        ItemStack NPCOptions = new ItemStack(Material.MAP);
        ItemMeta NPCOptionsMeta = NPCOptions.getItemMeta();
        NPCOptionsMeta.setDisplayName("§eNPC Options");
        NPCOptions.setItemMeta(NPCOptionsMeta);

        ItemStack NPCs = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemMeta NPCsMeta = NPCs.getItemMeta();
        if (Config.useNPCs()) {

            NPCsMeta.setDisplayName("§eNPCs §7- §aOn");
        } else {
            MaterialData data = NPCs.getData();
            data.setData((byte) 14);
            NPCs.setData(data);
            NPCs = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 14);
            NPCsMeta.setDisplayName("§eNPCs §7- §cFalse");
        }
        NPCsMeta.setLore(Arrays.asList("§7Enable NPCs","§e§lClick §7to toggle"));
        NPCs.setItemMeta(NPCsMeta);

        ItemStack NPCChoose = new ItemStack(Material.MAP);
        ItemMeta NPCChooseMeta = NPCChoose.getItemMeta();
        NPCChooseMeta.setDisplayName("§eChoose NPCs");
        NPCChoose.setItemMeta(NPCChooseMeta);

        inv.setItem(28,NPCOptions);
        inv.setItem(29,NPCs);
        inv.setItem(30,NPCChoose);

        
        // World Guard Options

        ItemStack worldGuard = new ItemStack(Material.MAP);
        ItemMeta worldGuardMeta = worldGuard.getItemMeta();
        worldGuardMeta.setDisplayName("§eWorldGuard Options");
        worldGuard.setItemMeta(worldGuardMeta);

        ItemStack allowShopFlag = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemMeta allowShopFlagMeta = allowShopFlag.getItemMeta();
        if (Config.useAllowFlag()) {
            allowShopFlagMeta.setDisplayName("§eAllow-Shop Flag §7- §aOn");
        } else {
            MaterialData data = allowShopFlag.getData();
            data.setData((byte) 14);
            allowShopFlag.setData(data);
            allowShopFlag = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 14);
            allowShopFlagMeta.setDisplayName("§eAllow-Shop Flag §7- §cFalse");
        }
        allowShopFlagMeta.setLore(Arrays.asList("§7Players can only create shops in","§7regions with the 'Allow-Shop' flag","§e§lClick §7to toggle"));
        allowShopFlag.setItemMeta(allowShopFlagMeta);

        ItemStack NPCOverride = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemMeta NPCOverrideMeta = NPCOverride.getItemMeta();
        if (Config.useNPCOverride()) {

            NPCOverrideMeta.setDisplayName("§eNPC Override §7- §aOn");
        } else {
            MaterialData data = NPCOverride.getData();
            data.setData((byte) 14);
            NPCOverride.setData(data);
            NPCOverride = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 14);
            NPCOverrideMeta.setDisplayName("§eNPC Override §7- §cFalse");
        }
        NPCOverrideMeta.setLore(Arrays.asList("§7Override a region to allow NPCs to spawn","§e§lClick §7to toggle"));
        NPCOverride.setItemMeta(NPCOverrideMeta);

        inv.setItem(37,worldGuard);
        inv.setItem(38,allowShopFlag);
        inv.setItem(39,NPCOverride);


        if (open)
            p.openInventory(inv);
    }

}
