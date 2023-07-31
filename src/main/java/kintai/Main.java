package kintai;

import kintai.domain.EndWork;
import kintai.domain.InputAttendance;
import kintai.domain.StartWork;
import kintai.domain.WorkDay;

public class Main {
    public static void main(String[] args){

        WorkDay     workDay = new WorkDay(args[1]);
        StartWork startWork = new StartWork(args[2]);
        EndWork   endWork   = new EndWork((args[3]));
        InputAttendance Attendance = new InputAttendance(workDay,startWork,endWork);

        System.out.println(Attendance.getWorkDay());
        System.out.println(Attendance.getStartWork());
        System.out.println(Attendance.getEndWork());
         }
}
