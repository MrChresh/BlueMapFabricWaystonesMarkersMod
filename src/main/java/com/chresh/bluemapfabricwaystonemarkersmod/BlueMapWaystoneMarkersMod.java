package com.chresh.bluemapfabricwaystonemarkersmod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chresh.bluemapfabricwaystonemarkersmod.entity.WaystoneEntry;
import com.chresh.bluemapfabricwaystonemarkersmod.entity.WaystoneManager;
import com.chresh.bluemapfabricwaystonemarkersmod.util.Constants;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.SharedConstants;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.*;

import wraith.fwaystones.FabricWaystones;
import wraith.fwaystones.util.WaystoneStorage;

public class BlueMapWaystoneMarkersMod implements DedicatedServerModInitializer {

	private ScheduledExecutorService scheduler;
	private static final Logger LOGGER = LoggerFactory.getLogger(Constants.MOD_ID);

	@Override
	public void onInitializeServer() {
		ServerLifecycleEvents.SERVER_STARTING.register(this::onServerStarting);
		ServerLifecycleEvents.SERVER_STOPPING.register(this::onServerStopping);

		ServerLifecycleEvents.SERVER_STARTED.register(this::markerRunner);

	}

	private void markerRunner(MinecraftServer server) {
		ArrayList<WaystoneEntry> entityEntries = new ArrayList<>();

		// org.apache.logging.log4j.core.config.Configurator.setLevel(Constants.MOD_ID,Level.DEBUG);

		if (server != null) {
			// MinecraftServer server = MinecraftClient.getInstance().getServer();

			this.scheduler = Executors.newScheduledThreadPool(1); // 1 is the size of the thread pool being created

			scheduler.scheduleAtFixedRate(() -> {

				for (WaystoneEntry entityEntry : entityEntries) {
					WaystoneManager.remove(entityEntry);
				}

				entityEntries.clear();
				LOGGER.debug("Entity...");

				server.getWorlds().forEach(world -> {

					LOGGER.debug("{}", world);
					LOGGER.debug("{}", world.getRegistryKey().getValue().toString());

					try {
						NbtCompound compoundTag = world.getPersistentStateManager().readNbt("fw_waystones",
								SharedConstants.getGameVersion().getProtocolVersion());

						LOGGER.debug("{}", compoundTag.getCompound("data"));
						LOGGER.debug("{}", compoundTag.getCompound("data").get("fwaystones"));
						LOGGER.debug("{}", compoundTag.getCompound("data").get("fwaystones").getClass());

						NbtList list = (NbtList) compoundTag.getCompound("data").get("fwaystones");

						list.forEach(entry -> {
							LOGGER.debug("{}", entry);
							LOGGER.debug("{}", entry.getType());

							NbtCompound compound = (NbtCompound) entry;

							LOGGER.debug("{}", compound.getIntArray("position"));
							LOGGER.debug("{}", compound.getString("dimension"));
							LOGGER.debug("{}", compound.get("position"));
							LOGGER.debug("{}", compound.get("position").getType());

							String name = compound.getString("name");
							String dimension = compound.getString("dimension");
							int[] position = compound.getIntArray("position");

							LOGGER.debug("{}", compound.getString("hash").toString());

							LOGGER.debug("{}", FabricWaystones.WAYSTONE_STORAGE.isGlobal(compound.getString("hash")));

							if (FabricWaystones.WAYSTONE_STORAGE.isGlobal(compound.getString("hash"))) {
								entityEntries.add(new WaystoneEntry(
										new BlockPos(position[0],
												position[1],
												position[2]).toCenterPos(),
										null,
										null,
										name,
										dimension,
										position[0],
										position[1],
										position[2],
										null));

							}
						});
					} catch (IOException ignored) {

					}
				});

				for (WaystoneEntry entityEntry : entityEntries) {
					WaystoneManager.addOrUpdate(entityEntry);
				}
			}, 10, 10, TimeUnit.SECONDS);

		}
	}

	public void shutdown() {
		this.scheduler.shutdownNow();
		WaystoneManager.stop();
	}

	private void onServerStarting(MinecraftServer server) {

	}

	private void onServerStopping(MinecraftServer server) {
		shutdown();
	}
}
