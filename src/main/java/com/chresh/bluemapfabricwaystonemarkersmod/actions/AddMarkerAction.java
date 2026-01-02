package com.chresh.bluemapfabricwaystonemarkersmod.actions;


import com.chresh.bluemapfabricwaystonemarkersmod.entity.WaystoneEntry;
import com.chresh.bluemapfabricwaystonemarkersmod.markers.MarkerIdentifier;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.util.math.Vec3d;

public class AddMarkerAction extends MarkerAction {
    private final String label;
    private final String detail;
    private final EntityDimensions entityDimensions;
    private Vec3d entityPos;


    public AddMarkerAction(MarkerIdentifier markerIdentifier, WaystoneEntry entityEntry) {
        super(markerIdentifier);
        this.label = entityEntry.getSimpleName();
        this.detail = entityEntry.getSimpleName();
        this.entityPos = entityEntry.getPos();
        this.entityDimensions = entityEntry.getDimensions();
    }

    public String getLabel() {
        return label;
    }

    public String getDetail() {
        return detail;
    }

    public Vec3d getEntityPos() {
        return entityPos;
    }

    public EntityDimensions getEntityDimensions() {
        return entityDimensions;
    }

    @Override
    public String toString() {
        return "AddMarkerAction{" +
                "markerIdentifier=" + getMarkerIdentifier() +
                ", label='" + label + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

}