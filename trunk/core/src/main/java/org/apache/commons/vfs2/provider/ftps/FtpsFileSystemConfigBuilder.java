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
package org.apache.commons.vfs2.provider.ftps;

import org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.FileSystemConfigBuilder;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystem;

/**
 * The configuration builder for various FTPS configuration options.
 *
 * @since 2.0
 */
public final class FtpsFileSystemConfigBuilder extends FileSystemConfigBuilder
{
    private static final String _PREFIX = FtpsFileSystemConfigBuilder.class.getName();

    private static final FtpsFileSystemConfigBuilder BUILDER =
        new FtpsFileSystemConfigBuilder();

    private static final String FACTORY_KEY = FTPFileEntryParserFactory.class.getName() + ".KEY";
    private static final String PASSIVE_MODE = _PREFIX + ".PASSIVE";
    private static final String USER_DIR_IS_ROOT = _PREFIX + ".USER_DIR_IS_ROOT";
    private static final String DATA_TIMEOUT = _PREFIX + ".DATA_TIMEOUT";
    private static final String FTPS_TYPE = _PREFIX + ".FTPS_TYPE";
    private static final String SERVER_LANGUAGE_CODE = _PREFIX + ".SERVER_LANGUAGE_CODE";
    private static final String DEFAULT_DATE_FORMAT = _PREFIX + ".DEFAULT_DATE_FORMAT";
    private static final String RECENT_DATE_FORMAT = _PREFIX + ".RECENT_DATE_FORMAT";
    private static final String SERVER_TIME_ZONE_ID = _PREFIX + ".SERVER_TIME_ZONE_ID";
    private static final String SHORT_MONTH_NAMES = _PREFIX + ".SHORT_MONTH_NAMES";
    
    /**
     * FTPS implicit file type.
     */
    public static final String FTPS_TYPE_IMPLICIT = "implicit";

    /**
     * FTPS explicit file type.
     */
    public static final String FTPS_TYPE_EXPLICIT = "explicit";

    // For VFS-412
    // private static final String PROT =
    // FtpsFileSystemConfigBuilder.class.getName() + ".PROT";

    private FtpsFileSystemConfigBuilder()
    {
        // no init.
    }

    /**
     * Gets the singleton builder.
     *
     * @return the singleton builder.
     */
    public static FtpsFileSystemConfigBuilder getInstance()
    {
        return BUILDER;
    }

    /**
     * FTPFileEntryParserFactory which will be used for ftp-entry parsing.
     *
     * @param opts The FileSystemOptions.
     * @param factory instance of your factory
     */
    public void setEntryParserFactory(FileSystemOptions opts, FTPFileEntryParserFactory factory)
    {
        setParam(opts, FTPFileEntryParserFactory.class.getName(), factory);
    }

    /**
     * @param opts The FileSystemOptions
     * @return The FTPFileEntryParserFactory.
     * @see #setEntryParserFactory
     */
    public FTPFileEntryParserFactory getEntryParserFactory(FileSystemOptions opts)
    {
        return (FTPFileEntryParserFactory) getParam(opts, FTPFileEntryParserFactory.class.getName());
    }

    /**
     * set the FQCN of your FileEntryParser used to parse the directory listing from your server.<br />
     * <br />
     * <i>If you do not use the default commons-net FTPFileEntryParserFactory e.g. by using
     * {@link #setEntryParserFactory}
     * this is the "key" parameter passed as argument into your custom factory</i>
     *
     * @param opts The FileSystemOptions.
     * @param key The key.
     */
    public void setEntryParser(FileSystemOptions opts, String key)
    {
        setParam(opts, FACTORY_KEY, key);
    }

    /**
     * @param opts The FileSystemOptions.
     * @return The key.
     * @see #setEntryParser
     */
    public String getEntryParser(FileSystemOptions opts)
    {
        return (String) getParam(opts, FACTORY_KEY);
    }

