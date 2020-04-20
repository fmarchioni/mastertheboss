package com.mastertheboss.rest;

import java.io.File;

public interface Config {
    String UPLOAD_FOLDER = System.getProperty("user.home") + File.separator + "uploads";
}
