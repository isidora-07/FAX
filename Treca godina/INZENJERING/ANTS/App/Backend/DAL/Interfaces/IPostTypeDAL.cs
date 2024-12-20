﻿using Backend.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Backend.DAL.Interfaces
{
    public interface IPostTypeDAL
    {
        List<PostType> getAllPostTypes();
        PostType getByID(long id);
    }
}
