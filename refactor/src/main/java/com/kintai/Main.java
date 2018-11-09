package com.kintai;

import com.kintai.登録.勤務時間を登録するAPI;
import com.kintai.表示.勤務時間を表示するAPI;

public class Main {
    public static void main(String[] args) {
        try {
            if(args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];

            if("input".equals(methodType)) {
                勤務時間を登録するAPI.登録する(args);
                //引数を受け取ってるAPI
//                if(args.length < 4) {
//                    throw new RuntimeException("引数が足りません");
//                }

//                String date = args[1];
//                String start = args[2];
//                String end = args[3];
//                String now = LocalDateTime.now().toString();
//
//                //計算してるからドメイン
//                int startH = Integer.valueOf(start.substring(0, 2));
//                int startM = Integer.valueOf(start.substring(2, 4));
//
//                int endH = Integer.valueOf(end.substring(0, 2));
//                int endM = Integer.valueOf(end.substring(2, 4));
//
//                int workMinutes = endH * 60 + endM - (startH * 60 + startM);
//
//                if(endH == 12) {
//                    workMinutes -= endM;
//                } else if(endH >= 13) {
//                    workMinutes -= 60;
//                }
//
//                if(endH == 18) {
//                    workMinutes -= endM;
//                } else if(endH >= 19) {
//                    workMinutes -= 60;
//                }
//
//                if(endH == 21) {
//                    workMinutes -= endM;
//                } else if(endH >= 22) {
//                    workMinutes -= 60;
//                }
//
//                int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
//                //CSVに書き込んでるからデータソース
//                File file = new File("data.csv");
//                try(FileWriter filewriter = new FileWriter(file, true)) {
//                    filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, workMinutes, overWorkMinutes, now));
//                }

            } else if("total".equals(methodType)) {
                勤務時間を表示するAPI.表示する(args);
//                //API
//                if(args.length < 2) {
//                    throw new RuntimeException("引数が足りません");
//                }
//
//                String yearMonth = args[1];
//                int totalWorkMinutes = 0;
//                int totalOverWorkMinutes = 0;
//
//
//                //ファイルから取得するDatasource
//                File file = new File("data.csv");
//
//                try(
//                        FileReader fr = new FileReader(file);
//                        BufferedReader br = new BufferedReader(fr);
//                        ) {
//
//                    String line = br.readLine();
//                    Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
//                    Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
//                    while(line != null){
//                        String[] columns = line.split(",");
//                        if(!columns[0].startsWith(yearMonth)) {
//                            continue;
//                        }
//                        totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
//                        totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));
//
//                        line = br.readLine();
//                    }
//
//                    //計算するDomain
//                    Set<String> keySet = totalWorkMinutesMap.keySet();
//
//                    for(String key : keySet) {
//                        totalWorkMinutes += totalWorkMinutesMap.get(key);
//                        totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
//                    }
//
//                    System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
//                    System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
//                }

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}