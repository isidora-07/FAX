using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;
using loboASPNET.BLL;
using loboASPNET.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace loboASPNET.Controllers
{
    [Authorize]
    [Route("api/bend")]
    [ApiController]
    public class BendsController : ControllerBase
    {
        private readonly IBendBLL _bendBLL;
        private readonly ILogger<BendsController> _logger;

        public BendsController(IBendBLL bendBLL, ILogger<BendsController> logger)
        {
            _bendBLL = bendBLL;
            _logger = logger;
        }

        [Authorize(Roles = "user, admin")]
        [HttpGet]
        public dynamic Get()    
        {
            _logger.LogInformation("method Get");
            return _bendBLL.Select();
        }

        [Authorize(Roles = "user, admin")]
        [HttpGet("getBend")]
        public List<dynamic> GetBend(int id)
        {
            return _bendBLL.GetBendByID(id);
        }

        [Authorize(Roles = "admin")]
        [HttpPost]
        public string AddBend(Bend aBend)
        {
            bool isNull = aBend.GetType().GetProperties().All(x => x.GetValue(aBend) == null);

            if (isNull)
            {
                return "Doslo je do greske";
            }
            else
            {
                if (_bendBLL.Insert(aBend) == false)
                {
                    return "Neuspesno dodavanje.";
                }
                return "Uspesno dodavanje.";
            }
        }


        [Authorize(Roles = "admin")]
        [HttpDelete("deleteBend")]
        public string DeleteBend(int id)
        {
            if (_bendBLL.DeleteBendByID(id) == false)
            {
                return "Neuspesno brisanje.";
            }
            return "Uspesno brisanje.";
        }

        [Authorize(Roles = "admin, user")]
        [HttpGet]
        [Route("getArtistByIdBend")]
        public List<dynamic> GetArtistByIdBend(int idBend)
        {
            return _bendBLL.GetArtistByIdBend(idBend);
        }

    }
}
