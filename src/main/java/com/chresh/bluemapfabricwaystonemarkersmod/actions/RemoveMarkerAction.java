package com.chresh.bluemapfabricwaystonemarkersmod.actions;

import com.chresh.bluemapfabricwaystonemarkersmod.markers.MarkerIdentifier;

public class RemoveMarkerAction extends MarkerAction {
    public RemoveMarkerAction(MarkerIdentifier markerIdentifier) {
        super(markerIdentifier);
    }

    @Override
    public String toString() {
        return "RemoveMarkerAction{" +
                "markerIdentifier=" + getMarkerIdentifier() +
                '}';
    }
}