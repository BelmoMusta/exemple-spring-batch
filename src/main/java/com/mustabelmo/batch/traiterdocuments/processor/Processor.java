package com.mustabelmo.batch.traiterdocuments.processor;

import com.mustabelmo.batch.traiterdocuments.dto.DocumentDTO;
import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<DocumentDTO, DocumentDTO> {
    @Override
    public DocumentDTO process(DocumentDTO data) {
        DocumentDTO dto = new DocumentDTO();
        dto.setContent(data.getContent().toUpperCase());
        return dto;
    }
}
