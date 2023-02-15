package com.mustabelmo.batch.traiterdocuments;

import com.mustabelmo.batch.archi.annotations.Batch;
import com.mustabelmo.batch.archi.config.AbstractBatchConfig;
import com.mustabelmo.batch.traiterdocuments.dto.DocumentDTO;
import com.mustabelmo.batch.traiterdocuments.processor.Processor;
import com.mustabelmo.batch.traiterdocuments.reader.Reader;
import com.mustabelmo.batch.traiterdocuments.writer.Writer;
import org.springframework.batch.core.configuration.annotation.StepScope;


@Batch
public class BatchTraitementDocuments extends AbstractBatchConfig<DocumentDTO, DocumentDTO> {

    @Override
    public String getName() {
        return "TRAIT_DOCS";
    }
    
    @Override
    protected Writer writer() {
        return new Writer();
    }
    
    @Override
    protected int getChunkSize() {
        return 5;
    }
    
    @Override
    protected Processor processor() {
        return new Processor();
    }

    @Override
    @StepScope
    protected Reader reader() {
        return new Reader();
    }
}
