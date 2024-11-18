using loboASPNET.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.DAL.Interfaces
{
    public interface IUserDAL
    {
        public UserModel TryLogin(LoginModel login);
        public List<dynamic> GerUserRole(string username, string pass);
    }
}
