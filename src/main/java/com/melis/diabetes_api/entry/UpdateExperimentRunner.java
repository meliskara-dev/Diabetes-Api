package com.melis.diabetes_api.entry;

import com.melis.diabetes_api.entry.dto.CreateEntryRequest;
import com.melis.diabetes_api.entry.dto.EntryResponse;
import com.melis.diabetes_api.entry.dto.UpdateEntryRequest;
import com.melis.diabetes_api.entry.entity.EntryType;
import com.melis.diabetes_api.entry.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UpdateExperimentRunner implements CommandLineRunner{

	private final EntryService entryService;

	@Override
	public void run(String... args) throws Exception {

		EntryResponse created = entryService.create(new CreateEntryRequest(EntryType.FASTING_GLUCOSE, new BigDecimal("120.0"), LocalDateTime.now()));
		System.out.println("Finished create, id: " + created.id());

		System.out.println("update başlıyor");
		entryService.update(created.id(), new UpdateEntryRequest(EntryType.POSTPRANDIAL_GLUCOSE, new BigDecimal("150.00"), LocalDateTime.now()));
		System.out.println("update bitti");
	}
}
