package api;

import datasource.RepositoryDb;
import domain.CalcWorkTimeVO;
import domain.Japan.*;
import service.Service;

public class Api {
    public static void main(String[] args) throws Exception {

        ArgsCheckVO argsVO = new ArgsCheckVO(args);
        argsVO.checkArgsLength();

//      引数：input 20180101 0900 2100　の場合
        if (argsVO.getMethodType().equals(MethodType.input)) {
            inputController(argsVO);

        } else if (argsVO.getMethodType().equals(MethodType.total)) {
            totalController(argsVO);

        } else {
            throw new RuntimeException("methodTypeが不正です");
        }

    }

    public static void inputController(ArgsCheckVO argsVO) throws Exception {
        argsVO.inputCheckArgsLength();
        ValueAdjustVO valueAdjust = new ValueAdjustVO(argsVO);


        DateVO date = new DateVO(valueAdjust.getDate());
        StartVO start = new StartVO(valueAdjust.getStart());
        EndVO end = new EndVO(valueAdjust.getEnd());

//        } else {
//            DateVO date = new DateVO(argsVO.getValue()[1]);
//            StartVO start = new StartVO(argsVO.getValue()[2]);
//            EndVO end = new EndVO(argsVO.getValue()[3]);
//        }

        Service sv = new Service();

        NowRepository nowRepo = new NowRepository(); //現在日時取得
        RepositoryDb repoDb = new RepositoryDb(); //Datasource

        StartHourVO startHour = new StartHourVO(valueAdjust.getIntStartH());
        StartMinutesVO startMinutes = new StartMinutesVO(valueAdjust.getIntStartM());
        EndHourVO endHour = new EndHourVO(valueAdjust.getIntEndH());
        EndMinutesVO endMinutes = new EndMinutesVO(valueAdjust.getIntEndM());

        StartTimeVO startTime = new StartTimeVO(start, startHour, startMinutes); //TODO この辺をまとめる。
        EndTimeVO endTime = new EndTimeVO(end, endHour, endMinutes);
        CalcWorkTimeVO workTime = new CalcWorkTimeVO(startTime, endTime);

        sv.input(date, start, end, workTime, nowRepo, repoDb);
    }

    public static void totalController(ArgsCheckVO argsVO) {
        argsVO.totalCheckArgsLength();

        Service sv = new Service();
        DateVO date = new DateVO(argsVO.getValue()[1]);
        RepositoryDb repoDb = new RepositoryDb(); //Datasource

        sv.total(date, repoDb);
    }


}
