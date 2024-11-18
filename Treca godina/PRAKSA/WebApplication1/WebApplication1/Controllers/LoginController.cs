using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Routing;
using Microsoft.Extensions.Configuration;
using WebApplication1.Helper;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class LoginController : ControllerBase
    {
        private readonly IConfiguration _configuration;

        public LoginController(IConfiguration configuration)
        {
            _configuration = configuration;
            JwtHelper.Singleton.SetConfig(configuration);
        }

        [AllowAnonymous]
        [HttpPost]
        [Route("createToken")]
        public IActionResult CreateToken([FromBody] LoginModel loginModel)
        {
            IActionResult response = Unauthorized();

            UserModel user = JwtHelper.Singleton.AuthenticateUser(loginModel);

            if(user != null)
            {
                var token = JwtHelper.Singleton.BuildToken(user);
                response = Ok(new { token = token });
            }

            return response;
        }

    }
}
