﻿using Backend.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Backend.UI.Interfaces
{
    public interface IReportTypeUI
    {
        List<ReportType> getAllReportTypes();
        ReportType getByID(long id);
    }
}
