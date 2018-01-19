# プロジェクト向けのカスタマイズ方法

## プロジェクト固有の設定

* Gradleファイル
 * 対象ファイル
     * etc/gradle/project.gradle
 * 対象項目
     * projectName
 * 注意点
     * gradleの設定とは別に、URLの申請が必要

* Logbackの設定ファイル
 * 対象ファイル
     * src/main/resources/logback.groovy
 * 対象項目
     * LOG_DIR_NAME

* クラホスのDBの接続設定
 * 対象ファイル
     * src/test/resources/properties/environment/cloudHosting.properties
 * 対象項目
     * jdbc.schema
     * jdbc.username
     * jdbc.password
 * 注意点
     * 事前にクラホスのOracleに、プロジェクト用のスキーマ／ユーザ名／パスワードを作成しておく

* アラーム識別子の設定
 * 対象ファイル
     * src/main/resources/META-INF/properties/project.properties
 * 対象項目
     * service.alarm.hiosIdentifier
     * service.alarm.alarmIdentifier
     * service.alarm.subSystemIdentifier
 * 注意点
     * プロパティの設定とは別に、HIOSの申請が必要

## サンプルの削除

* サンプルのコード削除
 * 対象ファイル
     * src/main/java/jp/co/biglobe/isp/sample/ ディレクトリ内のファイル全て
     * src/test/java/jp/co/biglobe/isp/sample/ ディレクトリ内のファイル全て

* サンプルのテーブル定義ファイル削除
 * 対象ファイル
     * etc/database/local/createtable/sample/ ディレクトリ内のファイル全て
     * etc/database/local/createsequence/sample/ ディレクトリ内のファイル全て

* サンプルのcomponent-scanの記述削除
 * 対象ファイル
     * src/main/resources/META-INF/component-scan.xml
