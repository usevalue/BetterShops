package me.moomaxie.BetterShops.Listeners.Misc;

import me.moomaxie.BetterShops.Configurations.Config;
import me.moomaxie.BetterShops.Configurations.GUIMessages.MainGUI;
import me.moomaxie.BetterShops.Configurations.Messages;
import me.moomaxie.BetterShops.Configurations.ShopLimits;
import me.moomaxie.BetterShops.Core;
import me.moomaxie.BetterShops.Listeners.ManagerOptions.ShopKeeperManager;
import me.moomaxie.BetterShops.Listeners.ManagerOptions.ShopSettings;
import me.moomaxie.BetterShops.Listeners.ManagerOptions.Stocks;
import me.moomaxie.BetterShops.Listeners.OpenShopOptions;
import me.moomaxie.BetterShops.Listeners.OwnerSellingOptions.OpenSellingOptions;
import me.moomaxie.BetterShops.Listeners.SearchEngine.DisplayNameCheck;
import me.moomaxie.BetterShops.Listeners.SearchEngine.IdCheck;
import me.moomaxie.BetterShops.Listeners.SearchEngine.MaterialCheck;
import me.moomaxie.BetterShops.Listeners.SearchEngine.PriceCheck;
import me.moomaxie.BetterShops.Shops.AddShop;
import me.moomaxie.BetterShops.Shops.Shop;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * ***********************************************************************
 * Copyright Max Hubbard (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated documents with similar branding
 * are the sole property of Max. Distribution, reproduction, taking snippets, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 * ************************************************************************
 */
public class ChatMessages implements Listener {

    public static Map<Player, Chest> shopCreate = new HashMap<>();
    public static Map<Player, Block> shopCreate2 = new HashMap<>();

    public static Map<Player, Map<Shop, ItemStack>> collectStock = new HashMap<>();
    public static Map<Player, Map<Shop, ItemStack>> addStock = new HashMap<>();
    public static Map<Player, Map<Shop, ItemStack>> removeStock = new HashMap<>();

    public static Map<Player, Shop> description = new HashMap<>();

    public static Map<Player, Map<Shop, ItemStack>> setSellAmount = new HashMap<>();
    public static Map<Player, Map<Shop, ItemStack>> setBuyAmount = new HashMap<>();

    public static Map<Player, Map<Shop, ItemStack>> setSellPrice = new HashMap<>();
    public static Map<Player, Map<Shop, ItemStack>> setBuyPrice = new HashMap<>();

    public static Map<Player, Map<Shop, ItemStack>> changeData = new HashMap<>();

    public static Map<Player, Shop> addKeeper = new HashMap<>();
    public static Map<Player, Shop> removeKeeper = new HashMap<>();

    public static Map<Player, Map<Shop, Inventory>> addSellItem = new HashMap<>();

    public static Map<Player, Map<Shop, Boolean>> searchMaterial = new HashMap<>();
    public static Map<Player, Map<Shop, Boolean>> searchName = new HashMap<>();
    public static Map<Player, Map<Shop, Boolean>> searchID = new HashMap<>();
    public static Map<Player, Map<Shop, Boolean>> searchPrice = new HashMap<>();

    @EventHandler
    public void onStopChat(AsyncPlayerChatEvent e) {

        try {
            for (Player p : e.getRecipients()) {
                if (!shopCreate.containsKey(p) && !collectStock.containsKey(p) && !addStock.containsKey(p) && !description.containsKey(p)
                        && !setSellAmount.containsKey(p) && !setBuyAmount.containsKey(p) && !setSellPrice.containsKey(p) && !setBuyPrice.containsKey(p)
                        && !searchID.containsKey(p) && !searchMaterial.containsKey(p) && !searchName.containsKey(p) && !searchPrice.containsKey(p) && !changeData.containsKey(p)
                        && !addKeeper.containsKey(p) && !removeKeeper.containsKey(p) && !addSellItem.containsKey(p)) {

                } else {
                    e.getRecipients().remove(p);
                }
            }
        } catch (Exception ex) {

        }
    }

    @EventHandler
    public void onAddSellItem(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (addSellItem.containsKey(p)) {
            String name = e.getMessage();
            if (!name.equalsIgnoreCase("cancel")) {

                Shop shp = null;

                for (Shop s : addSellItem.get(p).keySet()) {
                    shp = s;
                }
                
                

                final Shop shop = shp;
                Inventory inv = addSellItem.get(p).get(shop);
                boolean can = false;
                int amt;
                try {
                    amt = Integer.parseInt(name);

                    ItemStack item = new ItemStack(amt);

                    if (item != null) {
                        if (!shop.alreadyBeingSold(item, true)) {

                            if (!shop.alreadyBeingSold(item, true)) {
                                if (shop.getHighestSlot(true) < 161) {

                                    if (shop.getHighestSlot(true) == 53) {
                                        shop.addItem(item, 72, true);
                                    } else if (shop.getHighestSlot(true) == 107) {
                                        shop.addItem(item, 126, true);
                                    } else if (inv.getItem(12).getData().getData() == (byte) 10) {
                                        if (inv.firstEmpty() >= 18) {
                                            if (inv.firstEmpty() == 18) {
                                                shop.addItem(item, 18, true);
                                            } else {
                                                shop.addItem(item, shop.getHighestSlot(true) + 1, true);
                                            }
                                        } else {
                                            shop.addItem(item, shop.getHighestSlot(true) + 1, true);
                                        }
                                    } else {
                                        shop.addItem(item, shop.getHighestSlot(true) + 1, true);
                                    }

                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 400, 400);

                                    p.closeInventory();

                                    p.sendMessage(Messages.getPrefix() + Messages.getAddItem());

                                    can = true;

                                } else {
                                    p.sendMessage(Messages.getPrefix() + Messages.getShopFull());
                                }
                            } else {
                                p.sendMessage(Messages.getPrefix() + Messages.getAlreadyAsk());
                            }
                        } else {
                            can = true;
                        }
                    }


                } catch (Exception ex) {

                    if (Material.getMaterial(name.toUpperCase()) != null) {
                        ItemStack item = new ItemStack(Material.valueOf(name.toUpperCase()));

                        if (!shop.alreadyBeingSold(item, true)) {

                            if (!shop.alreadyBeingSold(item, true)) {
                                if (shop.getHighestSlot(true) < 161) {

                                    if (shop.getHighestSlot(true) == 53) {
                                        shop.addItem(item, 72, true);
                                    } else if (shop.getHighestSlot(true) == 107) {
                                        shop.addItem(item, 126, true);
                                    } else if (inv.getItem(12).getData().getData() == (byte) 10) {
                                        if (inv.firstEmpty() >= 18) {
                                            if (inv.firstEmpty() == 18) {
                                                shop.addItem(item, 18, true);
                                            } else {
                                                shop.addItem(item, shop.getHighestSlot(true) + 1, true);
                                            }
                                        } else {
                                            shop.addItem(item, shop.getHighestSlot(true) + 1, true);
                                        }
                                    } else {
                                        shop.addItem(item, shop.getHighestSlot(true) + 1, true);
                                    }

                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 400, 400);

                                    p.closeInventory();

                                    p.sendMessage(Messages.getPrefix() + Messages.getAddItem());

                                    can = true;

                                } else {
                                    p.sendMessage(Messages.getPrefix() + Messages.getShopFull());
                                }
                            } else {
                                p.sendMessage(Messages.getPrefix() + Messages.getAlreadyAsk());
                            }
                        } else {
                            can = true;
                        }
                    } else {
                        for (Material m : Material.values()) {
                            if (m.name().contains(name.toUpperCase().replaceAll(" ", "_"))) {
                                ItemStack item = new ItemStack(m);

                                if (!shop.alreadyBeingSold(item, true)) {

                                    if (!shop.alreadyBeingSold(item, true)) {
                                        if (shop.getHighestSlot(true) < 161) {

                                            if (shop.getHighestSlot(true) == 53) {
                                                shop.addItem(item, 72, true);
                                            } else if (shop.getHighestSlot(true) == 107) {
                                                shop.addItem(item, 126, true);
                                            } else if (inv.getItem(12).getData().getData() == (byte) 10) {
                                                if (inv.firstEmpty() >= 18) {
                                                    if (inv.firstEmpty() == 18) {
                                                        shop.addItem(item, 18, true);
                                                    } else {
                                                        shop.addItem(item, shop.getHighestSlot(true) + 1, true);
                                                    }
                                                } else {
                                                    shop.addItem(item, shop.getHighestSlot(true) + 1, true);
                                                }
                                            } else {
                                                shop.addItem(item, shop.getHighestSlot(true) + 1, true);
                                            }

                                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 400, 400);

                                            p.closeInventory();

                                            p.sendMessage(Messages.getPrefix() + Messages.getAddItem());

                                            can = true;
                                            break;
                                        } else {
                                            p.sendMessage(Messages.getPrefix() + Messages.getShopFull());
                                        }
                                    } else {
                                        p.sendMessage(Messages.getPrefix() + Messages.getAlreadyAsk());
                                    }
                                } else {
                                    can = true;
                                }
                            }
                        }
                    }
                }


                if (!can) {
                    p.sendMessage(Messages.getPrefix() + Messages.getInvalidItem());
                }

                addSellItem.remove(p);
                e.setCancelled(true);

            } else {
                addSellItem.remove(p);
                e.setCancelled(true);
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
        }
    }

    @EventHandler
    public void onCreate(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (shopCreate.containsKey(p)) {
            String name = e.getMessage();
            if (!name.equalsIgnoreCase("cancel")) {
                final Chest finalChest = shopCreate.get(p);
                Block b = shopCreate2.get(p);

                boolean can = true;
                boolean Long = false;

                if (name.length() > 21) {
                    Long = true;
                }

                if (new File(Core.getCore().getDataFolder(), "Shops").listFiles() != null) {

                    for (File file : new File(Core.getCore().getDataFolder(), "Shops").listFiles()) {
                        if (file.getName().contains(".yml")) {
                            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

                            for (String s : config.getKeys(false)) {
                                if (s.equals(name)) {
                                    can = false;
                                }
                            }
                        }
                    }
                }

                if (can && !Long) {

                    new AddShop(e.getPlayer(), finalChest, name);
                    e.getPlayer().sendMessage(Messages.getPrefix() + Messages.getCreateShop());

                    shopCreate.remove(p);
                    shopCreate2.remove(p);
                    e.setCancelled(true);


                    Sign s = (Sign) b.getState();

                    s.setLine(0, MainGUI.getString("SignLine1"));
                    s.setLine(1, MainGUI.getString("SignLine2"));
                    s.setLine(2, MainGUI.getString("SignLine3Closed"));
                    s.setLine(3, MainGUI.getString("SignLine4"));

                    s.update();

                    if (Core.isAboveEight() && Config.useTitles()) {

                            Core.getTitleManager().setTimes(p, 20, 40, 20);
                            Core.getTitleManager().sendTitle(p, Messages.getCreateShop());

                    }

                    ShopLimits.loadShops();

                    if (Config.autoAddItems()) {

                        if (finalChest != null && finalChest.getBlockInventory() != null) {
                            Shop shop = ShopLimits.fromString(finalChest.getLocation());
                            int i = 18;
                            for (final ItemStack items : finalChest.getBlockInventory().getContents()) {
                                if (items != null && items.getType() != Material.AIR) {

                                    int am = items.getAmount();

                                    items.setAmount(1);

                                    if (!shop.getShopContents(false).containsKey(items)) {
                                        if (i < 53) {
                                            shop.addItem(items, i, false);
                                            i++;
                                        } else {
                                            if (i == 53) {
                                                shop.addItem(items, i, false);
                                                i = 72;
                                            } else {
                                                shop.addItem(items, i, false);
                                                i++;
                                            }
                                        }
                                    } else {
                                        shop.addItem(items, i, false);
                                    }

                                    items.setAmount(am);

                                    if (items.getAmount() > 1) {
                                        int amt = items.getAmount();

                                        shop.setStock(items, ((shop.getStock(items, false) + amt) - 1), false);

                                        Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("BetterShops"), new Runnable() {
                                            public void run() {
                                                for (int in = 0; in < finalChest.getBlockInventory().getSize(); in++) {
                                                    if (finalChest.getBlockInventory().getItem(in) != null) {

                                                        if (finalChest.getBlockInventory().getItem(in).equals(items)) {
                                                            finalChest.getBlockInventory().setItem(in, new ItemStack(Material.AIR));
                                                        }
                                                    }
                                                }

                                            }
                                        }, 5L);

                                    } else {
                                        Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("BetterShops"), new Runnable() {
                                            public void run() {
                                                for (int in = 0; in < finalChest.getBlockInventory().getSize(); in++) {
                                                    if (finalChest.getBlockInventory().getItem(in) != null) {

                                                        if (finalChest.getBlockInventory().getItem(in).equals(items)) {
                                                            finalChest.getBlockInventory().setItem(in, new ItemStack(Material.AIR));
                                                        }
                                                    }
                                                }

                                            }
                                        }, 5L);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    shopCreate.remove(p);
                    shopCreate2.remove(p);
                    e.setCancelled(true);
                    if (Long) {
                        e.getPlayer().sendMessage(Messages.getPrefix() + "§cThat Shop Name Is Too long! §7(Max: 21 Characters)");

                        if (Core.isAboveEight() && Config.useTitles()) {

                                Core.getTitleManager().setTimes(p, 20, 40, 20);
                                Core.getTitleManager().sendTitle(p, "§cName Too Long");


                        }
                    }

                    if (!can) {
                        e.getPlayer().sendMessage(Messages.getPrefix() + "§cA shop with that name already exists!");

                        if (Core.isAboveEight() && Config.useTitles()) {

                                Core.getTitleManager().setTimes(p, 20, 40, 20);
                                Core.getTitleManager().sendTitle(p, "§cName Already Exists");


                        }
                    }
                }
            } else {
                shopCreate.remove(p);
                shopCreate2.remove(p);
                e.setCancelled(true);
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
        }
    }

    @EventHandler
    public void onSearches(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();

        if (searchMaterial.containsKey(p)) {
            final String name = e.getMessage();

            if (!name.equalsIgnoreCase("cancel")) {

                Shop shp = null;

                for (Shop s : searchMaterial.get(p).keySet()) {
                    shp = s;
                }

                final Shop shop = shp;

                final boolean sell = searchMaterial.get(p).get(shop);

                int amt = 0;
                boolean can = true;
                try {
                    amt = Integer.parseInt(name);
                    can = false;


                } catch (Exception ex) {
                }

                if (can) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("BetterShops"), new Runnable() {
                        public void run() {
                            MaterialCheck.searchByMaterial(null,p, shop, name, sell);
                        }
                    }, 1L);

                } else {
                    p.sendMessage(Messages.getPrefix() + Messages.getImproperSearch());
                }
            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            searchMaterial.remove(p);
            e.setCancelled(true);
        }

        if (searchID.containsKey(p)) {
            final String name = e.getMessage();
            if (!name.equalsIgnoreCase("Cancel")) {

                Shop shp = null;

                for (Shop s : searchID.get(p).keySet()) {
                    shp = s;
                }

                final Shop shop = shp;

                final boolean sell = searchID.get(p).get(shop);

                int amt = 0;
                boolean can = false;
                try {
                    amt = Integer.parseInt(name);
                    can = true;


                } catch (Exception ex) {
                }

                if (can) {
                    final int carl = amt;
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("BetterShops"), new Runnable() {
                        public void run() {
                            IdCheck.searchById(null,p, shop, carl, sell);
                        }
                    }, 1L);

                } else {
                    p.sendMessage(Messages.getPrefix() + Messages.getImproperSearch());
                }
            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            searchID.remove(p);
            e.setCancelled(true);
        }

        if (searchPrice.containsKey(p)) {
            final String name = e.getMessage();
            if (!name.equalsIgnoreCase("Cancel")) {

                Shop shp = null;

                for (Shop s : searchPrice.get(p).keySet()) {
                    shp = s;
                }

                final Shop shop = shp;

                final boolean sell = searchPrice.get(p).get(shop);

                int amt = 0;
                boolean can = false;
                try {
                    amt = Integer.parseInt(name);
                    can = true;


                } catch (Exception ex) {
                }

                if (can) {
                    final int carl = amt;
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("BetterShops"), new Runnable() {
                        public void run() {
                            PriceCheck.searchByPrice(null,p, shop, carl, sell);
                        }
                    }, 1L);

                } else {
                    p.sendMessage(Messages.getPrefix() + Messages.getImproperSearch());
                }
            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            searchPrice.remove(p);
            e.setCancelled(true);
        }

        if (searchName.containsKey(p)) {
            final String name = e.getMessage();
            if (!name.equalsIgnoreCase("Cancel")) {
                Shop shp = null;

                for (Shop s : searchName.get(p).keySet()) {
                    shp = s;
                }

                final Shop shop = shp;

                final boolean sell = searchName.get(p).get(shop);

                int amt = 0;
                boolean can = true;
                try {
                    amt = Integer.parseInt(name);
                    can = false;


                } catch (Exception ex) {
                }

                if (can) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("BetterShops"), new Runnable() {
                        public void run() {
                            DisplayNameCheck.searchByName(null,p, shop, name, sell);
                        }
                    }, 1L);

                } else {
                    p.sendMessage(Messages.getPrefix() + Messages.getImproperSearch());
                }

            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            searchName.remove(p);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCollectStock(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        String name = e.getMessage();


        if (collectStock.containsKey(p)) {

            if (!name.equalsIgnoreCase("Cancel")) {
                Shop shp = null;

                for (Shop s : collectStock.get(p).keySet()) {
                    shp = s;
                }

                final Shop shop = shp;

                ItemStack ite = collectStock.get(p).get(shop);

                int amt;
                try {
                    amt = Integer.parseInt(name);
                } catch (Exception ex) {
                    if (name.equalsIgnoreCase("all")){
                        Stocks.collectAll(ite, shop, p);
                        return;
                    } else {
                        p.sendMessage(Messages.getPrefix() + Messages.getInvalidNumber());
                        OpenSellingOptions.openShopSellingOptions(null,p,shop,1);
                        return;
                    }
                }

                Stocks.collectStock(ite, amt, p, shop);


            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            collectStock.remove(p);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onAddStock(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        String name = e.getMessage();

        if (addStock.containsKey(p)) {

            e.setCancelled(true);


            if (!name.equalsIgnoreCase("Cancel")) {
                Shop shp = null;

                for (Shop s : addStock.get(p).keySet()) {
                    shp = s;
                }

                final Shop shop = shp;

                ItemStack ite = addStock.get(p).get(shop);

                int amt;
                try {
                    amt = Integer.parseInt(name);
                } catch (Exception ex) {
                    if (name.equalsIgnoreCase("all")){
                        Stocks.addAll(ite,shop,p);
                        return;
                    } else {
                        p.sendMessage(Messages.getPrefix() + Messages.getInvalidNumber());
                        OpenShopOptions.openShopOwnerOptionsInventory(null,p, shop, 1);
                        return;
                    }
                }

                Stocks.addStock(ite, amt, p, shop);


            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            addStock.remove(p);
            e.setCancelled(true);
            p.updateInventory();
        }
    }

    @EventHandler
    public void onRemoveStock(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        String name = e.getMessage();

        if (removeStock.containsKey(p)) {

            e.setCancelled(true);


            if (!name.equalsIgnoreCase("Cancel")) {
                Shop shp = null;

                for (Shop s : removeStock.get(p).keySet()) {
                    shp = s;
                }

                final Shop shop = shp;

                ItemStack ite = removeStock.get(p).get(shop);

                int amt;
                try {
                    amt = Integer.parseInt(name);
                } catch (Exception ex) {
                    if (name.equalsIgnoreCase("all")){
                        Stocks.removeAll(ite,shop,p);
                        return;
                    } else {
                        p.sendMessage(Messages.getPrefix() + Messages.getInvalidNumber());
                        OpenShopOptions.openShopOwnerOptionsInventory(null,p, shop, 1);
                        return;
                    }
                }

                Stocks.removeStock(ite, amt, p, shop);


            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            removeStock.remove(p);
            e.setCancelled(true);
            p.updateInventory();
        }
    }

    @EventHandler
    public void onSetBuyPrice(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        String name = e.getMessage();

        if (setBuyPrice.containsKey(p)) {

            if (!name.equalsIgnoreCase("Cancel")) {
                Shop shp = null;

                for (Shop s : setBuyPrice.get(p).keySet()) {
                    shp = s;
                }

                final Shop shop = shp;

                ItemStack ite = setBuyPrice.get(p).get(shop);

                boolean can;
                double amt = 0.0;
                try {
                    amt = Double.parseDouble(name);
                    can = true;
                } catch (Exception ex) {
                    p.sendMessage(Messages.getPrefix() + Messages.getInvalidNumber());
                    can = false;
                }

                if (can) {
                    shop.setPrice(ite, amt, false);
                    p.sendMessage(Messages.getPrefix() + Messages.getChangePrice());
                }
                OpenShopOptions.openShopOwnerOptionsInventory(null,p, shop, 1);
            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            setBuyPrice.remove(p);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSetSellPrice(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        String name = e.getMessage();

        if (setSellPrice.containsKey(p)) {

            if (!name.equalsIgnoreCase("Cancel")) {
                Shop shp = null;

                for (Shop s : setSellPrice.get(p).keySet()) {
                    shp = s;
                }

                final Shop shop = shp;

                ItemStack ite = setSellPrice.get(p).get(shop);

                boolean can;
                double amt = 0.0;
                try {
                    amt = Double.parseDouble(name);
                    can = true;
                } catch (Exception ex) {
                    p.sendMessage(Messages.getPrefix() + Messages.getInvalidNumber());
                    can = false;
                }

                if (can) {
                    shop.setPrice(ite, amt, true);
                    p.sendMessage(Messages.getPrefix() + Messages.getChangePrice());
                }

                OpenShopOptions.openShopOwnerOptionsInventory(null,p, shop, 1);
            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            setSellPrice.remove(p);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSetBuyAmount(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        String name = e.getMessage();

        if (setBuyAmount.containsKey(p)) {
            if (!name.equalsIgnoreCase("Cancel")) {
                Shop shp = null;

                for (Shop s : setBuyAmount.get(p).keySet()) {
                    shp = s;
                }

                final Shop shop = shp;

                ItemStack ite = setBuyAmount.get(p).get(shop);

                boolean can;
                int amt = 0;
                try {
                    amt = Integer.parseInt(name);
                    can = true;
                } catch (Exception ex) {
                    p.sendMessage(Messages.getPrefix() + Messages.getInvalidNumber());
                    can = false;
                }

                if (can) {
                    shop.setAmount(ite, amt, false);
                    p.sendMessage(Messages.getPrefix() + Messages.getChangeAmount());
                }
                OpenShopOptions.openShopOwnerOptionsInventory(null,p, shop, 1);
            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            setBuyAmount.remove(p);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSetSellAmount(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        String name = e.getMessage();

        if (setSellAmount.containsKey(p)) {
            if (!name.equalsIgnoreCase("Cancel")) {
                Shop shp = null;

                for (Shop s : setSellAmount.get(p).keySet()) {
                    shp = s;
                }

                final Shop shop = shp;

                ItemStack ite = setSellAmount.get(p).get(shop);

                boolean can;
                int amt = 0;
                try {
                    amt = Integer.parseInt(name);
                    can = true;
                } catch (Exception ex) {
                    p.sendMessage(Messages.getPrefix() + Messages.getInvalidNumber());
                    can = false;
                }

                if (can) {
                    shop.setAmount(ite, amt, true);
                    p.sendMessage(Messages.getPrefix() + Messages.getChangeAmount());
                }

                OpenShopOptions.openShopOwnerOptionsInventory(null,p, shop, 1);
            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            setSellAmount.remove(p);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChangeData(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        String name = e.getMessage();

        if (changeData.containsKey(p)) {
            if (!name.equalsIgnoreCase("Cancel")) {
                e.setCancelled(true);
                Shop shp = null;

                for (Shop s : changeData.get(p).keySet()) {
                    shp = s;
                }

                final Shop shop = shp;

                ItemStack ite = changeData.get(p).get(shop);

                boolean can;
                int amt = 0;
                try {
                    amt = Integer.parseInt(name);
                    can = true;
                } catch (Exception ex) {
                    p.sendMessage(Messages.getPrefix() + Messages.getInvalidNumber());
                    can = false;
                }

                if (can) {

                    int am = shop.getAmount(ite, true);
                    double price = shop.getPrice(ite, true);

                    int slot = shop.getSlotForItem(ite, true);

                    Material mat = ite.getType();

                    shop.deleteItem(ite, true);

                    ItemStack it = new ItemStack(mat, 1, (byte) amt);

                    shop.addItem(it, slot, true);

                    shop.setAmount(it, am, true);
                    shop.setPrice(it, price, true);

                    p.sendMessage(Messages.getPrefix() + Messages.getChangeData());

                }
                OpenSellingOptions.openShopSellingOptions(null,p, shop, 1);
            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            e.setCancelled(true);
            changeData.remove(p);
        }
    }

    @EventHandler
    public void onDescription(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (description.containsKey(p)) {
            String name = e.getMessage();
            if (!name.equalsIgnoreCase("Cancel")) {

                Shop shop = description.get(p);

                shop.setDescription(name);

                p.sendMessage(Messages.getPrefix() + "Changed Shop Description!");
                p.closeInventory();
                ShopSettings.openShopManager(null,p, shop);
            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            description.remove(p);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onAddKeeper(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (addKeeper.containsKey(p)) {
            String name = e.getMessage();

            if (!name.equalsIgnoreCase("Cancel")) {

                Shop shop = addKeeper.get(p);

                shop.addManager(Bukkit.getOfflinePlayer(name));
                p.sendMessage(Messages.getPrefix() + "Added shop keeper");
                ShopKeeperManager.openKeeperManager(p, shop);
            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            addKeeper.remove(p);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onRemoveKeeper(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (removeKeeper.containsKey(p)) {
            String name = e.getMessage();

            if (!name.equalsIgnoreCase("Cancel")) {

                Shop shop = removeKeeper.get(p);

                shop.removeManager(Bukkit.getOfflinePlayer(name));
                p.sendMessage(Messages.getPrefix() + "Removed shop keeper");
                ShopKeeperManager.openKeeperManager(p, shop);
            } else {
                p.sendMessage(Messages.getPrefix() + "§cCanceled");
            }
            removeKeeper.remove(p);
            e.setCancelled(true);
        }
    }
}
