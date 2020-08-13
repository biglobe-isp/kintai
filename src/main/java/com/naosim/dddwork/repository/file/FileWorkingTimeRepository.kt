package com.naosim.dddwork.repository.file

import com.naosim.dddwork.domain.WorkingTimeRange
import com.naosim.dddwork.domain.WorkingTimeRecord
import com.naosim.dddwork.domain.WorkingTimeRepository
import com.naosim.dddwork.domain.WorkingTimeRule
import com.naosim.dddwork.domain.vo.*
import java.io.FileOutputStream
import java.nio.file.Path
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

class FileWorkingTimeRepository(
        private val path: Path
) : WorkingTimeRepository {
    companion object {
        const val LINE_ITEM_INDEX_DATE = 0
        const val LINE_ITEM_INDEX_PUNCH_IN = 1
        const val LINE_ITEM_INDEX_PUNCH_OUT = 2
        // const val LINE_ITEM_INDEX_SCHEDULED_WORKING_TIME_SPAN = 3
        // const val LINE_ITEM_INDEX_EXTRA_WORKING_TIME_SPAN = 4
        const val LINE_ITEM_INDEX_PUNCHED_DATE_TIME = 5
    }

    override fun save(workingTimeRecord: WorkingTimeRecord) {
        synchronized(path) {
            FileOutputStream(path.toFile(), true).use {
                it.bufferedWriter().use {
                    it.appendln(String.format("%s,%s,%s,%s,%s,%s",
                            workingTimeRecord.workingDate.value.format(DateTimeFormatter.ofPattern("uuuu-MM-dd")),
                            workingTimeRecord.workingTimeRange.start.value.format(DateTimeFormatter.ofPattern("HHmm")),
                            workingTimeRecord.workingTimeRange.end.value.format(DateTimeFormatter.ofPattern("HHmm")),
                            workingTimeRecord.workingTimeRange.actualWorkingTimeSpan.value.toMinutes(),
                            workingTimeRecord.workingTimeRange.extraWorkingTimeSpan.value.toMinutes(),
                            workingTimeRecord.punchedDateTime.value.format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS"))
                    ))
                }
            }
        }
    }

    override fun findByYearAndMonth(year: Year, month: Month, workingTimeRule: WorkingTimeRule): List<WorkingTimeRecord> {
        val prefix: String = DateTimeFormatter.ofPattern("uuuu-MM").format(
                YearMonth.of(year.value, month.value)
        )

        return path.toFile().bufferedReader().lines()
                .filter {
                    it.startsWith(prefix)
                }.map {
                    it.split(",")
                }.map {
                    val workingDate = WorkingDate(LocalDate.from(DateTimeFormatter.ofPattern("uuuu-MM-dd").parse(it[LINE_ITEM_INDEX_DATE])))
                    val punchInTime = PunchInTime(LocalTime.from(DateTimeFormatter.ofPattern("HHmm").parse(it[LINE_ITEM_INDEX_PUNCH_IN])))
                    val punchOutTime = PunchOutTime(LocalTime.from(DateTimeFormatter.ofPattern("HHmm").parse(it[LINE_ITEM_INDEX_PUNCH_OUT])))
                    // リファクタリング前のプログラムでは使用されていたが、出退勤時刻から解決される値のため、ドメイン側で自動算出可能なように変更されている部分
                    // val scheduledWorkingTimeSpan = WorkingTimeRange.ScheduledWorkingTimeSpan(Duration.ofMinutes(it[LINE_ITEM_INDEX_SCHEDULED_WORKING_TIME_SPAN].toLong()))
                    // val extraWorkingTimeSpan = WorkingTimeRange.ExtraWorkingTimeSpan(Duration.ofMinutes(it[LINE_ITEM_INDEX_EXTRA_WORKING_TIME_SPAN].toLong()))
                    val punchedDateTime = PunchedDateTime(LocalDateTime.from(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS").parse(it[LINE_ITEM_INDEX_PUNCHED_DATE_TIME])))

                    WorkingTimeRecord(
                            workingDate = workingDate,
                            workingTimeRange = workingTimeRule.calcWorkingTimeRange(
                                    punchInTime = punchInTime,
                                    punchOutTime = punchOutTime

                            ),
                            punchedDateTime = punchedDateTime
                    )
                }.collect(Collectors.toList()).groupBy {
                    it.workingDate
                }.map {
                    it.value.maxBy { it.punchedDateTime }!!
                }
    }
}
