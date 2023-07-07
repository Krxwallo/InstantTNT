package io.github.krxwallo.instant_tnt.config;

import io.github.krxwallo.instant_tnt.InstantTNT;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    public static class Common {

        public final ForgeConfigSpec.DoubleValue tntChargeDuration;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Instant TNT Mod Configurations")
                   .push(InstantTNT.MOD_ID);

            tntChargeDuration = builder
                    .comment("The time until TNT explodes after it gets charged. (in seconds) (default: 8s)")
                    .worldRestart()
                    .defineInRange("tnt_charge_duration", 8f, 0f, 100f);

            builder.pop();
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
