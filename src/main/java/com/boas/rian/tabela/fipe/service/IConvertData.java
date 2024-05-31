package com.boas.rian.tabela.fipe.service;

import java.util.List;

public interface IConvertData {
    <T> T convertJson(String json, Class<T> tClass);
    <T> List<T> convertJsonList(String json, Class<T> tClass);
}
