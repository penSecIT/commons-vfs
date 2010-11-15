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
package org.apache.commons.vfs2.impl;

import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileContentInfo;
import org.apache.commons.vfs2.FileContentInfoFactory;

import java.net.FileNameMap;
import java.net.URLConnection;

/**
 * The FileContentInfoFilenameFactory.<br>
 * Uses the filename extension to determine the content-type.<br>
 * The content-encoding is not resolved.
 *
 * @author <a href="http://commons.apache.org/vfs/team-list.html">Commons VFS team</a>
 * @version $Revision$ $Date$
 */
public class FileContentInfoFilenameFactory implements FileContentInfoFactory
{
    public FileContentInfo create(FileContent fileContent)
    {
        String contentType = null;

        String name = fileContent.getFile().getName().getBaseName();
        if (name != null)
        {
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            contentType = fileNameMap.getContentTypeFor(name);
        }

        return new DefaultFileContentInfo(contentType, null);
    }
}
