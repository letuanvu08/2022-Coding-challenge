package com.example.demo.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Objects;

public class GsonUtils {

  private final Gson gson;
  private final Gson gsonSnakeCase;
  private static GsonUtils instance = null;

  private GsonUtils() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    gson = gsonBuilder.disableHtmlEscaping().create();
    gsonSnakeCase = gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
  }

  public static String toJsonString(Object obj) {
    return getInstance().gson.toJson(obj);
  }

  public static <T> T fromJsonString(String sJson, Class<T> t) {
    return getInstance().gson.fromJson(sJson, t);
  }

  public static <T> T json2Collection(String sJson, Type t) {
    return getInstance().gson.fromJson(sJson, t);
  }

  public static <T> T fromJsonSnakeCase(String json, Type typeOfT) {
    return getInstance().gsonSnakeCase.fromJson(json, typeOfT);
  }

  private static GsonUtils getInstance() {
    if (Objects.isNull(instance)) {
      instance = new GsonUtils();
    }
    return instance;
  }
}
