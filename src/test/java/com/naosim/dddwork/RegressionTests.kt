package com.naosim.dddwork

import com.naosim.dddwork.presentation.CommandLine
import jdk.nashorn.internal.ir.annotations.Ignore
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.file.Paths

class RegressionTests {
    @Test
    fun `inputコマンドの出力結果がリファクタ前の出力と一致している`() {
        val commandArgs: List<Array<String>> = (1..31).map {
            arrayOf("input", String.format("2020-07-%02d", it), "0900", "1900")
        } + listOf(
                arrayOf("input", "2020-07-10", "0900", "1600"),
                arrayOf("input", "2020-07-20", "0800", "1900"),
                arrayOf("input", "2020-07-30", "0900", "1930"),
                arrayOf("input", "2020-07-31", "0900", "2330")
        )

        val expected = "target/data_test_ext.csv".let {
            System.setProperty("kintai.file.path", it)
            commandArgs.forEach {
                Main.main(it)
            }

            Paths.get(it).toFile()
                    .readLines()
                    .filter { it.isNotEmpty() }
                    .map { it.substringBeforeLast(',') } // 打刻日時は条件から除外する
        }

        val actual = "target/data_test_act.csv".let {
            System.setProperty("kintai.file.path", it)
            commandArgs.forEach {
                CommandLine.main(it)
            }

            Paths.get(it).toFile()
                    .readLines()
                    .filter { it.isNotEmpty() }
                    .map { it.substringBeforeLast(',') } // 打刻日時は条件から除外する
        }

        Assertions.assertThat(actual).containsExactly(*expected.toTypedArray())
    }

    @Test
    fun `totalコマンドの出力結果がリファクタ前の出力と一致している`() {
        System.setProperty("kintai.file.path",
                RegressionTests::class.java.getResource("RegressionTests_data.csv").path)

        val originOut = System.out

        try {
            val strBuf = ByteArrayOutputStream()
            System.setOut(PrintStream(strBuf))

            arrayOf("total", "2020-07").let {
                Main.main(it)
                val expected = strBuf.toString("UTF-8")
                strBuf.reset()

                CommandLine.main(it)
                val actual = strBuf.toString("UTF-8")
                strBuf.reset()

                Assertions.assertThat(actual).isEqualTo(expected)
            }
        } finally {
            System.setOut(originOut)
        }
    }
}
