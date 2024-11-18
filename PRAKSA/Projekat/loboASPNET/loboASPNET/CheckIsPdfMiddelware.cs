using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http;

namespace loboASPNET
{ 
    // You may need to install the Microsoft.AspNetCore.Http.Abstractions package into your project
    public class CheckIsPdfMiddelware
    {
        private readonly RequestDelegate _next;

        public CheckIsPdfMiddelware(RequestDelegate next)
        {
            _next = next;
        }

        public Task Invoke(HttpContext httpContext)
        {
            if (httpContext.Request.Path.ToString().ToLower().Equals("/api/googledrive/uploadfile"))
            {
                if (CheckIsPdf(httpContext.Request))
                {
                    httpContext.Request.Body.Position = 0;
                    return _next(httpContext);
                }
                else
                {
                    return httpContext.Response.WriteAsync("Niste poslali pdf file.");
                }
            }

            return _next(httpContext);
        }

        private bool CheckIsPdf(HttpRequest httpRequest)
        {
            httpRequest.EnableBuffering();

            string path = httpRequest.Query["path"];

            if (path.Contains(".pdf"))
            {
                return true;
            }

            return false;
        }
    }

    // Extension method used to add the middleware to the HTTP request pipeline.
    public static class CheckIsPdfExtensions
    {
        public static IApplicationBuilder UseCheckIsPdf(this IApplicationBuilder builder)
        {
            return builder.UseMiddleware<CheckIsPdfMiddelware>();
        }
    }
}