    @Override
    protected Class<? extends FileSystem> getConfigClass()
    {
        return FtpFileSystem.class;
    }

    /**
     * Enter into passive mode.
     *
     * @param opts The FileSystemOptions.
     * @param passiveMode true if passive mode should be used, false otherwise.
     */
    public void setPassiveMode(FileSystemOptions opts, boolean passiveMode)
    {
        setParam(opts, PASSIVE_MODE, passiveMode ? Boolean.TRUE : Boolean.FALSE);
    }

    /**
     * @param opts The FileSystemOptions.
     * @return true if passive mode is being used.
     * @see #setPassiveMode
     */
    public Boolean getPassiveMode(FileSystemOptions opts)
    {
        return (Boolean) getParam(opts, PASSIVE_MODE);
    }

    /**
     * use user directory as root (do not change to fs root).
     *
     * @param opts The FileSystemOptions.
     * @param userDirIsRoot true if the user directory should be the root.
     */
    public void setUserDirIsRoot(FileSystemOptions opts, boolean userDirIsRoot)
    {
        setParam(opts, USER_DIR_IS_ROOT,
            userDirIsRoot ? Boolean.TRUE : Boolean.FALSE);
    }

    /**
     * @param opts The FileSystemOptions.
     * @return true if the user directory is the root.
     * @see #setUserDirIsRoot
     */
    public Boolean getUserDirIsRoot(FileSystemOptions opts)
    {
        return getBoolean(opts, USER_DIR_IS_ROOT, Boolean.TRUE);
    }

    /**
     * Set FTPS security mode, either "implicit" or "explicit".
     *
     * @param opts The FileSystemOptions.
     * @param ftpsType The file type.
     */
    public void setFtpsType(FileSystemOptions opts, String ftpsType)
    {
        setParam(opts, FTPS_TYPE, ftpsType);
    }

    /**
     * Return the FTPS security mode. Defaults to "explicit" if not defined.
     *
     * @param opts The FileSystemOptions.
     * @return The file type.
     * @see #setFtpsType
     */
    public String getFtpsType(FileSystemOptions opts)
    {
        return getString(opts, FTPS_TYPE, FtpsFileSystemConfigBuilder.FTPS_TYPE_EXPLICIT);
    }

    /**
     * @param opts The FileSystemOptions.
     * @return The timeout value.
     * @see #setDataTimeout
     */
    public Integer getDataTimeout(FileSystemOptions opts)
    {
        return (Integer) getParam(opts, DATA_TIMEOUT);
    }

    /**
     * set the data timeout for the ftp client.<br />
     * If you set the dataTimeout to {@code null} no dataTimeout will be set on the
     * ftp client.
     *
     * @param opts  The FileSystemOptions.
     * @param dataTimeout The timeout value.
     */
    public void setDataTimeout(FileSystemOptions opts, Integer dataTimeout)
    {
        setParam(opts, DATA_TIMEOUT, dataTimeout);
    }

    /**
     * get the language code used by the server. see {@link org.apache.commons.net.ftp.FTPClientConfig}
     * for details and examples.
     * @param opts The FileSystemOptions.
     * @return The language code.
     */
    public String getServerLanguageCode(FileSystemOptions opts)
    {
        return (String) getParam(opts, SERVER_LANGUAGE_CODE);
    }

    /**
     * set the language code used by the server. see {@link org.apache.commons.net.ftp.FTPClientConfig}
     * for details and examples.
     * @param opts The FileSystemOptions.
     * @param serverLanguageCode the language code.
     */
    public void setServerLanguageCode(FileSystemOptions opts,
                                      String serverLanguageCode)
    {
        setParam(opts, SERVER_LANGUAGE_CODE, serverLanguageCode);
    }

    /**
     * get the language code used by the server. see {@link org.apache.commons.net.ftp.FTPClientConfig}
     * for details and examples.
     * @param opts The FileSystemOptions.
     * @return The default date format.
     */
    public String getDefaultDateFormat(FileSystemOptions opts)
    {
        return (String) getParam(opts, DEFAULT_DATE_FORMAT);
    }

