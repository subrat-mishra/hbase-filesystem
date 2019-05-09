/*
 * Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.hadoop.hbase.oss.contract;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.contract.AbstractFSContract;
import org.apache.hadoop.fs.contract.AbstractContractRenameTest;
import org.apache.hadoop.hbase.oss.EmbeddedS3;
import org.apache.hadoop.hbase.oss.TestUtils;
import org.junit.Assume;
import org.junit.Test;

/**
 * There is an S3A-specific extension of AbstractContractRenameTest
 * that is similarly extended for HBOSS-on-S3A. This class remains to be run in
 * the general case.
 */
public class TestHBOSSContractRename extends AbstractContractRenameTest {

  @Override
  protected Configuration createConfiguration() {
    return super.createConfiguration();
  }

  @Override
  protected AbstractFSContract createContract(Configuration conf) {
    HBOSSContract contract = new HBOSSContract(conf);
    try {
      TestUtils.getFileSystem(conf);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception configuring FS: " + e);
    }
    Assume.assumeFalse(TestUtils.fsIs(TestUtils.S3A, conf));
    return contract;
  }
}
