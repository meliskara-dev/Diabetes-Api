package com.melis.diabetes_api.entry.dto;

import com.melis.diabetes_api.entry.entity.EntryType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EntryResponse(
    Long id,
	EntryType type,
	BigDecimal value,
	LocalDateTime recordedAt
) {
}
