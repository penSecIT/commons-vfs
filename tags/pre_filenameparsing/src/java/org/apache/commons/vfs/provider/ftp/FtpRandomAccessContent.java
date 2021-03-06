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
package org.apache.commons.vfs.provider.ftp;

import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.provider.AbstractRandomAccessContent;
import org.apache.commons.vfs.util.MonitorInputStream;
import org.apache.commons.vfs.util.RandomAccessMode;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

class FtpRandomAccessContent extends AbstractRandomAccessContent
{
    private final FtpFileObject fileObject;

    protected long filePointer = 0;
    private DataInputStream dis = null;
    private FtpFileObject.FtpInputStream mis = null;

    FtpRandomAccessContent(final FtpFileObject fileObject, RandomAccessMode mode)
    {
        super(mode);

        this.fileObject = fileObject;
        // fileSystem = (FtpFileSystem) this.fileObject.getFileSystem();
    }

    public long getFilePointer() throws IOException
    {
        return filePointer;
    }

    public void seek(long pos) throws IOException
    {
        if (pos == filePointer)
        {
            // no change
            return;
        }

        if (pos < 0)
        {
            throw new FileSystemException("vfs.provider/random-access-invalid-position.error",
                new Object[]
                {
                    new Long(pos)
                });
        }
        if (dis != null)
        {
            close();
        }

        filePointer = pos;
    }

    private void createStream() throws IOException
    {
        if (dis != null)
        {
            return;
        }

        // FtpClient client = fileSystem.getClient();
        mis = fileObject.getInputStream(filePointer);
        dis = new DataInputStream(new FilterInputStream(mis)
        {
            public int read() throws IOException
            {
                int ret = super.read();
                if (ret > -1)
                {
                    filePointer++;
                }
                return ret;
            }

            public int read(byte b[]) throws IOException
            {
                int ret = super.read(b);
                if (ret > -1)
                {
                    filePointer+=ret;
                }
                return ret;
            }

            public int read(byte b[], int off, int len) throws IOException
            {
                int ret = super.read(b, off, len);
                if (ret > -1)
                {
                    filePointer+=ret;
                }
                return ret;
            }

            public void close() throws IOException
            {
                FtpRandomAccessContent.this.close();
            }
        });
    }


    public void close() throws IOException
    {
        if (dis != null)
        {
            mis.abort();

            // this is to avoid recursive close
            DataInputStream oldDis = dis;
            dis = null;
            oldDis.close();
            mis = null;
        }
    }

    public long length() throws IOException
    {
        return fileObject.getContent().getSize();
    }

    public byte readByte() throws IOException
    {
        createStream();
        byte data = dis.readByte();
        return data;
    }

    public char readChar() throws IOException
    {
        createStream();
        char data = dis.readChar();
        return data;
    }

    public double readDouble() throws IOException
    {
        createStream();
        double data = dis.readDouble();
        return data;
    }

    public float readFloat() throws IOException
    {
        createStream();
        float data = dis.readFloat();
        return data;
    }

    public int readInt() throws IOException
    {
        createStream();
        int data = dis.readInt();
        return data;
    }

    public int readUnsignedByte() throws IOException
    {
        createStream();
        int data = dis.readUnsignedByte();
        return data;
    }

    public int readUnsignedShort() throws IOException
    {
        createStream();
        int data = dis.readUnsignedShort();
        return data;
    }

    public long readLong() throws IOException
    {
        createStream();
        long data = dis.readLong();
        return data;
    }

    public short readShort() throws IOException
    {
        createStream();
        short data = dis.readShort();
        return data;
    }

    public boolean readBoolean() throws IOException
    {
        createStream();
        boolean data = dis.readBoolean();
        return data;
    }

    public int skipBytes(int n) throws IOException
    {
        createStream();
        int data = dis.skipBytes(n);
        return data;
    }

    public void readFully(byte b[]) throws IOException
    {
        createStream();
        dis.readFully(b);
    }

    public void readFully(byte b[], int off, int len) throws IOException
    {
        createStream();
        dis.readFully(b, off, len);
    }

    public String readUTF() throws IOException
    {
        createStream();
        String data = dis.readUTF();
        return data;
    }

    public InputStream getInputStream() throws IOException
    {
        createStream();
        return dis;
    }
}