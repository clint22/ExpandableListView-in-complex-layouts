package org.bestdoc.jitha.tvseriesnandmovies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jitha on 29/7/17.
 */

class MovieDetails {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {

        return name;
    }

    public MovieDetails(String name, int id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }
}
