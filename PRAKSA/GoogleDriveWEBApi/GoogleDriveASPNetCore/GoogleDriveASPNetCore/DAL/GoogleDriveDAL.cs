using GoogleDriveASPNetCore.DAL.Interface;
using GoogleDriveASPNetCore.Models;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GoogleDriveASPNetCore.DAL
{
    public class GoogleDriveDAL : IGoogleDriveDAL
    {
        private string connectionString;
        private readonly IConfiguration _configuration;
        public GoogleDriveDAL(IConfiguration configuration)
        {
            _configuration = configuration;
            connectionString = _configuration["ConnectionStrings:Connection:Value"];
        }

        public void CreateDocument(DocumentModel documentModel)
        {
            using (SqlConnection connection = new SqlConnection(connectionString)) {
                try {
                    connection.Open();
                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = "INSERT INTO document VALUES (@name, @drivePath, @creationDate, @driveDocumentId, @typeId)";

                    SqlParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@name";
                    parameter.Value = documentModel.name;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@drivePath";
                    parameter.Value = documentModel.drivePath;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.DateTime;
                    parameter.ParameterName = "@creationDate";
                    parameter.Value = DateTime.Now;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@driveDocumentId";
                    parameter.Value = documentModel.driveDocumentId;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@typeId";
                    parameter.Value = documentModel.type.id;
                    command.Parameters.Add(parameter);

                    command.ExecuteNonQuery();
                    connection.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
        }

        public string GetDocumentData(string driveId)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                try
                {
                    connection.Open();
                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = "SELECT * FROM document inner join [type] on document.[type_id] = [type].id where document.drive_document_id = @driveId FOR JSON AUTO";

                    SqlParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@driveId";
                    parameter.Value = driveId;
                    command.Parameters.Add(parameter);

                    StringBuilder json = new StringBuilder();
                    SqlDataReader reader = command.ExecuteReader();

                    if (!reader.HasRows)
                    {
                        json.Append("[]");
                    }
                    else
                    {
                        while (reader.Read()){
                            json.Append(reader.GetValue(0).ToString());
                        }
                    }

                    reader.Close();
                    connection.Close();

                    return json.ToString();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
        }

        public void CreateShere(string shereId, string fileId, string user)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                try
                {
                    connection.Open();

                    SqlCommand command = new SqlCommand("create_share", connection);
                    command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@shareId";
                    parameter.Value = shereId;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@id";
                    parameter.Value = fileId;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@user";
                    parameter.Value = user;
                    command.Parameters.Add(parameter);

                    command.ExecuteNonQuery();
                    connection.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
        }

        public int GetTypeId(string typeName)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                try
                {
                    connection.Open();

                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = "select id from [type] where [name] = @typeName";


                    SqlParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@typeName";
                    parameter.Value = typeName;
                    command.Parameters.Add(parameter);

                    SqlDataReader reader = command.ExecuteReader();

                    int typeId = -1;

                    if (reader.HasRows)
                    {
                        while (reader.Read())
                        {
                            typeId = Int32.Parse(reader.GetValue(0).ToString());
                        }
                    }

                    reader.Close();
                    connection.Close();

                    return typeId;
                }
                catch (Exception ex)
                {

                    throw new Exception(ex.Message);
                }
            }
        }

        public List<DocumentModel> GetDocumentsByTypeId(int typeId)
        {
            List<DocumentModel> doc = new List<DocumentModel>();

            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                try
                {
                    connection.Open();

                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = $"select * from document where [type_id] = @typeId";

                    SqlParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@typeId";
                    parameter.Value = (int)typeId;
                    command.Parameters.Add(parameter);

                    SqlDataReader reader = command.ExecuteReader();

                    while (reader.Read())
                    {
                        doc.Add(new DocumentModel()
                        {
                            id = Int32.Parse(reader.GetValue(0).ToString()),
                            name = reader.GetValue(1).ToString(),
                            drivePath = reader.GetValue(2).ToString(),
                            creationDate = DateTime.Parse(reader.GetValue(3).ToString()),
                            driveDocumentId = reader.GetValue(4).ToString()
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
            return doc;
        }
    }
}
