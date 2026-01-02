package com.chresh.bluemapfabricwaystonemarkersmod.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.chresh.bluemapfabricwaystonemarkersmod.actions.ActionFactory;
import com.chresh.bluemapfabricwaystonemarkersmod.markers.MarkerSetIdentifierCollection;
import com.chresh.bluemapfabricwaystonemarkersmod.util.BlueMapAPIConnector;
import com.chresh.bluemapfabricwaystonemarkersmod.util.IResetHandler;

public class WaystoneManager implements IResetHandler {
    private static WaystoneManager instance;
    private static final Object mutex = new Object();

    private static WaystoneManager getInstance() {
        WaystoneManager result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    instance = result = new WaystoneManager();
                }
            }
        }
        
        return result;
    }

    public static void addOrUpdate(WaystoneEntry entityEntry) {
        getInstance().addOrUpdateEntity(entityEntry);
    }
    public static void remove(WaystoneEntry entityEntry) {
        getInstance().removeEntity(entityEntry);
    }

    public static List<WaystoneEntry> getAll() {
        return getInstance().getAllEntities();
    }
    public static void stop() {
        getInstance().shutdown();
    }

    private final BlueMapAPIConnector blueMapAPIConnector;
    private final ActionFactory actionFactory;
    private final ConcurrentMap<WaystoneEntryKey, WaystoneEntry> entityCache = new ConcurrentHashMap<>();

    private WaystoneManager() {
        MarkerSetIdentifierCollection markerSetIdentifierCollection = new MarkerSetIdentifierCollection();
        actionFactory = new ActionFactory(markerSetIdentifierCollection);
        blueMapAPIConnector = new BlueMapAPIConnector();
        
        blueMapAPIConnector.addResetHandler(this);
    }

    private List<WaystoneEntry> getAllEntities() {
        return new ArrayList<>(entityCache.values());
    }

    private void shutdown() {
        blueMapAPIConnector.shutdown();
    }

    private void removeEntity(WaystoneEntry entityEntry) {
        blueMapAPIConnector.dispatch(
                actionFactory.createRemovePOIAction(entityEntry));
            return;
    }

    private void addOrUpdateEntity(WaystoneEntry entityEntry) {;
            blueMapAPIConnector.dispatch(
                    actionFactory.createAddPOIAction(entityEntry));
            return;
        }



    @Override
    public void reset() {
        //reloadSigns();
    }
}
