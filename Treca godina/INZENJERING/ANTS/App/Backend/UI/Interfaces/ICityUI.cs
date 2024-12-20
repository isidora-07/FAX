﻿using Backend.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Backend.UI.Interfaces
{
    public interface ICityUI
    {
        List<City> getAllCities();
        City getByID(long id);
        City insertCity(City city);
        City getCityFromName(string name);
    }
}
