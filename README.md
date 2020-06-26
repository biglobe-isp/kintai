# 開発チュートリアル（勤怠管理）

## Setup
```
mkdir -p ~/.m2
mv ~/.m2/settings.xml ~/.m2/settings.xml.origin
cp ./settings.xml ~/.m2/settings.xml
```

## How to Build
```
./mvnw clean package
```

## How to Run
```
java -jar target/kintai-1.0.0.jar input 2020-06-26 1404 1800

cat data.csv
2020-06-26,1404,1800,176,0,2020-06-26T14:04:48.430
```

----

## 概要

勤怠管理アプリの[既存コード](./kintai_kadai_main.java)を元に
[課題](./kintai_kadai.md)に取り組むためのリポジトリです。

## 作業手順

- 本リポジトリをローカルにクローンし、ブランチ名「`refactor/[メールアカウント名]`」でブランチを切って作業を行ってください
- Githubリポジトリには、作成したブランチをプッシュするようにしてください（チュートリアル実施者の作業内容を解答例として後世に引き継ぐため）
