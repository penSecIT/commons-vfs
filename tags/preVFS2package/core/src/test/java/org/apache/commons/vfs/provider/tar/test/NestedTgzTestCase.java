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
package org.apache.commons.vfs.provider.tar.test;

import junit.framework.Test;
import org.apache.commons.AbstractVfsTestCase;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.impl.DefaultFileSystemManager;
import org.apache.commons.vfs.provider.tar.TarFileProvider;
import org.apache.commons.vfs.test.AbstractProviderTestConfig;
import org.apache.commons.vfs.test.ProviderTestConfig;
import org.apache.commons.vfs.test.ProviderTestSuite;

/**
 * Tests for the Tar file system, using a tar file nested inside another tar file.
 */
public class NestedTgzTestCase
    extends AbstractProviderTestConfig
    implements ProviderTestConfig
{
    /**
     * Creates the test suite for nested tar files.
     */
    public static Test suite() throws Exception
    {
        return new ProviderTestSuite(new NestedTgzTestCase(), true);
    }

    /**
     * Prepares the file system manager.
     */
    @Override
    public void prepare(final DefaultFileSystemManager manager)
        throws Exception
    {
        manager.addProvider("tgz", new TarFileProvider());
        manager.addExtensionMap("tgz", "tgz");
        manager.addProvider("tar", new TarFileProvider());
    }

    /**
     * Returns the base folder for tests.
     */
    @Override
    public FileObject getBaseTestFolder(final FileSystemManager manager) throws Exception
    {
        // Locate the base Tar file
        final String tarFilePath = AbstractVfsTestCase.getTestResource("nested.tgz").getAbsolutePath();
        String uri = "tgz:file:" + tarFilePath + "!/test.tgz";
        final FileObject tarFile = manager.resolveFile(uri);

        // Now build the nested file system
        final FileObject nestedFS = manager.createFileSystem(tarFile);
        return nestedFS.resolveFile("/");
    }
}