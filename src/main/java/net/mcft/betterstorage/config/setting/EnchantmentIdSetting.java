package net.mcft.betterstorage.config.setting;

import net.mcft.betterstorage.BetterStorage;
import net.mcft.betterstorage.config.Config;
import net.minecraft.enchantment.Enchantment;

public class EnchantmentIdSetting extends IntegerSetting {

    public EnchantmentIdSetting(Config config, String fullName, Integer defaultId, String langKey) {
        super(config, fullName, defaultId, langKey);
        setValidRange(0, 255);
        setHasComment(false);
        setRequiresMcRestart(true);
    }

    public int getFreeId() {
        int id = value;
        while (Enchantment.enchantmentsList[id] != null)
            if ((id = (id + 1) % 256) == value) throw new RuntimeException("No free enchantment ID found.");
        if (id != value) BetterStorage.log.info(
            String
                .format("Enchantment ID %s for %s already used, using next free id %s instead.", value, fullName, id));
        return value = id;
    }

}
