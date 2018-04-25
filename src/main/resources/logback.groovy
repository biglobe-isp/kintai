import ch.qos.logback.classic.encoder.PatternLayoutEncoder

import java.nio.charset.Charset

import static ch.qos.logback.classic.Level.INFO
import static ch.qos.logback.classic.Level.TRACE

/**
 * プロジェクトごとにユニークな名前
 */
def LOG_DIR_NAME = "kintai-kanri"

// ログ出力先ファイルパス
def logFile = ""

// ログレベル
def logLevel = INFO

// ログの出力フォーマット
def LOG_FORMAT = "%d{yyyy/MM/dd HH:mm:ss.SSS} [%level] [%thread] [%logger{0}] %message [%logger] %n"

// ログのデフォルト文字コード
def DEFAULT_CHARSET = Charset.forName("UTF-8")

// ローカルだけ設定を変更
if (System.getProperty("os.name").startsWith("Mac")) {
    logFile = "build/tomcat/logs/${LOG_DIR_NAME}-application.log"
    logLevel = TRACE
}

// ログファイルへのログ出力設定
appender("FILE", RollingFileAppender) {
    file = logFile

    encoder(PatternLayoutEncoder) {
        charset = DEFAULT_CHARSET
        pattern = LOG_FORMAT
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = logFile + ".%d{yyyy-MM-dd}"
    }
}

// 標準出力へのログ出力設定
appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = DEFAULT_CHARSET
        pattern = LOG_FORMAT
    }
}

// ログレベル設定
// catalina.out が肥大化するので、標準出力へのログ出力は無効化
root(logLevel, ["FILE"])
logger("org.springframework", INFO, ["FILE"])
logger("org.dbunit", INFO, ["FILE"])
logger("org.mybatis", INFO, ["FILE"])
