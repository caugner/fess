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
package org.codelibs.fess.app.web.admin.fileconfig;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.codelibs.fess.Constants;
import org.codelibs.fess.annotation.UriType;
import org.codelibs.fess.app.web.CrudMode;
import org.codelibs.fess.util.ComponentUtil;
import org.lastaflute.web.validation.Required;

/**
 * @author codelibs
 * @author Keiichi Watanabe
 */
public class CreateForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public String[] roleTypeIds;

    public String[] labelTypeIds;

    @Digits(integer = 10, fraction = 0)
    public Integer crudMode;

    @Required
    @Size(max = 200)
    public String name;

    @Required
    @UriType(protocols = "file:,smb:")
    @Size(max = 4000)
    public String paths;

    @Size(max = 4000)
    public String includedPaths;

    @Size(max = 4000)
    public String excludedPaths;

    @Size(max = 4000)
    public String includedDocPaths;

    @Size(max = 4000)
    public String excludedDocPaths;

    @Size(max = 4000)
    public String configParameter;

    @Min(value = 0)
    @Max(value = 2147483647)
    @Digits(integer = 10, fraction = 0)
    public Integer depth;

    @Min(value = 0)
    @Max(value = 9223372036854775807l)
    @Digits(integer = 19, fraction = 0)
    public Long maxAccessCount;

    @Required
    @Min(value = 0)
    @Max(value = 2147483647)
    @Digits(integer = 10, fraction = 0)
    public Integer numOfThread;

    @Required
    @Min(value = 0)
    @Max(value = 2147483647)
    @Digits(integer = 10, fraction = 0)
    public Integer intervalTime;

    @Required
    @Min(value = 0)
    @Max(value = 2147483647)
    @Digits(integer = 10, fraction = 0)
    public Integer boost;

    @Required
    @Size(max = 5)
    public String available;

    @Required
    @Min(value = 0)
    @Max(value = 2147483647)
    @Digits(integer = 10, fraction = 0)
    public Integer sortOrder;

    @Required
    @Size(max = 1000)
    public String createdBy;

    @Required
    @Digits(integer = 19, fraction = 0)
    public Long createdTime;

    public void initialize() {
        crudMode = CrudMode.CREATE;
        boost = 1;
        numOfThread = Constants.DEFAULT_NUM_OF_THREAD_FOR_FS;
        intervalTime = Constants.DEFAULT_INTERVAL_TIME_FOR_FS;
        sortOrder = 0;
        createdBy = ComponentUtil.getSystemHelper().getUsername();
        createdTime = ComponentUtil.getSystemHelper().getCurrentTimeAsLong();
    }
}
