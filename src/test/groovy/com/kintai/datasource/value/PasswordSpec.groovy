package com.kintai.datasource.value

import com.kintai.exception.ValidatorException
import spock.lang.Specification
import spock.lang.Unroll

class PasswordSpec extends Specification {
    def "正常系"() {
        when:
        Password actualPassword = new Password("Test")

        then:
        actualPassword.getPassword() == "Test"
    }

    @Unroll
    def "異常系(#description)"() {
        when:
        new Password(testPassword)

        then:
        ValidatorException e = thrown()
        e.getMessage() == errorMessage

        where:
        testPassword || errorMessage || description
        null || "パスワードは必須です。" || "必須エラー:null"
        "" || "パスワードは必須です。" || "必須エラー:空文字"
        " " || "パスワードは必須です。" || "必須エラー:半角スペースのみ"
        "　" || "パスワードは必須です。" || "必須エラー:全角スペースのみ"
    }
}
