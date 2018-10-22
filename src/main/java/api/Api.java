package api;

import datasource.RepositoryDb;
import domain.*;
import service.Service;

public class Api {
    public static void main(String[] args) {

        ArgsCheckVO argsVO = new ArgsCheckVO(args);
        argsVO.checkArgsLength();

        if (argsVO.getMethodType().equals(MethodType.input)) {
            inputController(argsVO);

        } else if (argsVO.getMethodType().equals(MethodType.total)) {
            totalController(argsVO);

        } else {
            throw new RuntimeException("methodTypeが不正です");
        }

    }

    public static void inputController(ArgsCheckVO argsVO) {
        argsVO.inputCheckArgsLength();

        Service sv = new Service();

        //VOをまとめて定義
        DateVO date = new DateVO(argsVO.getValue()[1]);
        StartVO start = new StartVO(argsVO.getValue());
        EndVO end = new EndVO(argsVO.getValue());
        ValueAdjustVO valueAdjust = new ValueAdjustVO(argsVO.getValue());
        NowRepository nowRepo = new NowRepository();
        RepositoryDb repoDb = new RepositoryDb(); //Datasource

        StartHourVO startHour = new StartHourVO(valueAdjust.getIntStartH());
        StartMinutesVO startMinutes = new StartMinutesVO(valueAdjust.getIntStartM());
        EndHourVO endHour = new EndHourVO(valueAdjust.getIntEndH());
        EndMinutesVO endMinutes = new EndMinutesVO(valueAdjust.getIntEndM()); //TODO この辺もっとスマートに書けないものか。。

        StartTimeVO startTime = new StartTimeVO(start, startHour, startMinutes);
        EndTimeVO endTime = new EndTimeVO(end, endHour, endMinutes);

        CalcWorkTimeVO workTime = new CalcWorkTimeVO(startTime, endTime);

        sv.input(date, start, end, workTime, nowRepo, repoDb);
    }

    public static void totalController(ArgsCheckVO argsVO) {
        argsVO.totalCheckArgsLength();

        Service sv = new Service();
        RepositoryDb repoDb = new RepositoryDb(); //Datasource

        sv.total(repoDb);
    }


}
