package edu.nju.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by tj on 2016/5/14.
 */
public class Statistics {
    public static String[] categories = {"api", "django", "gem", "jquery", "web", "irc", "plugin", "database", "android", "git",
            "emacs", "linux", "json", "toolkit", ".net", "os", "xml", "ios", "mvc", "vim", "apache", "maven", "mysql"};
    public static final String[] userCompany = {"Shopify","Google","Github","Twitter","Microsoft","Mozilla","Xamarin","Heroku",
            "Facebook","Red Hat"};
    public static String[] countries = {"Afghanistan","Angola","Albania","United Arab Emirates","Argentina","Armenia",
            "French Southern and Antarctic Lands","Australia","Austria","Azerbaijan", "Burundi","Belgium","Benin","Burkina Faso",
            "Bangladesh","Bulgaria","The Bahamas","Bosnia and Herzegovina","Belarus","Belize","Bermuda","Bolivia","Brazil","Brunei",
            "Bhutan","Botswana","Central African Republic","Canada","Switzerland","Chile","China","Ivory Coast","Cameroon","Democratic Republic of the Congo",
            "Republic of the Congo","Colombia","Costa Rica","Cuba","Northern Cyprus","Cyprus","Czech Republic","Djibouti","Denmark",
            "Dominican Republic","Algeria","Ecuador","Egypt","Eritrea","Spain","Estonia","Ethiopia","Finland","Fiji","Falkland Islands",
            "Gabon","Georgia","Ghana","Guinea","Gambia","Guinea Bissau","Equatorial Guinea","Greece","Greenland",
            "Guatemala","French Guiana","Guyana","Honduras","Croatia","Haiti","Hungary","Indonesia","India","Ireland","Iran","Iraq","Iceland",
            "Israel","Italy","Jamaica","Jordan","Japan","Kazakhstan","Kenya","Kyrgyzstan","Cambodia","South Korea","Kosovo","Kuwait",
            "Laos","Lebanon","Liberia","Libya","Sri Lanka","Lesotho","Lithuania","Luxembourg","Latvia","Morocco","Moldova","Madagascar",
            "Mexico","Macedonia","Mali","Myanmar","Montenegro","Mongolia","Mozambique","Mauritania","Malawi","Malaysia","Namibia",
            "New Caledonia","Niger","Nigeria","Nicaragua","Netherlands","Norway","Nepal","New Zealand","Oman","Pakistan","Panama",
            "Peru","Philippines","Papua New Guinea","Poland","Puerto Rico","North Korea","Portugal","Paraguay","Qatar","Romania","Russia",
            "Rwanda","Western Sahara","Saudi Arabia","Sudan","South Sudan","Senegal","Solomon Islands","Sierra Leone","El Salvador","Somaliland",
            "Somalia","Republic of Serbia","Suriname","Slovakia","Slovenia","Sweden","Swaziland","Syria","Chad","Togo","Thailand","Tajikistan",
            "Turkmenistan","East Timor","Trinidad and Tobago","Tunisia","Turkey","United Republic of Tanzania","Uganda","Ukraine","Uruguay",
            "Uzbekistan","Venezuela","Vietnam","Vanuatu","West Bank","Yemen","South Africa","Zambia","Zimbabwe","United Kingdom","United States of America","France","Germany"
    };
    public static String[] big = {"United Kingdom","United States of America","France","Germany"};
    public static String[][] bigCountryCity = {{"london","uk","england"},{"san francisco","new york","seattle","us","america"},{"paris"},{"berlin"}};
    //germany:berlin,france:paris,uk:london
    public static String[] year = {"2007","2008","2009","2010","2011","2012","2013","2014","2015","2016"};
    public static List<String> tag = Arrays.asList(categories);
    public static List<String> country = Arrays.asList(countries);
    public static List<String> bigCountries = Arrays.asList(big);

}
