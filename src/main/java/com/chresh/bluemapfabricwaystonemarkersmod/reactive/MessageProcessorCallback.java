package com.chresh.bluemapfabricwaystonemarkersmod.reactive;

@FunctionalInterface
public interface MessageProcessorCallback<T> {
    void processMessage(T message);
}
