/*
 * Copyright 2002-2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.vfs.provider;

import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileName;

/**
 * A file name for layered files.
 *
 * @author <a href="mailto:adammurdoch@apache.org">Adam Murdoch</a>
 * @version $Revision$ $Date$
 */
public class LayeredFileName extends AbstractFileName
{
    private final FileName outerUri;

    public LayeredFileName(final String scheme,
                           final FileName outerUri,
                           final String path)
    {
        super(scheme, path);
        this.outerUri = outerUri;
    }

    /**
     * Returns the URI of the outer file.
     */
    public FileName getOuterName()
    {
        return outerUri;
    }

    public FileName createName(String path)
    {
        return new LayeredFileName(getScheme(), getOuterName(), path);
    }

    protected void appendRootUri(StringBuffer buffer)
    {
        buffer.append(getScheme());
        buffer.append(":");
        buffer.append(getOuterName().getURI());
        buffer.append("!");
    }
}
