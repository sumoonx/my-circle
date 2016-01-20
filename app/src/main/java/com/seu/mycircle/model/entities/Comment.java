/*
 * Copyright 2016 Team Wheat, CNV-2313
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seu.mycircle.model.entities;

/**
 * Comment for certain commodity
 *
 * @author JeremyXu on 2016/1/12 via jeremy_xm@163.com
 * @version v0.0
 */
public class Comment {
    private static final long EMPTY_COMMODITYID = 0;

    private long parentCommodityId;
    private Comment partner;
    private String data;

    private Comment() {}

    public Comment(long parentCommodityId, Comment partner, String data) {
        this.parentCommodityId = parentCommodityId;
        this.partner = partner;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public long getParentCommodityId() {
        return parentCommodityId;
    }

    public Comment getPartner() {
        return partner;
    }

    public boolean isRootComment() {
        return partner == null;
    }

    public boolean isValidComment() {
        return parentCommodityId != EMPTY_COMMODITYID;
    }
}
