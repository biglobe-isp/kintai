package com.naosim.dddwork.presentation

import com.naosim.dddwork.domain.TotalWorkingTimeSummary
import com.naosim.dddwork.domain.WorkingTimeRepository
import com.naosim.dddwork.domain.vo.*
import com.naosim.dddwork.repository.file.FileWorkingTimeRepository
import com.naosim.dddwork.service.WorkingTimePunchService
import com.naosim.dddwork.service.WorkingTimeSummationService
import java.nio.file.Paths
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CommandLine(
        private val workingTimePunchService: WorkingTimePunchService,
        private val workingTimeSummary: WorkingTimeSummationService

) {
    companion object {
        private const val SYSPROP_NAME_FILE_PATH = "kintai.file.path"
        private const val DEFAULT_FILE_PATH = "data.csv"

        @JvmStatic
        fun main(args: Array<String>) {
            // 依存関係管理
            val repos: WorkingTimeRepository = FileWorkingTimeRepository(Paths.get(
                    System.getProperty(SYSPROP_NAME_FILE_PATH) ?: DEFAULT_FILE_PATH
            ))
            val punchService = WorkingTimePunchService(repos)
            val summationService = WorkingTimeSummationService(repos)
            val command = CommandLine(
                    workingTimePunchService = punchService,
                    workingTimeSummary = summationService
            )

            if (args.isEmpty()) {
                throw IllegalArgumentException("コマンドライン引数がありません。")
            }

            when(val methodType: String = args[0]) {
                "input" -> {
                    if (args.size < 4) {
                        throw IllegalArgumentException("コマンドライン引数が不足しています。")
                    }
                    command.input(args[1], args[2], args[3])
                }
                "total" -> {
                    if (args.size < 2) {
                        throw IllegalArgumentException("コマンドライン引数が不足しています。")
                    }
                    command.printTotal(args[1])
                }
                else -> {
                    throw IllegalArgumentException("サポートされていない処理タイプです。 $methodType")
                }
            }
        }
    }

    fun input(date: String, start: String, end: String) {
        val workingDate = WorkingDate(LocalDate.from(DateTimeFormatter.ofPattern("uuuu-MM-dd").parse(date)))
        val punchInTime = PunchInTime(LocalTime.from(DateTimeFormatter.ofPattern("HHmm").parse(start)))
        val punchOutTime = PunchOutTime(LocalTime.from(DateTimeFormatter.ofPattern("HHmm").parse(end)))

        workingTimePunchService.punch(
                workingDate = workingDate,
                punchInTime = punchInTime,
                punchOutTime = punchOutTime
        )
    }

    fun printTotal(yearMonthString: String) {
        val yearMonth: YearMonth = YearMonth.from(DateTimeFormatter.ofPattern("uuuu-MM").parse(yearMonthString))
        val summary: TotalWorkingTimeSummary = workingTimeSummary.summarize(
                Year(yearMonth.year), Month(yearMonth.monthValue))

        println("勤務時間: ${summary.totalWorkingTimeSpan.hoursPart.value}時間${summary.totalWorkingTimeSpan.minutesPart.value}分")
        println("残業時間: ${summary.totalExtraWorkingTimeSpan.hoursPart.value}時間${summary.totalExtraWorkingTimeSpan.minutesPart.value}分")
    }
}
