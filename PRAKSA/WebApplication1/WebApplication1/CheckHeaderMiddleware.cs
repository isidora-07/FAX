using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http;
using WebApplication1.BLL;

namespace WebApplication1
{
    // You may need to install the Microsoft.AspNetCore.Http.Abstractions package into your project
    public class CheckHeaderMiddleware
    {
        private readonly RequestDelegate _next;
        private readonly IWeatherBLL _weatherBLL;

        public CheckHeaderMiddleware(RequestDelegate next, IWeatherBLL weatherBLL)
        {
            _next = next;
            _weatherBLL = weatherBLL;
        }

        public Task Invoke(HttpContext httpContext)
        {
            bool validHash = CheckHash(httpContext.Request);
            if (validHash || httpContext.Request.Path.Equals("/weatherforecast/getHash"))
            {
                httpContext.Request.Body.Position = 0;
                return _next(httpContext);
            }
            return httpContext.Response.WriteAsync("Denied.");
        }

        private bool CheckHash(HttpRequest httpRequest)
        {
            httpRequest.EnableBuffering(); //citanje vise puta
            byte[] buffer = new byte[Convert.ToInt32(httpRequest.ContentLength)];
            httpRequest.Body.ReadAsync(buffer, 0, buffer.Length);

            string hashFromRequest = httpRequest.Headers["Test-Header"];
            string hash = _weatherBLL.Hash(buffer);

            if (hashFromRequest == hash)
            {
                return true;
            }
            return false;
        }
    }

    // Extension method used to add the middleware to the HTTP request pipeline.
    public static class CheckHeaderMiddlewareExtensions
    {
        public static IApplicationBuilder UseCheckHeaderMiddleware(this IApplicationBuilder builder)
        {
            return builder.UseMiddleware<CheckHeaderMiddleware>();
        }
    }
}
