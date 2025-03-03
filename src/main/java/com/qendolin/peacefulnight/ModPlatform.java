package com.qendolin.peacefulnight;


/**
 * This interface allows you to define platform specific code, and call it in 
 */

public interface ModPlatform {
    String getModloader();
    boolean isModLoaded(String modid);
}
