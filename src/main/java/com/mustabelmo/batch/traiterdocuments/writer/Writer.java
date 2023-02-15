package com.mustabelmo.batch.traiterdocuments.writer;

import com.mustabelmo.batch.traiterdocuments.dto.DocumentDTO;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class Writer implements ItemWriter<DocumentDTO> {
    @Override
    public void write(Chunk<? extends DocumentDTO> messages) throws Exception {
        for (DocumentDTO msg : messages) {
            System.out.println("Writing the data " + msg.getContent());
        }
    }
}
