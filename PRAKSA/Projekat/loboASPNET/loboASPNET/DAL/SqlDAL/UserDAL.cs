using loboASPNET.DAL.Interfaces;
using loboASPNET.Helper;
using loboASPNET.Models;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.DAL
{
    public class UserDAL : IUserDAL
    {
        private readonly IConfiguration _configuration;
        private string ConnectionString;

        public UserDAL(IConfiguration configuration)
        {
            _configuration = configuration;
            ConnectionString = ProtectionHelper.Singleton.GetSectionValue("ConnectionStrings:Connection1");
        }

        public UserModel TryLogin(LoginModel login)
        {            
            UserModel user = null;

            using (SqlConnection connection = new SqlConnection(ConnectionString))
            {
                try
                {
                    connection.Open();

                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = $"select user_role from [user] where username = @username and pass = @password";

                    SqlParameter parameter = new SqlParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@username";
                    parameter.Value = login.Username;
                    command.Parameters.Add(parameter);

                    parameter = new SqlParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@password";
                    parameter.Value = login.Password;
                    command.Parameters.Add(parameter);

                    SqlDataReader reader = command.ExecuteReader();

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
