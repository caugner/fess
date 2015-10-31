/*
 * Copyright 2012-2015 CodeLibs Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.codelibs.fess.app.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codelibs.core.beans.util.BeanUtil;
import org.codelibs.fess.Constants;
import org.codelibs.fess.app.pager.FileAuthenticationPager;
import org.codelibs.fess.es.config.cbean.FileAuthenticationCB;
import org.codelibs.fess.es.config.exbhv.FileAuthenticationBhv;
import org.codelibs.fess.es.config.exentity.FileAuthentication;
import org.dbflute.cbean.result.PagingResultBean;
import org.dbflute.optional.OptionalEntity;

public class FileAuthenticationService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Resource
    protected FileAuthenticationBhv fileAuthenticationBhv;

    public FileAuthenticationService() {
        super();
    }

    public List<FileAuthentication> getFileAuthenticationList(final FileAuthenticationPager fileAuthenticationPager) {

        final PagingResultBean<FileAuthentication> fileAuthenticationList = fileAuthenticationBhv.selectPage(cb -> {
            cb.paging(fileAuthenticationPager.getPageSize(), fileAuthenticationPager.getCurrentPageNumber());
            setupListCondition(cb, fileAuthenticationPager);
        });

        // update pager
        BeanUtil.copyBeanToBean(fileAuthenticationList, fileAuthenticationPager, option -> option.include(Constants.PAGER_CONVERSION_RULE));
        fileAuthenticationPager.setPageNumberList(fileAuthenticationList.pageRange(op -> {
            op.rangeSize(5);
        }).createPageNumberList());

        return fileAuthenticationList;
    }

    public OptionalEntity<FileAuthentication> getFileAuthentication(final String id) {
        return fileAuthenticationBhv.selectByPK(id);
    }

    public void store(final FileAuthentication fileAuthentication) {
        setupStoreCondition(fileAuthentication);

        fileAuthenticationBhv.insertOrUpdate(fileAuthentication, op -> {
            op.setRefresh(true);
        });

    }

    public void delete(final FileAuthentication fileAuthentication) {
        setupDeleteCondition(fileAuthentication);

        fileAuthenticationBhv.delete(fileAuthentication, op -> {
            op.setRefresh(true);
        });

    }

    protected void setupListCondition(final FileAuthenticationCB cb, final FileAuthenticationPager fileAuthenticationPager) {
        if (fileAuthenticationPager.id != null) {
            cb.query().docMeta().setId_Equal(fileAuthenticationPager.id);
        }
        // TODO Long, Integer, String supported only.

        // setup condition
        cb.query().addOrderBy_Hostname_Asc();

        // search

    }

    protected void setupEntityCondition(final FileAuthenticationCB cb, final Map<String, String> keys) {

        // setup condition

    }

    protected void setupStoreCondition(final FileAuthentication fileAuthentication) {

        // setup condition

    }

    protected void setupDeleteCondition(final FileAuthentication fileAuthentication) {

        // setup condition

    }

    public List<FileAuthentication> getFileAuthenticationList(final String fileConfigId) {
        return fileAuthenticationBhv.selectList(cb -> {
            cb.query().setFileConfigId_Equal(fileConfigId);
        });
    }
}
