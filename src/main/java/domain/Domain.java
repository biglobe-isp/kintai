package domain;

public class Domain {

    public void input(DateVO date, StartVO start, EndVO end, CalcWorkTimeVO workTime, INowRepository iNowRepo, IRepository iRepo) {
        iRepo.writeData(date, start, end, workTime, iNowRepo);
    }


    public void total(IRepository iRepo) {
        iRepo.readData();

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

