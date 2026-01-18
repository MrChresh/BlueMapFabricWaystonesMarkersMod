package com.chresh.bluemapfabricwaystonemarkersmod.actions;

import com.chresh.bluemapfabricwaystonemarkersmod.entity.WaystoneEntry;
import com.chresh.bluemapfabricwaystonemarkersmod.markers.MarkerGroup;
import com.chresh.bluemapfabricwaystonemarkersmod.markers.MarkerGroupType;
import com.chresh.bluemapfabricwaystonemarkersmod.markers.MarkerIdentifier;
import com.chresh.bluemapfabricwaystonemarkersmod.markers.MarkerSetIdentifierCollection;

public class ActionFactory {
    private final MarkerSetIdentifierCollection markerSetIdentifierCollection;

    public ActionFactory(MarkerSetIdentifierCollection markerSetIdentifierCollection) {
        this.markerSetIdentifierCollection = markerSetIdentifierCollection;
    }

    public AddMarkerAction createAddPOIAction(WaystoneEntry entityEntry) {
        return new AddMarkerAction(
                new MarkerIdentifier(
                        entityEntry.getX(),
                        entityEntry.getY(),
                        entityEntry.getZ(),
                        markerSetIdentifierCollection.getIdentifier(entityEntry.getWorldKey(), new MarkerGroup("waystone", MarkerGroupType.POI, "Waystone", "", 0, 0))),
                        entityEntry);
    }

    public RemoveMarkerAction createRemovePOIAction(WaystoneEntry entityEntry) {
        return new RemoveMarkerAction(
                new MarkerIdentifier(
                        entityEntry.getX(),
                        entityEntry.getY(),
                        entityEntry.getZ(),
                        markerSetIdentifierCollection.getIdentifier(entityEntry.getWorldKey(), new MarkerGroup("waystone", MarkerGroupType.POI, "Waystone", "", 0, 0))));
    }

    public UpdateMarkerAction createUpdatePOIAction(WaystoneEntry entityEntry) {
        return new UpdateMarkerAction(
                new MarkerIdentifier(
                        entityEntry.getX(),
                        entityEntry.getY(),
                        entityEntry.getZ(),
                        markerSetIdentifierCollection.getIdentifier(entityEntry.getWorldKey(), new MarkerGroup("waystone", MarkerGroupType.POI, "Waystone", "", 0, 0))),
                        entityEntry);
    }


}
