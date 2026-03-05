package edu.lms.constants;

public class ApiPaths {

    // AUTH
    public static final String AUTH = BaseURI.API_V1 + "/auth";

    public static final String CATEGORIES = BaseURI.API_V1 + "/categories";
    public static final String COURSES = BaseURI.API_V1 + "/courses";
    // ===== CURRENT USER =====
    public static final String ME = BaseURI.API_V1 + "/me";
    public static final String MY_COURSES = ME + "/courses";

    // ===== MANAGEMENT =====
    public static final String MANAGEMENT = BaseURI.API_V1 + "/management";
    public static final String MANAGEMENT_COURSES = MANAGEMENT + "/courses";

    // ===== ADMIN =====
    public static final String ADMIN = BaseURI.API_V1 + "/admin";
    public static final String ADMIN_COURSES = ADMIN + "/courses";

    // ===== SUPPORT =====
    public static final String SUPPORT = BaseURI.API_V1 + "/support";
    public static final String SUPPORT_COURSES = SUPPORT + "/courses";

    // ===== SUB-RESOURCE =====
    public static final String LESSONS = "/lessons";
    public static final String MODULES = "/modules";


}
