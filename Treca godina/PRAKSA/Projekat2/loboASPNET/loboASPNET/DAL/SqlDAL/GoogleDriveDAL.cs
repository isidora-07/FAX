using loboASPNET.DAL.Interfaces;
using loboASPNET.Helper;
using loboASPNET.Models.GoogleDrive;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace loboASPNET.DAL.SqlDAL
{
    public class GoogleDriveDAL : IGoogleDriveDAL
    {
        private string connectionString;
        private readonly IConfiguration _configuration;

        public GoogleDriveDAL(IConfiguration configuration)
        {
            _configuration = configuration;
            connectionString = ProtectionHelper.Singleton.GetSectionValue("ConnectionStrings:Connection3");
        }
       
        public void CreateDocument(DocumentModel documentModel)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                try
                {
                    connection.Open();
                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = "INSERT INTO document VALUES(@name, @drivePath, @creationDate, @driveDocumentId, @typeId)";

                    SqlParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@name";
                    parameter.Value = documentModel.Name;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@drivePath";
                    parameter.Value = documentModel.DrivePath;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.DateTime;
                    parameter.ParameterName = "@creationDate";
                    parameter.Value = DateTime.Now;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@driveDocumentId";
                    parameter.Value = documentModel.DriveDocumentId;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@typeId";
                    parameter.Value = documentModel.Type.Id;
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
                        while (reader.Read())
                        {
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

        public void CreateShare(string shareId, string id, string user)
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
                    parameter.Value = shareId;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@id";
                    parameter.Value = id;
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

        public List<DocumentModel> GetDocumentByType(int typeId)
        {
            List<DocumentModel> documenti = new List<DocumentModel>();

            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                try
                {
                    connection.Open();
                    SqlCommand command = connection.CreateCommand();
                    SqlCommand command1 = connection.CreateCommand();
                    command.CommandText = $"SELECT d.* FROM document d join [type] t on d.[type_id] = t.id where t.id = @typeId";


                    SqlParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@typeId";
                    parameter.Value = typeId;
                    command.Parameters.Add(parameter);

                    SqlDataReader reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        documenti.Add(new DocumentModel()
                        {
                            Id = Int32.Parse(reader[0].ToString()),
                            Name = reader[1].ToString(),
                            DrivePath = reader[2].ToString(),
                            CreationDate = DateTime.Parse(reader[3].ToString()),
                            DriveDocumentId = reader[4].ToString(),
                            Type = new TypeModel()
                            {
                                Id = Int32.Parse(reader[5].ToString()),
                            }

                        });
                    }
                    reader.Close();
                    connection.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
                return documenti;
            }
        }

    }
}
