package domain;

import java.io.FileReader;
import java.time.LocalDateTime;

public class Domain {

    //日付（YYYYMMDD）
    private String date = null;
    //開始時刻（MMDD）
    private String start = null;
    //終了時刻（MMDD）
    private String end = null;
    //現在日時
    private String now = null;
    //開始時刻（MM）
    private int startH = 0;
    //開始時刻（DD）
    private int startM = 0;
    //終了時刻（MM）
    private int endH = 0;
    //終了時刻（DD）
    private int endM = 0;
    //労働時間（DD）
    private int workMinutes = 0;
    //残業時間（DD）
    private int overWorkMinutes = 0;
    //労働時間合計（M）
    private int totalWorkMinutes = 0;
    //残業時間合計（M）
    private int totalOverWorkMinutes = 0;
    //労働時間合計（M)と残業時間合計（M）を持つ配列
    private int[] totalNums = null;

    /*
     *
     *
     */
    public void checkArgsLength(int length) {
        //引数0個ならエラー
        if (length < 1) {
            throw new RuntimeException("引数が足りません");
        }
    }

    /*
     *
     *
     */
    public void inputData(String[] args, IRepository iRepository) {
        if (args.length < 4) {
            throw new RuntimeException("引数が足りません");
        }
        date = args[1];
        start = args[2];
        end = args[3];
        now = LocalDateTime.now().toString();
        startH = Integer.valueOf(start.substring(0, 2));
        startM = Integer.valueOf(start.substring(2, 4));
        endH = Integer.valueOf(end.substring(0, 2));
        endM = Integer.valueOf(end.substring(2, 4));

        workMinutes = endH * 60 + endM - (startH * 60 + startM);

        if(endH == 12) {
            workMinutes -= endM;
        } else if(endH >= 13) {
            workMinutes -= 60;
        }

        if(endH == 18) {
            workMinutes -= endM;
        } else if(endH >= 19) {
            workMinutes -= 60;
        }

        if(endH == 21) {
            workMinutes -= endM;
        } else if(endH >= 22) {
            workMinutes -= 60;
        }

        int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);

        iRepository.writeData(args, workMinutes, overWorkMinutes);
        //iRepo.writeData();

    }


    /*
     *
     *
     */

//    public void outputData(String[] args) {
//        if (args.length < 2) {
//            throw new RuntimeException("引数が足りません");
//        }
//
//        String yearMonth = args[1];
//
//
//
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
//
//            System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
//            System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
//        }
//
//    }


}
