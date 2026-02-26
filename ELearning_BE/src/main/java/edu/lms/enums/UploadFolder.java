package edu.lms.enums;

public enum UploadFolder {
    COURSE("lms/courses"),
    LESSON("lms/lessons"),
    AVATAR("lms/avatars");

    private final String path;

    UploadFolder(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
