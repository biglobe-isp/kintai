package api;

import datasource.RepositoryDb;
import domain.DateVO;
import domain.EndTimeVO;
import domain.StartTimeVO;
import domain.WorkTimeVO;
import service.Service;

public class Api { //入出力を行う
    public static void main(String[] args) {

        ArgsAndCheckVO argsVO = new ArgsAndCheckVO(args);
        argsVO.checkArgsLength();

        if (argsVO.getMethodType().equals(MethodType.input)) {
            inputController(argsVO);

        } else if (argsVO.getMethodType().equals(MethodType.total)) {
            totalController(argsVO);

        } else {
            throw new RuntimeException("methodTypeが不正です");
        }

    }

    //TODO staticにしたがこれでいいのか検討
    public static void inputController(ArgsAndCheckVO argsVO) {
        argsVO.inputCheckArgsLength();

        Service sv = new Service();
        RepositoryDb repoDb = new RepositoryDb(); //Datasource

        DateVO dateVO = new DateVO(argsVO.getDate());
        StartTimeVO startVO = new StartTimeVO(argsVO.getStart());
        EndTimeVO endVO = new EndTimeVO(argsVO.getEnd());
        WorkTimeVO workVO = new WorkTimeVO(startVO, endVO);

        sv.inputService(dateVO, startVO, endVO, workVO, repoDb);
    }

    public static void totalController(ArgsAndCheckVO argsVO) {
        argsVO.totalCheckArgsLength();

        Service sv = new Service();
        RepositoryDb repoDb = new RepositoryDb(); //Datasource


    }


}
