package com.common.livedata;

/**
 * @Authoer Dharmesh
 * @Date 07-03-2022
 * <p>
 * Information
 **/
public interface EventHandler<V>{
    void onEventUnHandled(V object);
}
