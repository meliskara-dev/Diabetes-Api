package com.melis.diabetes_api.entry.repository;

import com.melis.diabetes_api.entry.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
