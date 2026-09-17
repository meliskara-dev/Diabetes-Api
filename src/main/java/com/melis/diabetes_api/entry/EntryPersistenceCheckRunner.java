package com.melis.diabetes_api.entry;

import com.melis.diabetes_api.entry.entity.Entry;
import com.melis.diabetes_api.entry.entity.EntryType;
import com.melis.diabetes_api.entry.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class EntryPersistenceCheckRunner implements CommandLineRunner {

	private final EntryRepository entryRepository;

	@Override
	public void run(String... args) throws Exception {
		Entry entry = new Entry();
		entry.setType(EntryType.FASTING_GLUCOSE);
		entry.setValue(new BigDecimal("120.00"));
		entry.setRecordedAt(Instant.now());

		Entry saved = entryRepository.save(entry);

		Entry found = entryRepository.findById(saved.getId()).orElseThrow();
		log.info("Entry okundu: id={}, type={}, value={}, recordedAt={}, created_at={}, updated_at={}", found.getId(), found.getType(), found.getValue(), found.getRecordedAt(), found.getCreatedAt(), found.getUpdatedAt());
	}
}
