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
package org.apache.commons.vfs2.provider.bzip2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.provider.AbstractFileName;
import org.apache.commons.vfs2.provider.compressed.CompressedFileFileObject;
import org.apache.commons.vfs2.provider.compressed.CompressedFileFileSystem;

/**
 * the bzip2 file.
 *
 * @author <a href="http://commons.apache.org/vfs/team-list.html">Commons VFS team</a>
 */
public class Bzip2FileObject extends CompressedFileFileObject
{
    protected Bzip2FileObject(AbstractFileName name, FileObject container, CompressedFileFileSystem fs)
    {
        super(name, container, fs);
    }

    @Override
    protected InputStream doGetInputStream() throws Exception
    {
        // check file
        InputStream is = getContainer().getContent().getInputStream();
        return wrapInputStream(getName().getURI(), is);
    }

    public static InputStream wrapInputStream(final String name, final InputStream is) throws IOException
    {
        return new BZip2CompressorInputStream(is);
    }

    @Override
    protected OutputStream doGetOutputStream(boolean bAppend) throws Exception
    {
        OutputStream os = getContainer().getContent().getOutputStream(false);
        return new BZip2CompressorOutputStream(os);
    }
}
