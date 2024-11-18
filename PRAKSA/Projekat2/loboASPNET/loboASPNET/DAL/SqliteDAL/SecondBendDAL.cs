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
    public class SecondBendDAL : IBendDAL
    {
        private readonly IConfiguration _configuration;
        private string ConnectionString;
        public SecondBendDAL(IConfiguration configuration)
        {
            _configuration = configuration;
            ConnectionString = ProtectionHelper.Singleton.GetSectionValue("ConnectionStrings:Connection2");
        }
        public List<dynamic> Select()
        {
            List<dynamic> band = new List<dynamic>();

            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"select b.idBend, b.nameBend, g.nameGenre from bend b join genre g on b.idGenre = g.idGenre";

                    SQLiteDataReader reader = command.ExecuteReader();

                    while (reader.Read())
                    {
                        band.Add(new
                        {
                            idBand = Int32.Parse(reader[0].ToString()),
                            nameBend = reader[1].ToString(),
                            nameGenre = reader[2].ToString(),
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
            return band;
        }
        public bool DeleteBendByID(int id)
        {
            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"DELETE FROM bend WHERE @id = idBend";

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

        public List<dynamic> GetBendByID(int id)
        {
            List<dynamic> band = new List<dynamic>();

            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();

                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"select b.nameBend, g.nameGenre from bend b join genre g on b.idBend=@id and g.idGenre = b.idGenre";

                    SQLiteParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@id";
                    parameter.Value = (int)id;
                    command.Parameters.Add(parameter);

                    SQLiteDataReader reader = command.ExecuteReader();


                    while (reader.Read())
                    {
                        band.Add(new
                        {
                            nameBend = reader[0].ToString(),
                            nameGenre = reader[1].ToString(),
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

            return band;
        }

        public List<dynamic> GetArtistByIdBend(int idBend)
        {
            List<dynamic> band = new List<dynamic>();

            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();

                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"select distinct a.idArtist, a.nameArtist, a.dateArtist, b.nameBend from bend b join artist a on a.idBend=@idBend and b.idBend=@idBend";

                    SQLiteParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@idBend";
                    parameter.Value = (int)idBend;
                    command.Parameters.Add(parameter);

                    SQLiteDataReader reader = command.ExecuteReader();

                    while (reader.Read())
                    {
                        band.Add(new
                        {
                            idArtist = Int32.Parse(reader[0].ToString()),
                            nameArtist = reader[1].ToString(),
                            dateArtist = reader[2].ToString(),
                            nameBend = reader[3].ToString(),
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

            return band;
        }
        public bool Insert(Bend bend)
        {
            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = "INSERT INTO bend VALUES (NULL, @nameBend, @idGenre)";

                    SQLiteParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@nameBend";
                    parameter.Value = bend.nameBend;
                    command.Parameters.Add(parameter);


                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@idGenre";
                    parameter.Value = bend.idGenre;
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
    }
}
