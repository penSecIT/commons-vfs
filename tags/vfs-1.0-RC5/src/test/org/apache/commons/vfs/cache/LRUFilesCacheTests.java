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
package org.apache.commons.vfs.cache;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.test.AbstractProviderTestCase;

/**
 * NullFilesCache
 * 
 * @author <a href="mailto:imario@apache.org">Mario Ivankovits</a>
 * @version $Revision$ $Date$
 */
public class LRUFilesCacheTests extends AbstractProviderTestCase
{
    public void testFilesCache() throws Exception
    {
        FileObject scratchFolder = getWriteFolder();

        // releaseable
        FileObject dir1 = scratchFolder.resolveFile("dir1");

        // avoid cache removal
        FileObject dir2 = scratchFolder.resolveFile("dir2");
        dir2.getContent();

        // releaseable
        FileObject dir3 = scratchFolder.resolveFile("dir3");

        // releaseable
        FileObject dir4 = scratchFolder.resolveFile("dir4");

        // releaseable
        FileObject dir5 = scratchFolder.resolveFile("dir5");

        // releaseable
        FileObject dir6 = scratchFolder.resolveFile("dir6");

        // releaseable
        FileObject dir7 = scratchFolder.resolveFile("dir7");

        // releaseable
        FileObject dir8 = scratchFolder.resolveFile("dir8");

        // check if the cache still holds the right instance
        FileObject dir2_2 = scratchFolder.resolveFile("dir2");
        assertTrue(dir2 == dir2_2);

        // check if the cache still holds the right instance
        FileObject dir1_2 = scratchFolder.resolveFile("dir1");
        assertFalse(dir1 == dir1_2);
    }
}
