/*
 * Copyright (c) 2016 Red Hat, Inc. and/or its affiliates.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.jberet.testapps.simple;

import java.util.concurrent.TimeUnit;
import jakarta.batch.operations.JobOperator;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.batch.runtime.BatchStatus;

import org.jberet.runtime.JobExecutionImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests using {@code arrayItemReader} and {@code mockItemWriter} in jberet-support.
 */
public class SimpleIT {
    private static final String simpleJob = "simplebatchlet.xml";
    private static final JobOperator jobOperator = BatchRuntime.getJobOperator();
    @Test
    public void readArrayWriteToConsole() throws Exception {
        final long jobExecutionId = jobOperator.start(simpleJob, null);
        final JobExecutionImpl jobExecution = (JobExecutionImpl) jobOperator.getJobExecution(jobExecutionId);
        jobExecution.awaitTermination(5, TimeUnit.MINUTES);
        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getBatchStatus());
    }
}
