/*
 * Copyright 2002, 2003,2004 The Apache Software Foundation.
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
package org.apache.commons.vfs.provider.tar;

import org.apache.commons.vfs.FileName;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.provider.LayeredFileName;
import org.apache.commons.vfs.provider.UriParser;

/**
 * A parser for Tar file names.
 *
 * @author <a href="mailto:adammurdoch@apache.org">Adam Murdoch</a>
 * @version $Revision$ $Date$
 */
public class TarFileName extends LayeredFileName
{
    private static final char[] TAR_URL_RESERVED_CHARS = {'!'};

    public TarFileName(final String scheme,
                       final String tarFileUri,
                       final String path)
    {
        super(scheme, tarFileUri, path);
    }

    /**
     * Builds the root URI for this file name.
     */
    protected void appendRootUri(final StringBuffer buffer)
    {
        buffer.append(getScheme());
        buffer.append(":");
        UriParser.appendEncoded(buffer, getOuterUri(), TAR_URL_RESERVED_CHARS);
        buffer.append("!");
    }

    /**
     * Factory method for creating name instances.
     */
    protected FileName createName(final String path)
    {
        return new TarFileName(getScheme(), getOuterUri(), path);
    }

    /**
     * Parses a Tar URI.
     */
    public static TarFileName parseUri(final String uri)
        throws FileSystemException
    {
        final StringBuffer name = new StringBuffer();

        // Extract the scheme
        final String scheme = UriParser.extractScheme(uri, name);

        // Extract the Tar file URI
        final String tarUri = extractTarName(name);

        // Decode and normalise the path
        UriParser.decode(name, 0, name.length());
        UriParser.normalisePath(name);
        final String path = name.toString();

        return new TarFileName(scheme, tarUri, path);
    }

    /**
     * Pops the root prefix off a URI, which has had the scheme removed.
     */
    private static String extractTarName(final StringBuffer uri)
        throws FileSystemException
    {
        // Looking for <name>!<abspath>
        int maxlen = uri.length();
        int pos = 0;
        for (; pos < maxlen && uri.charAt(pos) != '!'; pos++)
        {
        }

        // Extract the name
        String prefix = uri.substring(0, pos);
        if (pos < maxlen)
        {
            uri.delete(0, pos + 1);
        }
        else
        {
            uri.setLength(0);
        }

        // Decode the name
        return UriParser.decode(prefix);
    }
}
