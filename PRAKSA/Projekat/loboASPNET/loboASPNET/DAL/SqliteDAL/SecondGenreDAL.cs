using loboASPNET.BLL;
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
    public class SecondGenreDAL : IGenreDAL
    {
        private readonly IConfiguration _configuration;
        private string ConnectionString;
        public SecondGenreDAL(IConfiguration configuration)
        {
            _configuration = configuration;
            ConnectionString = ProtectionHelper.Singleton.GetSectionValue("ConnectionStrings:Connection2");
        }
        public bool DeleteGenreByID(int id)
        {
            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"DELETE FROM genre WHERE @id = idGenre";

                    SQLiteParameter sqlParameter = command.CreateParameter();

                    sqlParameter = command.CreateParameter();
                    sqlParameter.DbType = System.Data.DbType.Int32;
                    sqlParameter.ParameterName = "@id";
                    sqlParameter.Value = id;
                    command.Parameters.Add(sqlParameter);

                    if (command.ExecuteNonQuery() == -1)
                    {
                        return false;
                    }

                    connection.Close();

                    return true;
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
        }

        public bool Insert(Genre genre)
        {
            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = "INSERT INTO genre VALUES (NULL, @nameGenre)";

                    SQLiteParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@nameGenre";
                    parameter.Value = genre.nameGenre;
                    command.Parameters.Add(parameter);

                    if (command.ExecuteNonQuery() == -1)
                    {
                        return false;
                    }

                    connection.Close();

                    return true;
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }

        }

        public List<Genre> Select()
        {
            List<Genre> genre = new List<Genre>();

            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"SELECT * FROM genre";

                    SQLiteDataReader reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        genre.Add(new Genre()
                        {
                            idGenre = Int32.Parse(reader[0].ToString()),
                            nameGenre = reader[1].ToString()
                        });
                    }

                    reader.Close();
                    connection.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
            return genre;
        }

        public Genre GetGenreByID(int id)
        {
            List<Genre> genre = new List<Genre>();

            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();

                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"select * from genre where idGenre = @id";

                    SQLiteParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@id";
                    parameter.Value = (int)id;
                    command.Parameters.Add(parameter);

                    SQLiteDataReader reader = command.ExecuteReader();

                    while (reader.Read())
                    {
                        genre.Add(new Genre()
                        {
                            idGenre = Int32.Parse(reader[0].ToString()),
                            nameGenre = reader[1].ToString()
                        });
                    }

                    reader.Close();
                    connection.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }

            return genre.FirstOrDefault();
        }

        public bool UpdateGenre(int idGenre, string nameGenre)
        {
            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"update genre set nameGenre = @nameGenre where idGenre = @idGenre";

                    SQLiteParameter sqlParameter = command.CreateParameter();

                    sqlParameter = command.CreateParameter();
                    sqlParameter.DbType = System.Data.DbType.Int32;
                    sqlParameter.ParameterName = "@idGenre";
                    sqlParameter.Value = idGenre;
                    command.Parameters.Add(sqlParameter);

                    sqlParameter = command.CreateParameter();
                    sqlParameter.DbType = System.Data.DbType.String;
                    sqlParameter.ParameterName = "@nameGenre";
                    sqlParameter.Value = nameGenre;
                    command.Parameters.Add(sqlParameter);

                    if (command.ExecuteNonQuery() == -1)
                    {
                        return false;
                    }

                    connection.Close();

                    return true;
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
        }

    }
}
