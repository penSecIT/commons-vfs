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
package org.apache.commons.vfs.provider.ftps;

import org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory;
import org.apache.commons.vfs.FileSystem;
import org.apache.commons.vfs.FileSystemConfigBuilder;
import org.apache.commons.vfs.FileSystemOptions;
import org.apache.commons.vfs.provider.ftp.FtpFileSystem;

/**
 * The config builder for various ftp configuration options
 *
 * @author <a href="mailto:imario@apache.org">Mario Ivankovits</a>
 * @version $Revision: 480428 $ $Date: 2006-11-29 07:15:24 +0100 (Mi, 29 Nov 2006) $
 */
public class FtpsFileSystemConfigBuilder extends FileSystemConfigBuilder {
  private final static FtpsFileSystemConfigBuilder builder =
    new FtpsFileSystemConfigBuilder();

  private final static String FACTORY_KEY =
    FTPFileEntryParserFactory.class.getName() + ".KEY";
  private final static String PASSIVE_MODE =
    FtpsFileSystemConfigBuilder.class.getName() + ".PASSIVE";
  private final static String USER_DIR_IS_ROOT =
    FtpsFileSystemConfigBuilder.class.getName() + ".USER_DIR_IS_ROOT";
  private final static String DATA_TIMEOUT =
    FtpsFileSystemConfigBuilder.class.getName() + ".DATA_TIMEOUT";
  private final static String FTPS_TYPE =
    FtpsFileSystemConfigBuilder.class.getName() + ".FTPS_TYPE";

  private final static String SERVER_LANGUAGE_CODE =
    FtpsFileSystemConfigBuilder.class.getName() + ".SERVER_LANGUAGE_CODE";
  private final static String DEFAULT_DATE_FORMAT =
    FtpsFileSystemConfigBuilder.class.getName() + ".DEFAULT_DATE_FORMAT";
  private final static String RECENT_DATE_FORMAT =
    FtpsFileSystemConfigBuilder.class.getName() + ".RECENT_DATE_FORMAT";
  private final static String SERVER_TIME_ZONE_ID =
    FtpsFileSystemConfigBuilder.class.getName() + ".SERVER_TIME_ZONE_ID";
  private final static String SHORT_MONTH_NAMES =
    FtpsFileSystemConfigBuilder.class.getName() + ".SHORT_MONTH_NAMES";

  public static FtpsFileSystemConfigBuilder getInstance() {
    return builder;
  }

  private FtpsFileSystemConfigBuilder() {
  }

  /**
   * FTPFileEntryParserFactory which will be used for ftp-entry parsing
   *
   * @param opts
   * @param factory instance of your factory
   */
  public void setEntryParserFactory(FileSystemOptions opts,
                                    FTPFileEntryParserFactory factory) {
    setParam(opts, FTPFileEntryParserFactory.class.getName(), factory);
  }

  /**
   * @param opts
   * @see #setEntryParserFactory
   */
  public FTPFileEntryParserFactory getEntryParserFactory(FileSystemOptions opts) {
    return (FTPFileEntryParserFactory)getParam(opts,
                                               FTPFileEntryParserFactory.class.getName());
  }

  /**
   * set the FQCN of your FileEntryParser used to parse the directory listing from your server.<br />
   * <br />
   * <i>If you do not use the default commons-net FTPFileEntryParserFactory e.g. by using {@link #setEntryParserFactory}
   * this is the "key" parameter passed as argument into your custom factory</i>
   *
   * @param opts
   * @param key
   */
  public void setEntryParser(FileSystemOptions opts, String key) {
    setParam(opts, FACTORY_KEY, key);
  }

  /**
   * @param opts
   * @see #setEntryParser
   */
  public String getEntryParser(FileSystemOptions opts) {
    return (String)getParam(opts, FACTORY_KEY);
  }

  @Override
  protected Class<? extends FileSystem> getConfigClass() {
    return FtpFileSystem.class;
  }

  /**
   * enter into passive mode
   *
   * @param opts
   * @param passiveMode
   */
  public void setPassiveMode(FileSystemOptions opts, boolean passiveMode) {
    setParam(opts, PASSIVE_MODE, passiveMode ? Boolean.TRUE : Boolean.FALSE);
  }

  /**
   * @param opts
   * @see #setPassiveMode
   */
  public Boolean getPassiveMode(FileSystemOptions opts) {
    return (Boolean)getParam(opts, PASSIVE_MODE);
  }

  /**
   * use user directory as root (do not change to fs root)
   *
   * @param opts
   * @param userDirIsRoot
   */
  public void setUserDirIsRoot(FileSystemOptions opts, boolean userDirIsRoot) {
    setParam(opts, USER_DIR_IS_ROOT,
             userDirIsRoot ? Boolean.TRUE : Boolean.FALSE);
  }

