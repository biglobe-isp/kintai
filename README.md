# 開発チュートリアル（勤怠管理）

## 概要

勤怠管理アプリの[既存コード](./kintai_kadai_main.java)を元に
[課題](./kintai_kadai.md)に取り組むためのリポジトリです。

## 作業手順

- 本リポジトリをローカルにクローンし、ブランチ名「`refactor/[メールアカウント名]`」でブランチを切って作業を行ってください
- Githubリポジトリには、作成したブランチをプッシュするようにしてください（チュートリアル実施者の作業内容を解答例として後世に引き継ぐため）


## memo

### gradle init
```shell
% gradle --version

------------------------------------------------------------
Gradle 8.6
------------------------------------------------------------

Build time:   2024-02-02 16:47:16 UTC
Revision:     d55c486870a0dc6f6278f53d21381396d0741c6e

Kotlin:       1.9.20
Groovy:       3.0.17
Ant:          Apache Ant(TM) version 1.10.13 compiled on January 4 2023
JVM:          21.0.2 (Homebrew 21.0.2)
OS:           Mac OS X 13.6.4 aarch64

% gradle init   

Select type of project to generate:
  1: basic
  2: application
  3: library
  4: Gradle plugin
Enter selection (default: basic) [1..4] 2

Select implementation language:
  1: C++
  2: Groovy
  3: Java
  4: Kotlin
  5: Scala
  6: Swift
Enter selection (default: Java) [1..6] 3

Generate multiple subprojects for application? (default: no) [yes, no] no
Select build script DSL:
  1: Kotlin
  2: Groovy
Enter selection (default: Kotlin) [1..2] 2

Select test framework:
  1: JUnit 4
  2: TestNG
  3: Spock
  4: JUnit Jupiter
Enter selection (default: JUnit Jupiter) [1..4] 

Project name (default: kintai): 
Enter target version of Java (min. 7) (default: 21): 20
Generate build using new APIs and behavior (some features may change in the next minor release)? (default: no) [yes, no] no

> Task :init
To learn more about Gradle by exploring our Samples at https://docs.gradle.org/8.6/samples/sample_building_java_applications.html

BUILD SUCCESSFUL in 2m 37s
1 actionable task: 1 executed
```

### plantuml
- プラグインPlantUML Integrationを追加
- `brew install graphviz`を追加 クラス図が描ける

### kintai_kadai_main.java 実行
`kintai_kadai_main.java`を`app/src/main/java/Main.java`に追加

```shell
% pwd
~/IdeaProjects/kintai/app/src/main/java
% javac org/example/Main.java 
%
% java org.example.Main input 20240213 0900 2400
% java org.example.Main input 20240214 0900 1800
% java org.example.Main input 20240214 0900 2300
% java org.example.Main input 20240215 0900 1800
%
% java org.example.Main total 202402
勤務時間: 31時間0分
残業時間: 7時間0分
%
```
