using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using loboASPNET.BLL.Interfaces;
using loboASPNET.Helper;
using loboASPNET.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;

namespace loboASPNET.Controllers
{
    [Authorize]
    [Route("api/login")]
    [ApiController]
    public class LoginController : ControllerBase
    {
        private readonly IConfiguration configuration;
        private readonly IUserBLL iUserBLL;

        public LoginController(IConfiguration configuration, IUserBLL iUserBLL)
        {
            this.configuration = configuration;
            this.iUserBLL = iUserBLL;

            JwtHelper.Singleton.SetConfig(configuration);
            JwtHelper.Singleton.SetUserBLL(iUserBLL);
        }

        [AllowAnonymous]
        [HttpPost]
        public IActionResult TryLogin([FromBody] LoginModel login)
        {
            IActionResult response = Unauthorized();

            //UserModel user = JwtHelper.Singleton.AuthenticateUser(login);
            UserModel user = iUserBLL.TryLogin(login);

            if (user != null)
            {
                var token = JwtHelper.Singleton.BuildToken(user);

                response = Ok(new { token });
            }

            return response;
        }
    }
}
