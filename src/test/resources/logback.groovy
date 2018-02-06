import ch.qos.logback.classic.encoder.PatternLayoutEncoder

import java.nio.charset.Charset


// ログの出力フォーマット
def LOG_FORMAT = "%d{yyyy/MM/dd HH:mm:ss.SSS} %-5p [%t] %c:%M\\(%F:%L\\) %p %m%n"

// ログのデフォルト文字コード
def DEFAULT_CHARSET = Charset.forName("UTF-8")

def filePathPrefix = "build/tomcat/logs"

def filePath = "${filePathPrefix}/test.log"


// ログファイルへのログ出力設定
appender("FILE", RollingFileAppender) {
    file = filePath
    encoder(PatternLayoutEncoder) {
        charset = DEFAULT_CHARSET
        pattern = LOG_FORMAT
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${filePath}.%d{yyyy-MM-dd}"
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
root(ERROR, ["FILE", "STDOUT"])