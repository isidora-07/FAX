using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.JsonWebTokens;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;
using WebApplication1.Models;
using JwtRegisteredClaimNames = Microsoft.IdentityModel.JsonWebTokens.JwtRegisteredClaimNames;

namespace WebApplication1.Helper
{
    public class JwtHelper
    {
        private static readonly JwtHelper singleton;
        private  IConfiguration _configuration;
        static JwtHelper()
        {
            singleton = new JwtHelper();
        }

        public static JwtHelper Singleton
        {
            get { return singleton; }
        }

        public void SetConfig(IConfiguration configuration)
        {
            singleton._configuration = configuration;
        }

        public UserModel AuthenticateUser(LoginModel loginUser)
        {
            UserModel user = null;

            if(loginUser.Username == "user" && loginUser.Password == "password")
            {
                user = new UserModel { Name = "Authenticated user", Email = "user@user.com", BirthDate = DateTime.Today.AddYears(-20) };
            }

            return user;
        }

        public string BuildToken(UserModel user)
        {
            Claim[] claims = new[]
            {
                new Claim(JwtRegisteredClaimNames.Sub, user.Name),
                new Claim(JwtRegisteredClaimNames.Email, user.Email),
                new Claim(JwtRegisteredClaimNames.Birthdate, user.BirthDate.ToString("yyyy-MM-dd")),
                new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString())
            };

            SymmetricSecurityKey key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["Jwt:Key"]));
            SigningCredentials credentials = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

            JwtSecurityToken token = new JwtSecurityToken(_configuration["Jwt:Issuer"], _configuration["Jwt:Issuer"], claims,
                expires: DateTime.Now.AddMinutes(60), signingCredentials: credentials);

            return new JwtSecurityTokenHandler().WriteToken(token);
        }

    }
}
