package com.chresh.bluemapfabricwaystonemarkersmod.actions;

import com.chresh.bluemapfabricwaystonemarkersmod.entity.WaystoneEntry;
import com.chresh.bluemapfabricwaystonemarkersmod.markers.MarkerIdentifier;

import net.minecraft.entity.EntityDimensions;

public class UpdateMarkerAction extends MarkerAction {
    private final String newLabel;
    private final String newDetails;
    private final EntityDimensions entityDimensions;
    
    public UpdateMarkerAction(MarkerIdentifier markerIdentifier, WaystoneEntry entityEntry) {
        super(markerIdentifier);
        this.newLabel = entityEntry.getSimpleName();
        this.newDetails = entityEntry.getSimpleName();
        this.entityDimensions = entityEntry.getDimensions();
    }

    public String getNewLabel() {
        return newLabel;
    }

    public String getNewDetails() {
        return newDetails;
    }

    public EntityDimensions getEntityDimensions() {
        return entityDimensions;
    }
    @Override
    public String toString() {
        return "UpdateMarkerAction{" +
                "markerIdentifier=" + getMarkerIdentifier() +
                ", newLabel='" + newLabel + '\'' +
                ", newDetails='" + newDetails + '\'' +
                '}';
    }
}