package com.example.uoftfinalproject.model;

import java.util.Objects;

import androidx.annotation.NonNull;

public class ImageModel {
    @NonNull
    public String image_drawable;
    String Path;

    public String getImage_drawable() {
        return image_drawable;
    }

    public void setImage_drawable(String image_drawable) {
        this.image_drawable = image_drawable;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageModel that = (ImageModel) o;
        return Objects.equals(image_drawable, that.image_drawable);
    }

    @Override
    public int hashCode() {

        return Objects.hash(image_drawable);
    }
}
