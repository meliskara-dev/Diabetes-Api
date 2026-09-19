package com.melis.diabetes_api.entry.service;

import com.melis.diabetes_api.common.exception.EntryNotFoundException;
import com.melis.diabetes_api.entry.dto.CreateEntryRequest;
import com.melis.diabetes_api.entry.dto.EntryResponse;
import com.melis.diabetes_api.entry.dto.UpdateEntryRequest;
import com.melis.diabetes_api.entry.entity.Entry;
import com.melis.diabetes_api.entry.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EntryService {

	private final EntryRepository entryRepository;

	@Transactional
	public EntryResponse create(CreateEntryRequest request) {
		Entry entry = toEntity(request);
		Entry savedEntry = entryRepository.save(entry);
		return toResponse(savedEntry);
	}

	@Transactional(readOnly = true)
	public EntryResponse findById(Long id){
		Entry entry = entryRepository.findById(id)
			.orElseThrow(() -> new EntryNotFoundException(id));
		return toResponse(entry);
	}

	@Transactional(readOnly = true)
	public List<EntryResponse> findAll(){
		List<Entry> entryList = entryRepository.findAll();
		return entryList.stream()
			.map(entry -> toResponse(entry))
			.toList();
	}

	@Transactional
	public EntryResponse update(Long id, UpdateEntryRequest request){
		Entry entry = entryRepository.findById(id)
			.orElseThrow(() -> new EntryNotFoundException(id));
		updateEntity(request, entry);
		Entry updatedEntry = entryRepository.save(entry);
		return toResponse(updatedEntry);
	}

	@Transactional
	public void delete(Long id){
		Entry entry = entryRepository.findById(id)
			.orElseThrow(() -> new EntryNotFoundException(id));
		entryRepository.delete(entry);
	}

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