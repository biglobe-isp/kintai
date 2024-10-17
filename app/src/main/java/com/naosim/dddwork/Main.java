package com.naosim.dddwork;

import com.naosim.dddwork.api.InputCommand;

/*
「kintai_kadai.md」より以下の課題実施
 1. 以下の「Main」クラスを「api」、「service」、「datasource」、「domain」に分割してリファクタリング
 2. 入力方法を「java Main input 20170101 0900 1800」→「java Main input -date:20170101 -start:09_00 -end:18_00」に変更対応
 3. data.csvをローカルの直置きではなく、外部のCSV管理ライブラリを使う形に変更　データ読み書きの合言葉は"Happy [day]" ex)"Happy Sunday"
 4. 15:00-16:00も休憩時間として除外するよう変更
 5. 有給休暇、午前休、午後休設定機能追加（この休みは労働時間として計算）
    入力例
    有給休暇：java Main input 20170101 v              →9:00-18:00労働扱い
    午前休　：java Main input 20170101 am -end:1800   →9:00-12:00労働扱い
    午後休　：java Main input 20170101 -start:0900 pm →12:00-18:00労働扱い（昼休憩の扱いはどうなる？）
 6. 朝フレックス設定機能追加 etc.

 素のビルド方法
 java -cp app/build/classes/java/main com.naosim.dddwork.Main (以降入力コマンド一緒)
 →課題のコマンド実行
 gradle4.9以降のビルド方法
 gradle run --args="(input/total以降のコマンド)"

 今後の方針
 1. ボトムアップDDD前後編読む
 2. 良いコード/悪いコード読む
 3. 勤怠チュートリアルリファクタ=↑の課題1（2-3同時並行でも）
 */