  /**
   * @param opts
   * @see #setUserDirIsRoot
   */
  public Boolean getUserDirIsRoot(FileSystemOptions opts) {
    return getBoolean(opts, USER_DIR_IS_ROOT, Boolean.TRUE);
  }

  /**
   * Set FTPS security mode, either "implicit" or "explicit"
   *
   * @param opts
   * @param ftpsType
   */
  public void setFtpsType(FileSystemOptions opts, String ftpsType) {
    setParam(opts, FTPS_TYPE, ftpsType);
  }

  /**
   * Return the FTPS security mode. Defaults to "explicit" if not defined.
   * @param opts
   * @see #setFtpsType
   */
  public String getFtpsType(FileSystemOptions opts) {
    return getString(opts, FTPS_TYPE, "explicit");
  }

  /**
   * @param opts
   * @see #setDataTimeout
   */
  public Integer getDataTimeout(FileSystemOptions opts) {
    return (Integer)getParam(opts, DATA_TIMEOUT);
  }

  /**
   * set the data timeout for the ftp client.<br />
   * If you set the dataTimeout to <code>null</code> no dataTimeout will be set on the
   * ftp client.
   *
   * @param opts
   * @param dataTimeout
   */
  public void setDataTimeout(FileSystemOptions opts, Integer dataTimeout) {
    setParam(opts, DATA_TIMEOUT, dataTimeout);
  }

  /**
   * get the language code used by the server. see {@link org.apache.commons.net.ftp.FTPClientConfig}
   * for details and examples.
   */
  public String getServerLanguageCode(FileSystemOptions opts) {
    return (String)getParam(opts, SERVER_LANGUAGE_CODE);
  }

  /**
   * set the language code used by the server. see {@link org.apache.commons.net.ftp.FTPClientConfig}
   * for details and examples.
   */
  public void setServerLanguageCode(FileSystemOptions opts,
                                    String serverLanguageCode) {
    setParam(opts, SERVER_LANGUAGE_CODE, serverLanguageCode);
  }

  /**
   * get the language code used by the server. see {@link org.apache.commons.net.ftp.FTPClientConfig}
   * for details and examples.
   */
  public String getDefaultDateFormat(FileSystemOptions opts) {
    return (String)getParam(opts, DEFAULT_DATE_FORMAT);
  }

  /**
   * set the language code used by the server. see {@link org.apache.commons.net.ftp.FTPClientConfig}
   * for details and examples.
   */
  public void setDefaultDateFormat(FileSystemOptions opts,
                                   String defaultDateFormat) {
    setParam(opts, DEFAULT_DATE_FORMAT, defaultDateFormat);
  }

  /**
   * see {@link org.apache.commons.net.ftp.FTPClientConfig} for details and examples.
   */
  public String getRecentDateFormat(FileSystemOptions opts) {
    return (String)getParam(opts, RECENT_DATE_FORMAT);
  }

  /**
   * see {@link org.apache.commons.net.ftp.FTPClientConfig} for details and examples.
   */
  public void setRecentDateFormat(FileSystemOptions opts,
                                  String recentDateFormat) {
    setParam(opts, RECENT_DATE_FORMAT, recentDateFormat);
  }

  /**
   * see {@link org.apache.commons.net.ftp.FTPClientConfig} for details and examples.
   */
  public String getServerTimeZoneId(FileSystemOptions opts) {
    return (String)getParam(opts, SERVER_TIME_ZONE_ID);
  }

  /**
   * see {@link org.apache.commons.net.ftp.FTPClientConfig} for details and examples.
   */
  public void setServerTimeZoneId(FileSystemOptions opts,
                                  String serverTimeZoneId) {
    setParam(opts, SERVER_TIME_ZONE_ID, serverTimeZoneId);
  }

  /**
   * see {@link org.apache.commons.net.ftp.FTPClientConfig} for details and examples.
   */
  public String[] getShortMonthNames(FileSystemOptions opts) {
    return (String[])getParam(opts, SHORT_MONTH_NAMES);
  }

  /**
   * see {@link org.apache.commons.net.ftp.FTPClientConfig} for details and examples.
   */
  public void setShortMonthNames(FileSystemOptions opts,
                                 String[] shortMonthNames) {
    String[] clone = null;
    if (shortMonthNames != null) {
      clone = new String[shortMonthNames.length];
      System.arraycopy(shortMonthNames, 0, clone, 0, shortMonthNames.length);
    }

    setParam(opts, SHORT_MONTH_NAMES, clone);
  }
}
