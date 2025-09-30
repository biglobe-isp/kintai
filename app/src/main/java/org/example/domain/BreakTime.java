package org.example.domain;

public record BreakTime(int startMinutes, int endMinutes){
    public int getTotalMinutes(){
        return endMinutes - startMinutes;
    }

    public boolean isInclude(int workStartMinutes, int workEndMinutes){
        return this.startMinutes > workStartMinutes && this.endMinutes < workEndMinutes;
    }
}