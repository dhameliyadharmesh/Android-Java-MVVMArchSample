package com.common.livedata;

/**
 * @Authoer Dharmesh
 * @Date 07-03-2022
 * <p>
 * Information
 **/

/*Used as a wrapper for data that is exposed via a LiveData that represents an
 event.*/


public class EventData<T> {

    private T mContent;
    private boolean hasBeenHandled = false;

    public EventData(T content) {
        if (content == null) {
            throw new IllegalArgumentException("null values in Event are not allowed.");
        }
        mContent = content;
    }

    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return mContent;
        }
    }

    public T peekContent() {
        return mContent;
    }

    public boolean hasBeenHandled() {
        return hasBeenHandled;
    }
}