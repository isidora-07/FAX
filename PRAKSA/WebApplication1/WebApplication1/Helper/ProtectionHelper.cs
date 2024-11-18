using Microsoft.AspNetCore.DataProtection;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Helper
{
    public class ProtectionHelper
    {
        private IConfiguration _configuration;
        private IDataProtector _protector; //iz servisa pozvanog u startuo-u

        private static readonly ProtectionHelper singleton;

        public ProtectionHelper()
        {

        }

        static ProtectionHelper()
        {
            singleton = new ProtectionHelper();
        }

        public static ProtectionHelper Singleton
        {
            get { return singleton; }
        }

        public void SetConfig(IConfiguration configuration)
        {
            singleton._configuration = configuration;
        }

        public void SetProvider(IDataProtectionProvider provider, string key)
        {
            _protector = provider.CreateProtector(key);
        }

        public string GetSectionValue(string key)
        {
            string[] keys = key.Split(':');
            string value = _configuration[key + ":Value"];

            bool isProtected = bool.Parse(_configuration[key + ":Protected"]);

            if (isProtected == false)
            {
                string appSettingsPath = System.IO.Path.Combine(AppContext.BaseDirectory, "appsettings.json");

                string json = System.IO.File.ReadAllText(appSettingsPath); //citamo file

                dynamic jsonObject = Newtonsoft.Json.JsonConvert.DeserializeObject<Newtonsoft.Json.Linq.JObject>(json);

                jsonObject[keys[0]][keys[1]]["Value"] = _protector.Protect(value);
                jsonObject[keys[0]][keys[1]]["Protected"] = true;

                string output = Newtonsoft.Json.JsonConvert.SerializeObject(jsonObject, Newtonsoft.Json.Formatting.Indented);
                System.IO.File.WriteAllText(appSettingsPath, output);

                return value;


            }

            return _protector.Unprotect(value);


        }

    }
}
