package service;

//import datasource.RepositoryDb; //serviceからdatasourceを参照してはいけない

import domain.Domain;
import domain.IRepository;
import domain.TermVO;

public class Service {
    public void service(ArgsVO argsVO, IRepository iRepository) {
        try {
            Domain dm = new Domain();
            argsVO.checkArgsLength();
            //プリミティブ型は禁止
            //String methodType = argsVO.getArgs()[0];


            //TODO 分岐処理がService層で良いのか検討する
            if (argsVO.getMethodType().equals(MethodType.input)) {
                argsVO.inputCheckArgsLength();

                StartHourVO startHour = new StartHourVO(argsVO);
                StartMinutesVO startMinutes = new StartMinutesVO(argsVO);
                EndHourVO endHour = new EndHourVO(argsVO);
                EndMinutesVO endMinutes = new EndMinutesVO(argsVO);

                StartTimeVO startTimeVO = new StartTimeVO(startHour, startMinutes);
                EndTimeVO endTimeVO = new EndTimeVO(endHour, endMinutes);
                BreakTimeVO breakTimeVO = new BreakTimeVO(endHour, endMinutes);
                TermVO termVO = new TermVO(startTimeVO, endTimeVO, breakTimeVO);

                dm.inputData(iRepository, termVO);

            } else if (argsVO.getMethodType().equals(MethodType.total)) {
                argsVO.totalCheckArgsLength();


                dm.outputData(iRepository);
            } else {
                throw new RuntimeException("methodTypeが不正です");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
