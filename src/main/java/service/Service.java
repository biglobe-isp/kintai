package service;

//import datasource.RepositoryDb; //serviceからdatasourceを参照してはいけない

import api.ArgsAndCheckVO;
import domain.*;

public class Service {
    Domain dm = new Domain();
    public void inputService(DateVO dateVO, StartTimeVO startVO, EndTimeVO endVO, WorkTimeVO workVO, IRepository iRepo) {
//        try {

        dm.inputData(dateVO, startVO, endVO, workVO, iRepo);

//            //TODO 分岐処理がService層で良いのか検討する
//            if (argsAndCheckVO.getMethodType().equals(MethodType.input)) {
//                argsAndCheckVO.inputCheckArgsLength();
//
//                StartHourVO startHour = new StartHourVO(argsAndCheckVO);
//                StartMinutesVO startMinutes = new StartMinutesVO(argsAndCheckVO);
//                EndHourVO endHour = new EndHourVO(argsAndCheckVO);
//                EndMinutesVO endMinutes = new EndMinutesVO(argsAndCheckVO);
//
//                StartTimeVO startTimeVO = new StartTimeVO(startHour, startMinutes);
//                EndTimeVO endTimeVO = new EndTimeVO(endHour, endMinutes);
//                BreakTimeVO breakTimeVO = new BreakTimeVO(endHour, endMinutes);
//                WorkTimeVO workTimeVO = new WorkTimeVO(startTimeVO, endTimeVO, breakTimeVO);
//
//                dm.inputData(iRepository, workTimeVO);
//
//            } else if (argsAndCheckVO.getMethodType().equals(MethodType.total)) {
//                argsAndCheckVO.totalCheckArgsLength();
//
//
//                dm.outputData(iRepository);
//            } else {
//                throw new RuntimeException("methodTypeが不正です");
//            }

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    public void totalService(){

    }
}
