package domain;

public class Domain {

    /*
     *
     *
     */
    public void inputData(DateVO dateVO, StartTimeVO startVO, EndTimeVO endVO, WorkTimeVO workVO, IRepository iRepo) { //TODO  Service層を使わないようにする

//        StartHourVO startH = new StartHourVO(Integer.valueOf(argsAndCheckVO.getArgs()[2].substring(0, 2)));
//        StartM_VO startM = new StartM_VO(Integer.valueOf(argsAndCheckVO.getArgs()[2].substring(2, 4)));
//        EndH_VO endH = new EndH_VO(Integer.valueOf(argsAndCheckVO.getArgs()[3].substring(0, 2)));
//        EndM_VO endM = new EndM_VO(Integer.valueOf(argsAndCheckVO.getArgs()[3].substring(2, 4)));


        //TODO 休憩時間を切り出す
        //workMinutes = endH * 60 + endM - (startH.getStartHour() * 60 + startM);


//        if (endH == 12) {
//            workMinutes -= endM;
//        } else if (endH >= 13) {
//            workMinutes -= 60;
//        }
//
//        if (endH == 18) {
//            workMinutes -= endM;
//        } else if (endH >= 19) {
//            workMinutes -= 60;
//        }
//
//        if (endH == 21) {
//            workMinutes -= endM;
//        } else if (endH >= 22) {
//            workMinutes -= 60;
//        }
        //TODO VO内で計算するロジックにし削除
        //int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
        iRepo.writeData(dateVO, startVO, endVO, workVO);

    }


    /*
     *
     *
     */

    public void outputData(IRepository iRepository) { //TODO  Service層を使わないように吸える
        //Serviceへ
//        if (argsVO.getArgs().length < 2) {
//            throw new RuntimeException("引数が足りません");
//        }
//
//        iRepository.readData(argsVO, iRepository);
        iRepository.readData();

//        File file = new File("data.csv");
//
//        try(
//                FileReader fr = new FileReader(file);
//                BufferedReader br = new BufferedReader(fr);
//        ) {
//
//            String line = br.readLine();
//            Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
//            Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
//            while(line != null){
//                String[] columns = line.split(",");
//                if(!columns[0].startsWith(yearMonth)) {
//                    continue;
//                }
//                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
//                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));
//
//                line = br.readLine();
//            }
//
//            Set<String> keySet = totalWorkMinutesMap.keySet();
//            for(String key : keySet) {
//                totalWorkMinutes += totalWorkMinutesMap.get(key);
//                totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
//            }

//        System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
//        System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
    }


}

