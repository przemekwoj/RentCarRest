package com.przemo.rentcar.repositoriesDB.queries;

public interface AdministrationQueries {
    String getAdministrationByEmail = "select a from Administration a where a.email = ?1";
}