    /**
     * set the language code used by the server. see {@link org.apache.commons.net.ftp.FTPClientConfig}
     * for details and examples.
     * @param opts The FileSystemOptions.
     * @param defaultDateFormat The default date format.
     */
    public void setDefaultDateFormat(FileSystemOptions opts,
                                     String defaultDateFormat)
    {
        setParam(opts, DEFAULT_DATE_FORMAT, defaultDateFormat);
    }

    /**
     * see {@link org.apache.commons.net.ftp.FTPClientConfig} for details and examples.
     * @param opts The FileSystemOptions.
     * @return The recent date format.
     */
    public String getRecentDateFormat(FileSystemOptions opts)
    {
        return (String) getParam(opts, RECENT_DATE_FORMAT);
    }

    /**
     * see {@link org.apache.commons.net.ftp.FTPClientConfig} for details and examples.
     * @param opts The FileSystemOptions
     * @param recentDateFormat The recent date format.
     */
    public void setRecentDateFormat(FileSystemOptions opts,
                                    String recentDateFormat)
    {
        setParam(opts, RECENT_DATE_FORMAT, recentDateFormat);
    }

    /**
     * see {@link org.apache.commons.net.ftp.FTPClientConfig} for details and examples.
     * @param opts The FileSystemOptions.
     * @return The server timezone id.
     */
    public String getServerTimeZoneId(FileSystemOptions opts)
    {
        return (String) getParam(opts, SERVER_TIME_ZONE_ID);
    }

    /**
     * see {@link org.apache.commons.net.ftp.FTPClientConfig} for details and examples.
     * @param opts The FileSystemOptions.
     * @param serverTimeZoneId The server's timezone id.
     */
    public void setServerTimeZoneId(FileSystemOptions opts,
                                    String serverTimeZoneId)
    {
        setParam(opts, SERVER_TIME_ZONE_ID, serverTimeZoneId);
    }

    /**
     * see {@link org.apache.commons.net.ftp.FTPClientConfig} for details and examples.
     * @param opts The FileSystemOptions.
     * @return An array of short month names.
     */
    public String[] getShortMonthNames(FileSystemOptions opts)
    {
        return (String[]) getParam(opts, SHORT_MONTH_NAMES);
    }

    /**
     * see {@link org.apache.commons.net.ftp.FTPClientConfig} for details and examples.
     * @param opts The FileSystemOptions.
     * @param shortMonthNames An array of short month names.
     */
    public void setShortMonthNames(FileSystemOptions opts,
                                   String[] shortMonthNames)
    {
        String[] clone = null;
        if (shortMonthNames != null)
        {
            clone = new String[shortMonthNames.length];
            System.arraycopy(shortMonthNames, 0, clone, 0, shortMonthNames.length);
        }

        setParam(opts, SHORT_MONTH_NAMES, clone);
    }


// For VFS-412
//    /**
//     * Gets the data channel protection level (PROT).
//     *
//     * @param opts The FileSystemOptions.
//     * @return The PROT value.
//     * @see org.apache.commons.net.ftp.FTPSClient#execPROT(String)
//     * @since 2.1
//     */
//    public String getDataChannelProtectionLevel(FileSystemOptions opts)
//    {
//        return (String) getParam(opts, PROT);
//    }
//
//    /**
//     * Sets the data channel protection level (PROT).
//     *
//     * @param opts  The FileSystemOptions.
//     * @param prot The PROT value, {@code null} has no effect.
//     * @see org.apache.commons.net.ftp.FTPSClient#execPROT(String)
//     * @since 2.1
//     */
//    public void setDataChannelProtectionLevel(FileSystemOptions opts, String prot)
//    {
//        setParam(opts, PROT, prot);
//    }
}
