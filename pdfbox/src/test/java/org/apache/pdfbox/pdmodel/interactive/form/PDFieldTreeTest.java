/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Tilman Hausherr
 */
public class PDFieldTreeTest
{

    /**
     * PDFBOX-5044 stack overflow
     *
     * @throws IOException
     */
    @Test
    public void test5044() throws IOException
    {
        String sourceUrl = "https://issues.apache.org/jira/secure/attachment/13016994/PDFBOX-4131-0.pdf";

        InputStream is = new URL(sourceUrl).openStream();
        PDDocument doc = PDDocument.load(is);
        PDDocumentCatalog catalog = doc.getDocumentCatalog();
        PDAcroForm acroForm = catalog.getAcroForm();
        int count = 0;
        for (PDField field : acroForm.getFieldTree())
        {
            ++count;
        }
        Assert.assertEquals(4, count);
        is.close();
        doc.close();
    }
}
