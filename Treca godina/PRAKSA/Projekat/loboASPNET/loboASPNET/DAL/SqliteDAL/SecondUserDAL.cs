using loboASPNET.DAL.Interfaces;
using loboASPNET.Helper;
using loboASPNET.Models;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.DAL
{
    public class SecondUserDAL : IUserDAL
    {
        private readonly IConfiguration _configuration;
        private string ConnectionString;
        public SecondUserDAL(IConfiguration configuration)
        {
            _configuration = configuration;
            ConnectionString = ProtectionHelper.Singleton.GetSectionValue("ConnectionStrings:Connection2");
        }

        public UserModel TryLogin(LoginModel login)
        {
            UserModel user = null;

            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();

                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"select user_role from [user] where username = @username and pass = @password";

                    SQLiteParameter parameter = new SQLiteParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@username";
                    parameter.Value = login.Username;
                    command.Parameters.Add(parameter);

                    parameter = new SQLiteParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@password";
                    parameter.Value = login.Password;
                    command.Parameters.Add(parameter);

                    SQLiteDataReader reader = command.ExecuteReader();

                    while (reader.Read())
                    {
                        user = new UserModel()
                        {
                            Name = login.Username,
                            Role = reader[0].ToString().Trim()
                        };
                    }

                    reader.Close();
                    connection.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }

            return user;
        }
    }
}
