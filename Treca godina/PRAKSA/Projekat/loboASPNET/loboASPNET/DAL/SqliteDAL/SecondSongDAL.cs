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
    public class SecondSongDAL : ISongDAL
    {
        private readonly IConfiguration _configuration;
        private string ConnectionString;
        public SecondSongDAL(IConfiguration configuration)
        {
            _configuration = configuration;
            ConnectionString = ProtectionHelper.Singleton.GetSectionValue("ConnectionStrings:Connection2");
        }
        public bool DeleteSongByID(int id)
        {
            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"DELETE FROM song WHERE @id = idSong";

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

        public List<dynamic> GetSongByID(int id)
        {
            List<dynamic> song = new List<dynamic>();

            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();

                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"select song.nameSong, bend.nameBend from song join bend on song.idBend = bend.idBend and song.idSong = @id";

                    SQLiteParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@id";
                    parameter.Value = (int)id;
                    command.Parameters.Add(parameter);

                    SQLiteDataReader reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        song.Add(new
                        {
                            nameSong = reader[0].ToString(),
                            nameBend = reader[1].ToString()
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

            return song;
        }

        public bool Insert(Song song)
        {
            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = "INSERT INTO song VALUES (NULL, @nameSong, @idBend)";

                    SQLiteParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@nameSong";
                    parameter.Value = song.nameSong;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@idBend";
                    parameter.Value = song.idBend;
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

        public List<dynamic> Select()
        {
            List<dynamic> song = new List<dynamic>();

            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"select s.idSong, s.nameSong, b.nameBend from song s join bend b on s.idBend = b.idBend";

                    SQLiteDataReader reader = command.ExecuteReader();

                    while (reader.Read())
                    {
                        song.Add(new
                        {
                            idSong = Int32.Parse(reader[0].ToString()),
                            nameSong = reader[1].ToString(),
                            nameBand = reader[2].ToString()
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
            return song;
        }

        public bool UpdateSong(int idSong, string nameSong)
        {
            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"UPDATE song SET nameSong = @nameSong WHERE idSong = @idSong";

                    SQLiteParameter sqlParameter = command.CreateParameter();

                    sqlParameter = command.CreateParameter();
                    sqlParameter.DbType = System.Data.DbType.Int32;
                    sqlParameter.ParameterName = "@idSong";
                    sqlParameter.Value = idSong;
                    command.Parameters.Add(sqlParameter);

                    sqlParameter = command.CreateParameter();
                    sqlParameter.DbType = System.Data.DbType.String;
                    sqlParameter.ParameterName = "@nameSong";
                    sqlParameter.Value = nameSong;
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
