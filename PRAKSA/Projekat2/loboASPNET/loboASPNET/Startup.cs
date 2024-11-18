using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using loboASPNET.BLL;
using loboASPNET.BLL.Interfaces;
using loboASPNET.DAL;
using loboASPNET.DAL.Interfaces;
using loboASPNET.DAL.SqlDAL;
using loboASPNET.Helper;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.DataProtection;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.IdentityModel.Tokens;

namespace loboASPNET
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers();

            services.AddTransient<ISongBLL, SongBLL>();
            services.AddTransient<IGenreBLL, GenreBLL>();
            services.AddTransient<IBendBLL, BendBLL>();
            services.AddTransient<IArtistBLL, ArtistBLL>();
            services.AddTransient<IUserBLL, UserBLL>();

            services.AddTransient<ISongDAL, SongDAL>();
            services.AddTransient<IGenreDAL, GenreDAL>();
            services.AddTransient<IBendDAL, BendDAL>();
            services.AddTransient<IArtistDAL, ArtistDAL>();
            services.AddTransient<IUserDAL, UserDAL>();

            services.AddTransient<IGoogleDriveApiHelper, GoogleDriveApiHelper>();
            services.AddTransient<IGoogleDriveDAL, GoogleDriveDAL>();
            services.AddTransient<IGoogleDriveBLL, GoogleDriveBLL>();

            /* services.AddTransient<ISongDAL, SecondSongDAL>();
             services.AddTransient<IGenreDAL, SecondGenreDAL>();
             services.AddTransient<IBendDAL, SecondBendDAL>();
             services.AddTransient<IArtistDAL, SecondArtistDAL>();
             services.AddTransient<IUserDAL, SecondUserDAL>();*/

            services.AddCors(o => o.AddPolicy("MyPolicy", builder =>
            {
                builder.AllowAnyOrigin()
                       .AllowAnyMethod()
                       .AllowAnyHeader();
            }));


            services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
                .AddJwtBearer(options =>
                {
                    options.TokenValidationParameters = new Microsoft.IdentityModel.Tokens.TokenValidationParameters
                    {
                        ValidateIssuer = true, 
                        ValidateAudience = true, 
                        ValidateLifetime = true, 
                        ValidateIssuerSigningKey = true,
                        ValidIssuer = Configuration["Jwt:Issuer"],
                        ValidAudience = Configuration["Jwt:Issuer"],
                        IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(Configuration["Jwt:key"]))
                    };
                });

            services.AddDataProtection();
        }
 
        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env, IDataProtectionProvider provider, ILoggerFactory loggerFactory)
        {
            Init(provider);

            loggerFactory.AddFile("Logs/logsFile.txt");

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseCors("MyPolicy");

            app.UseAuthentication();
            app.UseAuthorization();

            app.UseCheckIsPdf();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }

        private void Init(IDataProtectionProvider provider)
        {
            ProtectionHelper.Singleton.SetConfig(Configuration);

            ProtectionHelper.Singleton.SetProvider(provider, Configuration["DataProtection:Key:Value"]);
            ProtectionHelper.Singleton.GetSectionValue("DataProtection:Key");
        }

    }
}
