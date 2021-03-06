# 勤怠管理

## 機能
### 勤怠入力
- 1日分の勤怠データを入力する
- 入力データ: 日付・開始時刻・終了時刻
- 修正したい場合は、同じ日付で再投入すればok
- 入力例
```
java Main input 20170101 0900 1800
```

### 集計
- 1ヶ月分の集計結果を表示する
- 内容
  - 勤務時間
  - 残業時間
  - ※休憩時間をちゃんと除くこと
- 入力例
```
java Main total
```

## 就業規則
- 始業: 09:00
- 就業: 18:00
- 休憩: 12:00-13:00, 18:00-19:00, 21:00-22:00
- その他
  - フレックスなし
  - 遅刻は存在しない 10時出勤とかありえない。即クビである。
  - 早退はアリ
  - 作業開始から8時間経過以降を残業とする
  - 日付を越えたらサービス残業

## 課題
### リファクタ
コードをリファクタせよ
- パッケージ
  - api
  - service
  - datasource
  - domain

### 仕様変更 入力方法変更
```
java Main input -date:20170101 -start:09_00 -end:18_00
```

### インフラ変更 外部のCSV管理ライブラリを使う
- オレオレCSV管理ライブラリ廃止
- CSV管理ライブラリには引数に合言葉が必要
- 合言葉は、"Happy [day]"
  - 例えば今日が日曜日なら、"Happy Sunday"

※ライブラリは私が作ります。。

### 仕様変更 来月の途中から休憩時間が増える
来月15日から休憩が増えます。時間は15:00-16:00。対応してね。

### 案：機能追加 有給、午前休、午後休を申請できる
- 休んでる最中は働いたと見なす
   - 休暇: 9-18働いた
    - 午前休: 9-12働いた
     - 午後休: 12-18働いた
- 休暇
```
java Main input 20170101 v
```
- 午前休
```
java Main input 20170101 am -end:1800
```

- 午後休
```
java Main input 20170101 -start:0900 pm
```

### 案：ユーザ概念追加、朝フレックスが許可とか
