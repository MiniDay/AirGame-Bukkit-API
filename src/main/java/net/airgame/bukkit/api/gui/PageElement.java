package net.airgame.bukkit.api.gui;

import net.airgame.bukkit.api.gui.holder.PageableHolder;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @see PageableHolder#initPage()
 */
public interface PageElement {
    String replaceDisplayName(HumanEntity player, String displayName);

    List<String> replaceLore(HumanEntity player, List<String> lore);

    void replaceItem(ItemStack stack);

    void replaceMeta(ItemMeta meta);
}