public class Main {
    // args: 実行コマンドのうち「java Main」以降空白で区切られた数値や文字列の配列
    // args[0]: 実行する機能（課題によるとinput:勤怠入力、total:集計）
    // args[1]以降：inputのみ存在。1:日付、2:開始時刻、3:終了時刻
    // フレックス無し&日付超えはサビ残扱いのため、2・3の取りうる値は0900-2359の範囲内にあるはず
    public static void main(String[] args) {
        InputCommand inputCommand = new InputCommand();
        String result = inputCommand.input(args);
        System.out.println(result);

        /*
        try {
            // 実行機能すら記載されていない場合の例外処理
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            // args[0]の値をString型の「methodType」として定義
            String methodType = args[0];

            // methodTypeの値がStringの「input」と等しいかどうか判定
            if ("input".equals(methodType)) {
                InputCommand inputCommand = new InputCommand();
                String result = inputCommand.input(args);
                System.out.println(result);
                /*
                // inputを実行する場合、実行機能名+入力項目で計4個以上必要なため、配列長が4より少ない場合、例外処理を実行
                if (args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }

                // 入力項目を配列から抽出して定義
                // args[1]を日付を示すString型の「date」として定義
                String date = args[1];
                // args[2]を開始時刻を示すString型の「start」として定義
                String start = args[2];
                // args[3]を終了時刻を示すString型の「end」として定義
                String end = args[3];
                // 現在のコンピュータの時刻を取得してString型の「now」として定義
                String now = LocalDateTime.now().toString();

                // String型のstartの最初から2文字だけ抽出して、Intに変換した値を開始時刻の時間を示すint型の「startH」に格納
                int startH = Integer.valueOf(start.substring(0, 2));
                // String型のstartの3文字目から2文字だけ抽出して、Intに変換した値を開始時刻の分を示すint型の「startM」に格納
                int startM = Integer.valueOf(start.substring(2, 4));

                // String型のendの最初から2文字だけ抽出して、Intに変換した値を終了時刻の時間を示すint型の「endH」に格納
                int endH = Integer.valueOf(end.substring(0, 2));
                // String型のendの3文字目から2文字だけ抽出して、Intに変換した値を終了時刻の分を示すint型の「endM」に格納
                int endM = Integer.valueOf(end.substring(2, 4));

                // 終了時刻と開始時刻の差を取り、int型の「workMinutes」として定義（分で計算するため、startH、endHを60倍して計算）
                int workMinutes = endH * 60 + endM - (startH * 60 + startM);

                // 休憩時間の分まで計算している場合に、その分を除外
                // 12:00-12:59に終了した場合、endMの分だけworkMinutesから除外
                if (endH == 12) {
                    workMinutes -= endM;
                }
                // 13時以降に終了した場合は、12:00-12:59の60分だけworkMinutes除外
                else if (endH >= 13) {
                    workMinutes -= 60;
                }

                // 18:00-18:59に終了した場合、endMの分だけworkMinutesから除外
                if (endH == 18) {
                    workMinutes -= endM;
                }
                // 19時以降に終了した場合は、18:00-19:59の60分だけworkMinutes除外
                else if (endH >= 19) {
                    workMinutes -= 60;
                }

                // 21:00-21:59に終了した場合、endMの分だけworkMinutesから除外
                if (endH == 21) {
                    workMinutes -= endM;
                }
                // 22時以降に終了した場合は、21:00-21:59の60分だけworkMinutes除外
                else if (endH >= 22) {
                    workMinutes -= 60;
                }

                // workMinutesから所定労働時間である8時間分を引いた値を残業した時間を示すint型の「overWOrkMinutes」として定義
                int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
                // 「data」という名前のcsvファイルをFile型の「file」として定義
                File file = new File("data.csv");

                // FileWriter型の「fileWriter」を定義し、file=data.csvに書き込み（appendは上書き可のこと？）
                try (FileWriter filewriter = new FileWriter(file, true)) {
                    // それぞれの%sにdate、start、end、workMinutes、overWorkMinutes、nowを代入し、改行文字を入れることで、
                    // data.csvに勤怠情報を1行ずつ追加
                    filewriter.write(String.format(
                            "%s,%s,%s,%s,%s,%s\n",
                            date,
                            start,
                            end,
                            workMinutes,
                            overWorkMinutes,
                            now
                    ));
            }
            // methodTypeの値がStringの「total」と等しいかどうか判定
            else if ("total".equals(methodType)) {
                // totalを実行する場合、実行機能名+入力項目で計2個以上必要？（課題の入力例だと入力項目0）なため、
                // 配列長が2より少ない場合、例外処理を実行
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }

                // arg[1]を、集計する年月を示すString型の「yearMonth」として定義
                String yearMonth = args[1];

                // 合計労働時間を示すint型の「totalWorkMinutes」の値を0として定義
                int totalWorkMinutes = 0;
                // 合計残業時間を示すint型の「totalOverWorkMinutes」の値を0として定義
                int totalOverWorkMinutes = 0;

                // 「data」という名前のcsvファイルをFile型の「file」として定義
                File file = new File("data.csv");

                try (
                        // fileの読み込んだデータをFileReader型の「fr」として定義
                        FileReader fr = new FileReader(file);
                        // frをプログラムが解読できるオブジェクトに変換？するため、BufferedReader型の「br」として定義
                        BufferedReader br = new BufferedReader(fr)
                ) {
                    // brの1番目の行の文字列をString型の「line」として定義
                    String line = br.readLine();
                    // 総労働時間の連想配列（辞書型？）　Key:String型で年月日、Value:Integer型でその日の合計労働時間
                    Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
                    // 総残業時間の連想配列（辞書型？）　Key:String型で年月日、Value:Integer型でその日の合計残業時間
                    Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
                    // 1行ずつ読み込んでいき、lineが読み込む内容がnull=最後の行に到達するまで繰り返す
                    while (line != null) {
                        // 元のデータでは、値ごとにカンマ区切りで記述するようにしているので、カンマで文字列を分割するようにし、
                        // String型の配列である「columns」として定義
                        String[] columns = line.split(",");
                        // columns[0]=年月日が「yearMonth」と同じ文字列=集計の対象になっていない場合はデータをスキップして次の行へ？
                        // （このままだと次の行に読まないで無限ループするかも？）
                        if (!columns[0].startsWith(yearMonth)) {
                            continue;
                        }
                        // 集計対象の場合は、totalWorkMinutesMapとtotalOverWorkMinutesMapにデータを追加
                        // String型に年月日を示すcolumns[0]を入れ、Integer型に労働時間を示すcolumns[3]をInteger型に変換して入れる
                        totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                        // String型に年月日を示すcolumns[0]を入れ、Integer型に残業時間を示すcolumns[3]をInteger型に変換して入れる
                        totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                        // 次の行を読み込みlineに代入
                        line = br.readLine();
                    }

                    // totalWorkMinutesMapからKeyのみを抽出して、新しいString型の配列？である「keySet」を定義
                    Set<String> keySet = totalWorkMinutesMap.keySet();
                    // keySetを1つずつ取り出してString型の「key」に代入→keySetから代入するものが無くなるまで繰り返し（=foreach）
                    // このkeySetは年月日の集合体なので、日付を二重登録されない限り一意であるはず
                    // →機能説明読む感じ上書きあり？appendで上書きできるなら大丈夫そう
                    for (String key : keySet) {
                        // key=年月日でtotalWorkMinutesMapを検索し、
                        // 見つけたInteger型の合計労働時間をtotalWorkMinutesに加算
                        totalWorkMinutes += totalWorkMinutesMap.get(key);
                        // key=年月日でtotalOverWorkMinutesMapを検索し、
                        // 見つけたInteger型の合計労働時間をtotalOverWorkMinutesに加算
                        totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
                    }

                    // 合計労働時間と合計残業時間をそれぞれ出力
                    // ○時間□分として出力するため、
                    // ○はtotalWorkMinutesを60で割った商、□はtotalWorkMinutesを60で割った余りで計算
                    System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
                    // ○時間□分として出力するため、
                    // ○はtotalOverWorkMinutesを60で割った商、□はtotalOverWorkMinutesを60で割った余りで計算
                    System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
                }
            } else {
                // input、total以外の文字列をargs[0]の位置に入力した場合、不正コマンドとして例外処理
                throw new RuntimeException("methodTypeが不正です");
            }

        } catch (Exception e) {
            // 処理中にどこかでエラーが発生した場合、エラー原因を出力
            e.printStackTrace();
        }
         */
    }
}