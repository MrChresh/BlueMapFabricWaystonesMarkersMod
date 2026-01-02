package com.chresh.bluemapfabricwaystonemarkersmod.entity;

public record WaystoneEntryKey(
        int x,
        int y,
        int z,
        String parentMap) {


    public WaystoneEntryKey withParentMap(String parentMap) {
        return new WaystoneEntryKey(x, y, z, parentMap);
    }
}
