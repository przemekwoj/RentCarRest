package com.przemo.rentcar.repositoriesDB.queries;

public interface BrandQueries
{
    String getAllBrands = "SELECT NEW Brand (b.brandName,b.amountOfCars,b.amountOfAvailableCars,b.brand_id)FROM Brand b";
    String getBrandById = "SELECT NEW Brand (b.brandName,b.amountOfCars,b.amountOfAvailableCars,b.brand_id)FROM Brand b " +
            "WHERE b.brand_id = :id";
}
