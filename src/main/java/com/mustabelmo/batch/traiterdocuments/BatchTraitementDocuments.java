package com.mustabelmo.batch.traiterdocuments;

import com.mustabelmo.batch.archi.annotations.Batch;
import com.mustabelmo.batch.archi.config.AbstractBatchConfig;
import com.mustabelmo.batch.archi.config.BatchCommonListener;
import com.mustabelmo.batch.traiterdocuments.dto.DocumentDTO;
import com.mustabelmo.batch.traiterdocuments.processor.Processor;
import com.mustabelmo.batch.traiterdocuments.reader.Reader;
import com.mustabelmo.batch.traiterdocuments.writer.Writer;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;


@Batch
public class BatchTraitementDocuments extends AbstractBatchConfig<DocumentDTO, DocumentDTO> {
    @Autowired
    private BatchCommonListener traitementDocuemntsListener;
    
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
    
    @Override
    protected List<BatchCommonListener> listeners() {
        return Collections.singletonList(traitementDocuemntsListener);
    }
}
