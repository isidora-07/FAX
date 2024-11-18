using loboASPNET.BLL.Interfaces;
using loboASPNET.Models;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;

namespace loboASPNET.Helper
{
    public class JwtHelper
    {
        private static readonly JwtHelper _singleton;
        private IConfiguration configuration;
        private IUserBLL iUserBLL;

        static JwtHelper()
        {
            _singleton = new JwtHelper();
        }

        public static JwtHelper Singleton
        {
            get
            {
                return _singleton;
            }
        }

        public void SetConfig(IConfiguration configuration)
        {
            _singleton.configuration = configuration;
        }

        public void SetUserBLL(IUserBLL userBLL)
        {
            _singleton.iUserBLL = userBLL;
        }


        public UserModel AuthenticateUser(LoginModel login)
        {
            return iUserBLL.TryLogin(login);
        }

        public string BuildToken(UserModel user)
        {
            Claim[] claims = new[]
            {
                new Claim(JwtRegisteredClaimNames.Sub, user.Name),
                new Claim(ClaimTypes.Role, user.Role),
                new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString())
            };

            SymmetricSecurityKey key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(configuration["Jwt:Key"]));

            SigningCredentials credentials = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

            JwtSecurityToken token = new JwtSecurityToken(configuration["Jwt:Issuer"], configuration["Jwt:Issuer"], claims, expires: DateTime.Now.AddHours(10), signingCredentials: credentials);


            return new JwtSecurityTokenHandler().WriteToken(token);


        }



    }
}
