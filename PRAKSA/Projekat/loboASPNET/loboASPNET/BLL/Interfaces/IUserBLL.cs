using loboASPNET.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.BLL.Interfaces
{
    public interface IUserBLL
    {
        public UserModel TryLogin(LoginModel login);
    }
}
