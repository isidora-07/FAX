using loboASPNET.Helper;
using loboASPNET.Models;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.BLL
{
    public class ArtistDAL : IArtistDAL
    {
        private readonly IConfiguration _configuration;
        private string ConnectionString;
        public ArtistDAL(IConfiguration configuration)
        {
            _configuration = configuration;
            ConnectionString = ProtectionHelper.Singleton.GetSectionValue("ConnectionStrings:Connection1");
        }


        public List<dynamic> Select()
        {
            List<dynamic> artist = new List<dynamic>();

            using (SqlConnection connection = new SqlConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = $"select a.idArtist, a.nameArtist, a.dateArtist, b.nameBend from artist a join bend b on a.idBend = b.idBend";

                    SqlDataReader reader = command.ExecuteReader();

                    while (reader.Read())
                    {
                        artist.Add(new
                        {
                            idArtist = Int32.Parse(reader[0].ToString()),
                            nameArtist = reader[1].ToString(),
                            dateArtist = reader[2].ToString(),
                            nameBend = reader[3].ToString()
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
            return artist;
        }
        
        public bool DeleteArtistByID(int id)
        {
            using (SqlConnection connection = new SqlConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = $"DELETE FROM artist WHERE idArtist = @id";

                    SqlParameter sqlParameter = command.CreateParameter();

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

        public List<dynamic> GetArtistByID(int id)
        {
            List<dynamic> artist = new List<dynamic>();

            using (SqlConnection connection = new SqlConnection(ConnectionString))
            {
                try
                {
                    connection.Open();

                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = $"select a.nameArtist, a.dateArtist, b.nameBend from artist a join bend b on b.idBend = a.idBend and a.idArtist = @id";

                    SqlParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@id";
                    parameter.Value = (int)id;
                    command.Parameters.Add(parameter);

                    SqlDataReader reader = command.ExecuteReader();

                    while (reader.Read())
                    {
                        artist.Add(new
                        {
                            nameArtist = reader[0].ToString(),
                            dateArtist = DateTime.Parse(reader[1].ToString()),
                            nameBend = reader[2].ToString()
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

            return artist;
        }

        public bool Insert(Artist artist)
        {
            using (SqlConnection connection = new SqlConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = "INSERT INTO artist VALUES (@nameArtist, @dateArtist, @idBend)";

                    SqlParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@nameArtist";
                    parameter.Value = artist.nameArtist;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.DateTime;
                    parameter.ParameterName = "@dateArtist";
                    parameter.Value = artist.dateArtist;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@idBend";
                    parameter.Value = artist.idBend;
                    command.Parameters.Add(parameter);

                    if (command.ExecuteNonQuery() == -1)
                    {
                        return false;
                    }

                    connection.Close();

                    return true;
                }
               catch(Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
        }

        public bool UpdateArtist(int idArtist, string nameArtist, string dateArtist)
        {
            using (SqlConnection connection = new SqlConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = $"update artist set nameArtist = @nameArtist, dateArtist = @dateArtist where idArtist = @idArtist";

                    SqlParameter sqlParameter = command.CreateParameter();

                    sqlParameter = command.CreateParameter();
                    sqlParameter.DbType = System.Data.DbType.Int32;
                    sqlParameter.ParameterName = "@idArtist";
                    sqlParameter.Value = idArtist;
                    command.Parameters.Add(sqlParameter);

                    sqlParameter = command.CreateParameter();
                    sqlParameter.DbType = System.Data.DbType.String;
                    sqlParameter.ParameterName = "@nameArtist";
                    sqlParameter.Value = nameArtist;
                    command.Parameters.Add(sqlParameter);

                    sqlParameter = command.CreateParameter();
                    sqlParameter.DbType = System.Data.DbType.String;
                    sqlParameter.ParameterName = "@dateArtist";
                    sqlParameter.Value = dateArtist.ToString();
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
