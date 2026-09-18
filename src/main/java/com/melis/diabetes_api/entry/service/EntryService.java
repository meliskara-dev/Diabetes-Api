package com.melis.diabetes_api.entry.service;

import com.melis.diabetes_api.entry.dto.CreateEntryRequest;
import com.melis.diabetes_api.entry.dto.EntryResponse;
import com.melis.diabetes_api.entry.dto.UpdateEntryRequest;
import com.melis.diabetes_api.entry.entity.Entry;
import com.melis.diabetes_api.entry.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EntryService {

	private final EntryRepository entryRepository;

	private Entry toEntity(CreateEntryRequest request){
		Entry entry = new Entry();
		entry.setType(request.type());
		entry.setValue(request.value());
		entry.setRecordedAt(request.recordedAt());
		return entry;
	}

	private EntryResponse toResponse(Entry entry){
		return new EntryResponse(
			entry.getId(),
			entry.getType(),
			entry.getValue(),
			entry.getRecordedAt()
		);
	}

	private void updateEntity(UpdateEntryRequest request, Entry entry){
		entry.setType(request.type());
		entry.setValue(request.value());
		entry.setRecordedAt(request.recordedAt());
	}
}