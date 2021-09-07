package com.ljp.android_reader.bean;

public class Other {
    private boolean status;

    public int getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    private int lastPosition;

    private int lengthForReadActivity;

    private byte[] bytesForReadActivity;

    public byte[] getBytesForReadActivity() {
        return bytesForReadActivity;
    }

    public void setBytesForReadActivity(byte[] bytesForReadActivity) {
        this.bytesForReadActivity = bytesForReadActivity;
    }

    public int getLengthForReadActivity() {
        return lengthForReadActivity;
    }

    public void setLengthForReadActivity(int lengthForReadActivity) {
        this.lengthForReadActivity = lengthForReadActivity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
