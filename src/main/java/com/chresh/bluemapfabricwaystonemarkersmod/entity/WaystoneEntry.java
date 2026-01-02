package com.chresh.bluemapfabricwaystonemarkersmod.entity;

import java.util.UUID;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;

public record WaystoneEntry(
        Vec3d pos, EntityType<?> type, UUID uuid, String simpleName, String world, int x,
            int y, int z, EntityDimensions dimensions) {
                

                public Vec3d getPos() {
                    return pos;
                }
                public EntityType<?> getType() {
                    return type;
                }
                public UUID getUuid() {
                    return uuid;
                }
                public String getSimpleName() {
                    return simpleName;
                }
                public String getWorld() {
                    return world;
                }
                public String getWorldKey() {
                    if (world == null) {
                        return "unknown";
                    }
                    return getWorld();
                }
                public int getX() {
                    return x;
                }
                public int getY() {
                    return y;
                }
                public int getZ() {
                    return z;
                }
                public EntityDimensions getDimensions() {
                    return dimensions;
                }
}
