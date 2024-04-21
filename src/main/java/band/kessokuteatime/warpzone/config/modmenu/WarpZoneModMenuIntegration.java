package band.kessokuteatime.warpzone.config.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import band.kessokuteatime.warpzone.config.WarpZoneConfig;

public class WarpZoneModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(WarpZoneConfig.class, parent).get();
    }
}
