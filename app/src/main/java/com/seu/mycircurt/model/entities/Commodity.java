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

package com.seu.mycircurt.model.entities;

/**
 * Basic information for one commodity.
 * If you want to details about a commodity, see {@link CommodityDetail}
 *
 * @author JeremyXu on 2016/1/12 via jeremy_xm@163.com
 * @version v0.0
 */
public class Commodity {
    private long id;
    private int imageResource;
    private String resourceUri;
    private String name;
    private String school;
    private int popularity;
    private int price;

    private Commodity() {}

    public Commodity(long id, int imageResource, String name, String school, int popularity, int price) {
        this.id = id;
        this.imageResource = imageResource;
        this.name = name;
        this.school = school;
        this.popularity = popularity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getPrice() {
        return price;
    }
}
