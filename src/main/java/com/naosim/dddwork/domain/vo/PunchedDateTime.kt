package com.naosim.dddwork.domain.vo

import java.time.LocalDateTime

class PunchedDateTime(
        value: LocalDateTime
) : DateTime(value) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        return true
    }
}
