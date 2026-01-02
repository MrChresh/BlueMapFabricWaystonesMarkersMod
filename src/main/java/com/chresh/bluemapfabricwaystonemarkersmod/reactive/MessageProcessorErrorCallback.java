package com.chresh.bluemapfabricwaystonemarkersmod.reactive;

@FunctionalInterface
public interface MessageProcessorErrorCallback {
    void onError(Throwable throwable);
}
