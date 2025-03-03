package com.qendolin.peacefulnights.compat;

//? <1.21 {
/*import corgitaco.enhancedcelestials.EnhancedCelestialsWorldData;
import corgitaco.enhancedcelestials.api.ECLunarEventTags;
*///?} else {
import dev.corgitaco.enhancedcelestials.EnhancedCelestialsWorldData;
import dev.corgitaco.enhancedcelestials.api.ECLunarEventTags;
//?}
import net.minecraft.world.level.ServerLevelAccessor;

public class EnhancedCelestialsCompatImpl extends EnhancedCelestialsCompat{
    @Override
    public boolean isBloodMoon(ServerLevelAccessor level) {
        if(!(level instanceof EnhancedCelestialsWorldData ecwd)) {
            return false;
        }
        var ctx = ecwd.getLunarContext();
        if(ctx == null) return false;
        var forecast = ctx.getLunarForecast();
        if(forecast == null) return false;
        return forecast.currentLunarEvent().is(ECLunarEventTags.BLOOD_MOON);
    }
}
