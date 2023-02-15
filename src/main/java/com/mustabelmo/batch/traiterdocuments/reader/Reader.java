package com.mustabelmo.batch.traiterdocuments.reader;

import com.mustabelmo.batch.traiterdocuments.dto.DocumentDTO;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<DocumentDTO>, StepExecutionListener {

    private String[] messages = {"test 1",
            "test 2",
            "test 3"};

    private int count = 0;

    @Override
    public DocumentDTO read() throws Exception, UnexpectedInputException,
            ParseException, NonTransientResourceException {

        if (count < messages.length) {
            DocumentDTO acteDTO = new DocumentDTO();
            acteDTO.setContent(messages[count++]);
            return acteDTO;
        } else {
            count = 0;
        }
        return null;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }
}
