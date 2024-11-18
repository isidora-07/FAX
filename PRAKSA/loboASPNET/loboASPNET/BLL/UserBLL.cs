using loboASPNET.BLL.Interfaces;
using loboASPNET.DAL.Interfaces;
using loboASPNET.Models;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.BLL
{
    public class UserBLL : IUserBLL
    {
        private readonly IConfiguration _configuration;
        private readonly IUserDAL _iUserDAL;

        public UserBLL(IConfiguration configuration, IUserDAL userDAL)
        {
            _configuration = configuration;
            _iUserDAL = userDAL;
        }

        public List<dynamic> GerUserRole(string username, string pass)
        {
            return _iUserDAL.GerUserRole(username, pass);
        }

        public UserModel TryLogin(LoginModel login)
        {
            return _iUserDAL.TryLogin(login);
        }
    }
}
