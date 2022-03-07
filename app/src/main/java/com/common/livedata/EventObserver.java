package com.common.livedata;

import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;

/**
 * @Authoer Dharmesh
 * @Date 07-03-2022
 * <p>
 * Information
 **/
public class EventObserver<T> implements Observer<EventData<T>> {
    private EventHandler<T> onEventUnhandledContent;

    public EventObserver(@NotNull EventHandler<T> onEventUnhandledContent) {
        this.onEventUnhandledContent = onEventUnhandledContent;
    }

    @Override
    public void onChanged(EventData<T> event) {
        if(event != null){
            onEventUnhandledContent.onEventUnHandled(event.getContentIfNotHandled());
        }
    }
}
