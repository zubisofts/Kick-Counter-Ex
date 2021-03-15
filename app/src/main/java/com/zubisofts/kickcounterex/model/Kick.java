package com.zubisofts.kickcounterex.model;

public class Kick {
    private int id;
    private int kicksCount;
    private long startTime;
    private long lastTime;
    private long duration;

    public Kick(int kicksCount, long startTime, long lastTime, long duration) {
        this.kicksCount = kicksCount;
        this.startTime = startTime;
        this.lastTime = lastTime;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKicksCount() {
        return kicksCount;
    }

    public void setKicksCount(int kicksCount) {
        this.kicksCount = kicksCount;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
