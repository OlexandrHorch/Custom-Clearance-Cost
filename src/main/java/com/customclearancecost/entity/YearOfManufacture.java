package com.customclearancecost.entity;

// TODO: Change data at the beginning of the year!!!
public enum YearOfManufacture {
    year_2019("2019"),
    year_2018("2018"),
    year_2017("2017"),
    year_2016("2016"),
    year_2015("2015"),
    year_2014("2014"),
    year_2013("2013"),
    year_2012("2012"),
    year_2011("2011"),
    year_2010("2010"),
    year_2009("2009"),
    year_2008("2008"),
    year_2007("2007"),
    year_2006("2006"),
    year_2005("2005"),
    year_2004("2004"),
    year_2003_and_before("2003 і раніше");

    YearOfManufacture(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}